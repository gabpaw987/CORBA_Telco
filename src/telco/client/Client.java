package telco.client;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.TRANSACTION_ROLLEDBACK;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.CosTransactions.Control;
import org.omg.CosTransactions.Coordinator;
import org.omg.CosTransactions.CurrentHelper;
import org.omg.CosTransactions.Inactive;
import org.omg.CosTransactions.TransactionFactory;
import org.omg.CosTransactions.TransactionFactoryHelper;
import org.omg.CosTransactions.Unavailable;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import telco.general.TelcoHelper;
import telco.general.gen.DeliverException;
import telco.general.gen.IClient;
import telco.general.gen.IClientHelper;
import telco.general.gen.IClientPOA;
import telco.general.gen.IPrivateTelco;
import telco.general.gen.IPrivateTelcoHelper;
import telco.general.gen.ITransactionPOA;
import telco.general.gen.InvalidLoginException;
import telco.general.gen.Message;
import telco.general.gen.TelNr;

/**
 * Diese Klasse repraesentiert den Client. Sie kann sich selbst als Callback
 * <br> exportieren und besitzt die moeglichkeit sich zu eineServer zu connecten
 * und<br> Kommandos auszufuehren. Z.b. login, logout, sendSMS und stop.
 *
 * @author Himanshu Mongia
 * @author Josef Sochovsky
 * @author Gabriel Pawlowsky
 * @version 1.0
 */
public class Client extends IClientPOA {

    //Der Server, mit dem der Client verbunden ist
    private IPrivateTelco server;
    //Die ORB Referenz
    private ORB orb;
    //Die Callback-Instanz
    private IClient callback;
    //Der eigene Suffix, wenn null dann meist nich eingeloggt
    private String currentSuffix;
    //Der eigene PIN, wenn null dann meist nicht eingeloggt
    private String currentPin;

    /**
     * Konstruktor fuehrt den Superkonstruktor dey IClientPOA aus.
     */
    public Client() {
        super();
    }

    /**
     * Diese Methode verbindet den Client mit dem NamingService, um auf den<br>
     * Server zugreifen zu koennen.
     *
     * @param prefix zu dem verbunden werden soll
     * @return true, wenn alles fehlerfrei funktioniert hat
     */
    public boolean connect(String prefix) {
        try {
            System.out.println("Starting ORB");
            orb = ORB.init(new String[0], null);
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            System.out.println("Connecting to Server " + prefix);
            server = IPrivateTelcoHelper.narrow(ncRef.resolve_str(prefix));

            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(this);
            callback = IClientHelper.narrow(ref);

            return true;
        } catch (ServantNotActive ex) {
            System.err.println("The servant is not active!");
        } catch (WrongPolicy ex) {
            System.err.println("A wrong policy was provided!");
        } catch (AdapterInactive ex) {
            System.err.println("The adapter is inactive!");
        } catch (NotFound ex) {
            System.err.println("A NotFound-Exception occured");
        } catch (CannotProceed ex) {
            System.err.println("Cannot proceed!");
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
            System.err.println("An invalid name for naming context was provided!");
        } catch (InvalidName ex) {
            System.err.println("An invalid name was provided!");
        }
        return false;
    }

    /**
     * Stop-Methode, stoppt die CORBA-Verbindung des Client. Falls der
     * Client<br> eingeloggt war, wird er zuerst auch noch ausgeloggt.
     */
    public void stop() {
        if (currentSuffix != null && currentPin != null) {
            try {
                server.logout(currentSuffix, currentPin);
                currentSuffix = null;
                currentPin = null;
            } catch (InvalidLoginException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        orb.shutdown(true);
    }

    /**
     * Hier wird die Implementierung TransactionImpl fuer jede empfangene<br>
     * Nachricht instanziert. Die Message wird als Argument der useMessage()<br>
     * Methode von TransactionImpl uebergeben, und die Instanz des<br>
     * TransactionImpls wird mithilfe des Coordinators tatsaechlich als
     * Ressource<br> festgelegt.
     *
     * @param m Nachricht
     * @return erfolgreich oder nicht
     */
    @Override
    public boolean onSMS(Message m) {
        ITransactionPOA ti = new TransactionImpl();
        ti.useMessage(m);
        try {
            Control control = CurrentHelper.narrow(orb.resolve_initial_references("TransactionCurrent")).get_control();
            Coordinator coordinator = control.get_coordinator();
            coordinator.register_resource(ti._this(orb));
        } catch (InvalidName | Unavailable | Inactive ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new TRANSACTION_ROLLEDBACK();
        }
        return true;
    }

    /**
     * Siehe sendSms, nur dass diese Methode vom Server nur aufgerufen wird, wenn
     * er mit expliziten Transaktionen arbeitet.
     * 
     * @param m die Nachricht
     * @return ob es funktioniert hat
     */
    @Override
    public boolean onSMSExplicit(Message m, Control control) {
        ITransactionPOA ti = new TransactionImpl();
        ti.useMessageExplicit(m, control);
        try {
            Coordinator coordinator = control.get_coordinator();
            coordinator.register_resource(ti._this(orb));
        } catch (Unavailable | Inactive ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            throw new TRANSACTION_ROLLEDBACK();
        }
        return true;
    }

    /**
     * Loggt den Client am Server ein. Dabei werden die Login-Informatioenen<br>
     * gespeichert, sodass man spaeter weiss, wie diese heissen. Ausserdem
     * werden<br> bei einem erfolgreichen Login alle Nachrichten angezeigt, die
     * man verpasst hat.<br> Dies funktioniert nur, wenn man nicht bereits
     * eingeloggt ist.
     *
     * @param suffix des Clients der eingeloggt werden soll.
     * @param pin des Clients der eingeloggt werden soll.
     */
    public void login(String suffix, String pin) {
        if (currentSuffix != null && currentPin != null) {
            System.err.println("Already logged in!");
        } else {
            try {
                Message[] inbox = server.login(suffix, pin, callback);
                System.out.println("Missed messages:");
                for (Message m : inbox) {
                    System.out.println(TelcoHelper.formatMessage(m));
                }
                this.currentSuffix = suffix;
                this.currentPin = pin;
            } catch (InvalidLoginException ex) {
                System.err.println("Invalid login information!");
            }
        }
    }

    /**
     * Meldet sich wieder vom Server ab, sodass man den Client schliessen,
     * oder<br> sich mit einem anderen Server verbinden, aber keine Nachrichten
     * mehr schicken<br> kann.
     */
    public void logout() {
        try {
            if (currentSuffix == null | currentPin == null) {
                throw new InvalidLoginException();
            } else {
                server.logout(currentSuffix, currentPin);
                currentSuffix = null;
                currentPin = null;
                System.out.println("Logout was successful!");
            }
        } catch (InvalidLoginException ex) {
            System.err.println("Invalid login information or not logged in!");
        }
    }

    /**
     * Sendet eine Nachricht an beliebig viele Empfaenger. Dies geschieht ueber
     * eine<br> Verbindung zum Server, der dann die Nachricht an alle Empfaenger
     * weiter leitet.<br> Dies funktioniert nur, wenn man eingeloggt ist.
     *
     * @param m Message die gesendet werden soll
     */
    public void sendSms(Message m) {
        try {
            if (this.currentSuffix == null | currentPin == null) {
                throw new InvalidLoginException();
            }
            m.sender = new TelNr(this.server.getPrefix(), this.currentSuffix);
            if(server.send(currentSuffix, currentPin, m)){
                System.out.println("Message sent successfully.");
            } else {
                System.out.println("Message could not been sent.");
            }
        } catch (InvalidLoginException ex) {
            System.err.println("Invalid login information or not logged in!");
        } catch (DeliverException ex) {
            System.err.println("Message could not be delivered!");
        }
    }
    
    /**
     * Siehe sendSms, nur dass die Methode zum Senden mit expliziten Transaktionen <br>
     * am Server aufgerufen wird.
     * 
     * @param m Message die gesendet werden soll
     */
    public void sendSmsExplicit(Message m) {
        try {
            if (this.currentSuffix == null | currentPin == null) {
                throw new InvalidLoginException();
            }
            m.sender = new TelNr(this.server.getPrefix(), this.currentSuffix);
            if(server.sendExplicit(currentSuffix, currentPin, m)){
                System.out.println("Message sent successfully.");
            } else {
                System.out.println("Could not send message properly.");
            }
        } catch (InvalidLoginException ex) {
            System.err.println("Invalid login information or not logged in!");
        } catch (DeliverException ex) {
            System.err.println("Message could not be delivered!");
        }
    }
}