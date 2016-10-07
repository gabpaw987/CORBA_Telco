/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telco.server;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.TRANSACTION_ROLLEDBACK;
import org.omg.CosTransactions.Control;
import org.omg.CosTransactions.Coordinator;
import org.omg.CosTransactions.HeuristicCommit;
import org.omg.CosTransactions.HeuristicHazard;
import org.omg.CosTransactions.HeuristicMixed;
import org.omg.CosTransactions.Inactive;
import org.omg.CosTransactions.Unavailable;
import org.omg.CosTransactions.Vote;
import telco.client.Client;
import telco.general.User;
import telco.general.gen.ITransactionPOA;
import telco.general.gen.Message;

/**
 * Die TransactionImpl Klasse ist die Hilfsklasse fuer Transaktionen. Sie erbt
 * von ITransactionPOA. Sie erbt deshalb von <ITransaction>POA weil diese Klasse
 * die Interfaces TransactionalObject und Resource erbt, welche fuer eine
 * Transaktion notwendig sind.
 *
 * @author Himanshu Mongia
 * @author Josef Sochovsky
 */
public class TransactionImpl extends ITransactionPOA {

    private ORB orb;
    private Coordinator coord;
    private Message m;
    private User user;

    /**
     * Copy-Konstruktor
     *
     * @param user Der User, welcher die Nachricht erhalten soll
     * @param orb das ORB
     */
    public TransactionImpl(User user, ORB orb) {
        this.orb = orb;
        this.user = user;
    }

    /**
     * speichert die Message einfach nur als Attribut
     *
     * @param m die Nachricht
     */
    @Override
    public void useMessage(Message m) {
        this.m = m;
    }

    /**
     * gibt an ob commited werden kann oder nicht
     *
     * @return Vote
     * @throws HeuristicHazard
     * @throws HeuristicMixed
     */
    @Override
    public Vote prepare() throws HeuristicHazard, HeuristicMixed {
        return Vote.VoteCommit;
    }

    /**
     * gibt eine zusaetzliche Fehlermeldung aus
     *
     * @throws HeuristicHazard
     * @throws HeuristicMixed
     * @throws HeuristicCommit
     */
    @Override
    public void rollback() throws HeuristicHazard, HeuristicMixed, HeuristicCommit {
        Logger.getLogger(telco.client.TransactionImpl.class.getName()).log(Level.WARNING, "Transaction rolled back!");
    }

    /**
     * Wenn die Nachricht nicht null ist, wird dieser der Mailbox des Users
     * hinzugefuegt
     */
    @Override
    public void commit() {
        if (m != null) {
            user.addToMailbox(m);
        }
    }

    /**
     * commit()-Methode wird aufgerufen
     *
     * @throws HeuristicHazard
     */
    @Override
    public void commit_one_phase() throws HeuristicHazard {
        this.commit();
    }

    @Override
    public void forget() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void useMessageExplicit(Message m, Control control_) {
            this.m = m;
    }
}
