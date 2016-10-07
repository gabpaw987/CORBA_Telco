package telco.server;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORB;
import org.omg.CORBA.TRANSACTION_ROLLEDBACK;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.CosTransactions.Control;
import org.omg.CosTransactions.Coordinator;
import org.omg.CosTransactions.Current;
import org.omg.CosTransactions.CurrentHelper;
import org.omg.CosTransactions.HeuristicHazard;
import org.omg.CosTransactions.HeuristicMixed;
import org.omg.CosTransactions.Inactive;
import org.omg.CosTransactions.NoTransaction;
import org.omg.CosTransactions.SubtransactionsUnavailable;
import org.omg.CosTransactions.TransactionFactory;
import org.omg.CosTransactions.TransactionFactoryHelper;
import org.omg.CosTransactions.Unavailable;
import telco.client.Client;
import telco.general.TelcoProperties;
import telco.general.User;
import telco.general.gen.DeliverException;
import telco.general.gen.IClient;
import telco.general.gen.IPrivateTelcoPOA;
import telco.general.gen.IPublicTelco;
import telco.general.gen.IPublicTelcoHelper;
import telco.general.gen.InvalidLoginException;
import telco.general.gen.Message;
import telco.general.gen.TelNr;

/**
 * Diese Klasse repraesentiert eine Telco, und erbt daher von IPrivateTelcoPOA.
 *
 * @author Himanshu Mongia
 * @author Josef Sochovsky
 * @author Timon Hoebert
 */
public class Server extends IPrivateTelcoPOA {

    private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
    private TelcoProperties properties;
    private String name;
    private String prefix;
    private ORB orb;
    private NamingContextExt naming;
    private Current ts_current = null;
    private Control control = null;
    private TransactionFactory transactionFactory;

    /**
     * einfacher Copy-Konstruktor, mit super-Aufruf
     *
     * @param properties verstellbaren Eigenschaften
     * @throws org.omg.CORBA.ORBPackage.InvalidName siehe
     * IPrivateTelcoPOA-Konstruktor
     */
    public Server(TelcoProperties properties) throws org.omg.CORBA.ORBPackage.InvalidName {
        super();
        this.properties = properties;
        this.users = properties.getUser();

        this.name = properties.getName();
        this.prefix = properties.getPrefix();
    }

    /**
     * Diese Methode logt einen User ein, wobei dieser in der users-Liste
     * registriert sein muss.
     *
     * @param suffix die Nummer des Users ohne Prefix (0664 etc...)
     * @param pin der PIN des Users
     * @return die User-referenz
     * @throws InvalidLoginException Falls der User nicht registriert, oder der
     * PIN nicht uebereinstimmt
     */
    public User checkLogin(String suffix, String pin) throws InvalidLoginException {
        User u = users.get(suffix);
        if (u == null) {
            throw new InvalidLoginException("Incorrect credentials"); // invalid username
        } else {
            if (!u.checkPin(pin)) {
                throw new InvalidLoginException("Incorrect credentials"); // invalid pin
            }
        }
        return u;
    }

    /**
     * @see IPrivateTelcoPOA.login
     */
    @Override
    public Message[] login(String suffix, String pin, IClient callback) throws InvalidLoginException {
        User user = checkLogin(suffix, pin);
        user.loginWithCallback(callback);
        Message[] inbox = user.getMailbox();
        return inbox;
    }

    /**
     * Diese Methode logt den User aus.
     *
     * @param suffix die Nummer des Users ohne Prefix (0664 etc...)
     * @param pin der PIN des Users
     * @return erfolgreich oder nicht
     * @throws InvalidLoginException Falls der User nicht registriert, oder der
     * PIN nicht uebereinstimmt
     */
    @Override
    public boolean logout(String suffix, String pin) throws InvalidLoginException {
        User user = checkLogin(suffix, pin);
        user.logout();
        return true;
    }

    /**
     * sendet eine SMS an die gewuenschten User. Hier wird auch mittels
     * <code>ts_current.begin()</code> die Transaktion begonnen, welche am Ende
     * der Methode commited wird, und bei einer Exception gerollbacked wird.
     *
     * @param suffix die Nummer des Users ohne Prefix (0664 etc...)
     * @param pin der PIN des Users
     * @param m die Nachricht
     * @return erfolgreich oder nicht
     */
    @Override
    public boolean send(String suffix, String pin, Message m) throws DeliverException {
        try {
            ts_current.set_timeout(40);
            ts_current.begin();
            try {
                User user = checkLogin(suffix, pin);
                m.sender = user.getNr(); // sicherheitshalber korrekten sender setzten!
                this.deliver(m);

                ConcurrentHashMap<String, IPublicTelco> cache = new ConcurrentHashMap<>();
                for (TelNr rnr : m.reciever) {

                    String prefix = rnr.prefix;
                    if (!prefix.equals(this.prefix)) { // an eigene clients wurden schon gesendet
                        IPublicTelco server = cache.get(prefix);
                        if (server == null) {
                            server = IPublicTelcoHelper.narrow(naming.resolve_str(prefix));
                            cache.put(prefix, server);
                            System.out.println("Contacted telco \"<" + server.getName() + ">\" (<" + prefix + ">) successfully.");
                        }
                        server.deliver(m);
                    }
                }
                ts_current.commit(true);
                return true;
            } catch (org.omg.CORBA.TRANSIENT | org.omg.CORBA.COMM_FAILURE | NotFound | CannotProceed | InvalidName | InvalidLoginException | DeliverException | HeuristicHazard | HeuristicMixed ex) {
                try {
                    ts_current.rollback();
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                } catch (NoTransaction e) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, e.getMessage(), e);
                }
            } catch (NoTransaction ex) {
                throw new DeliverException();
            }
        } catch (SubtransactionsUnavailable ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }

    /**
     * Siehe sendExplicit. Diese Implementierung der Methode ist allerdings darauf<br>
     * ausgelegt, um mit expliziten Transaktionen zu arbeiten. Wenn am Anfang <br>
     * der Transaktionskontext (control) aus der TransactionFactory erzeugt wird, <br>
     * wird gleichzeitig die erstellte Transaktion gestartet.
     *
     * @param suffix die Nummer des Users ohne Prefix (0664 etc...)
     * @param pin der PIN des Users
     * @param m die Nachricht
     * @return erfolgreich oder nicht
     */
    @Override
    public boolean sendExplicit(String suffix, String pin, Message m) throws DeliverException {
        try {
            boolean wasWorking = true;
            this.control = transactionFactory.create(20);
            
            User user = checkLogin(suffix, pin);
            m.sender = user.getNr(); // sicherheitshalber korrekten sender setzten!
            wasWorking = this.deliverExplicit(m, control);

            ConcurrentHashMap<String, IPublicTelco> cache = new ConcurrentHashMap<>();
            for (TelNr rnr : m.reciever) {
                String prefix = rnr.prefix;
                if (!prefix.equals(this.prefix)) { // an eigene clients wurden schon gesendet
                    IPublicTelco server = cache.get(prefix);
                    if (server == null) {
                        server = IPublicTelcoHelper.narrow(naming.resolve_str(prefix));
                        cache.put(prefix, server);
                        System.out.println("Contacted telco \"<" + server.getName() + ">\" (<" + prefix + ">) successfully.");
                    }
                    if(wasWorking) {
                        wasWorking = server.deliverExplicit(m, control);
                    }
                }
            }
            if(wasWorking) {
                control.get_terminator().commit(true);
            }
            return true;
        } catch (org.omg.CORBA.TRANSIENT | org.omg.CORBA.COMM_FAILURE | NotFound | CannotProceed | InvalidName | InvalidLoginException | DeliverException | HeuristicHazard | HeuristicMixed ex) {
            try {
                control.get_terminator().rollback();
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            } catch (Unavailable ex1) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (Unavailable ex) {
            throw new DeliverException();
        }
        return false;
    }

    /**
     * sendet Nachrichten an die Telco-internen User. Falls die User online
     * sind, ist server-seitig kein Transaktions-Kontext noetig, da dieser vom
     * Client in der onSMS-Methode uebernommen wird. Falls jedoch der User
     * offline ist, muss wieder die useMessage Methode benutzt werden, welche
     * beim Commit die Nachricht in die Mailbox des erwuenschten Users schreibt.
     * s
     *
     * @param m die Nachricht
     * @return erfolgreich oder nicht
     */
    @Override
    public boolean deliver(Message m) {
        try {
            for (TelNr rnr : m.reciever) {
                if (!rnr.prefix.equalsIgnoreCase(prefix)) {
                    continue; // other telco - ignore
                }
                User receiver = users.get(rnr.suffix);
                if (receiver != null) {
                    TransactionImpl ti = new TransactionImpl(receiver, orb);
                    if (receiver.getCallBack() != null) {
                        receiver.getCallBack().onSMS(m);
                    } else {
                        ti.useMessage(m);
                    }
                    Control control = ts_current.get_control();
                    Coordinator coordinator = control.get_coordinator();
                    coordinator.register_resource(ti._this(orb));

                } else {
                    ts_current.rollback();
                    return false;
                }
            }
        } catch (Unavailable | Inactive | NoTransaction ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new TRANSACTION_ROLLEDBACK();
        }
        return true;
    }

    /**
     * Siehe deliver. Diese Implementierung der Methode ist allerdings darauf<br>
     * ausgelegt, um mit expliziten Transaktionen zu arbeiten. Hierbei ist wichtig <br>
     * zu beachten, dass das Transaktionsobjekt erst nach dem befuellen mit der <br>
     * Nachricht bei der Transaktion registriert werden darf.
     * 
     * @param m die Nachricht
     * @param control der Transaktionskontext
     * @return erfolgreich oder nicht
     */
    @Override
    public boolean deliverExplicit(Message m, Control control) {
        try {
            for (TelNr rnr : m.reciever) {
                if (!rnr.prefix.equalsIgnoreCase(prefix)) {
                    continue; // other telco - ignore
                }
                User receiver = users.get(rnr.suffix);
                
                if (receiver != null) {
                    TransactionImpl ti = new TransactionImpl(receiver, orb);
                    if (receiver.getCallBack() != null) {
                        receiver.getCallBack().onSMSExplicit(m, control);
                    } else {
                        ti.useMessageExplicit(m, control);
                    }
                    Coordinator coordinator = control.get_coordinator();
                    coordinator.register_resource(ti._this(orb));  
                } else {
                    control.get_terminator().rollback();
                    return false;
                }
            }
        } catch (Inactive| Unavailable ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new TRANSACTION_ROLLEDBACK();
        }
        return true;
    }

    /**
     * Bei der Erzeugung des Servers wird der orb gesetzt, welcher auch fuer die
     * Transaktion gebraucht wird. Hier wird also das Current-Objekt aus den im
     * build-File definierten intial references geholt.
     *
     * @param orb Object Request Broker
     * @throws org.omg.CORBA.ORBPackage.InvalidName Falscher Name des Current
     * (default=TransactionCurrent)
     */
    public void setORB(ORB orb) throws org.omg.CORBA.ORBPackage.InvalidName {
        this.orb = orb;
        this.ts_current = CurrentHelper.narrow(orb.resolve_initial_references("TransactionCurrent"));
    }

    /**
     * Diese Methode setzt den ORB fuer die Nutzung von expliziten Transaktionen.<br>
     * dazu muss aus dem NameService der TransactionService upgelooked werden<br>
     * aus dem dann eine TransactionFactory erzeugt werden kann. Diese wird <br>
     * um ggf. spaeter aus ihr Transaktionen zu erzeugen.
     * 
     * @param orb Object Request Broker
     * @throws org.omg.CORBA.ORBPackage.InvalidName Lookup des NameService scheitert
     * meist aufgrund eines falschen Namens
     */
    public void setORBExplicit(ORB orb) throws org.omg.CORBA.ORBPackage.InvalidName {
        try {
            this.orb = orb;
            NamingContextExt nc =
                    NamingContextExtHelper.narrow(orb.resolve_initial_references("NameService"));
            NameComponent[] name = new NameComponent[1];
            name[0] = new NameComponent("TransactionService", "service");
            this.transactionFactory =
                    TransactionFactoryHelper.narrow(nc.resolve(name));
        } catch (CannotProceed | InvalidName | NotFound ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return den Namen
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return den Prefix
     */
    @Override
    public String getPrefix() {
        return prefix;
    }

    /**
     * @param naming the naming to set
     */
    public void setNaming(NamingContextExt naming) {
        this.naming = naming;
    }
}
