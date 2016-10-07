/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telco.client;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CORBA.TRANSACTION_ROLLEDBACK;
import org.omg.CosTransactions.Control;
import org.omg.CosTransactions.Coordinator;
import org.omg.CosTransactions.HeuristicCommit;
import org.omg.CosTransactions.HeuristicHazard;
import org.omg.CosTransactions.HeuristicMixed;
import org.omg.CosTransactions.HeuristicRollback;
import org.omg.CosTransactions.Inactive;
import org.omg.CosTransactions.NotPrepared;
import org.omg.CosTransactions.Unavailable;
import org.omg.CosTransactions.Vote;
import telco.general.TelcoHelper;
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

    private Message m;

    /**
     * speichert die uebergebene Message einfach als Attribut
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
        Logger.getLogger(TransactionImpl.class.getName()).log(Level.WARNING, "Transaction rolled back!");
    }

    /**
     * gibt die gespeicherte Nachricht tatsaehlich aus
     *
     * @throws NotPrepared
     * @throws HeuristicHazard
     * @throws HeuristicMixed
     * @throws HeuristicRollback
     */
    @Override
    public void commit() throws NotPrepared, HeuristicHazard, HeuristicMixed, HeuristicRollback {
        System.out.println("Receive message within a transaction:");
        System.out.println(TelcoHelper.formatMessage(m));
    }

    /**
     * commit()-Methode wird aufgerufen
     *
     * @throws HeuristicHazard
     */
    @Override
    public void commit_one_phase() throws HeuristicHazard {
        try {
            commit();
        } catch (NotPrepared | HeuristicMixed | HeuristicRollback ex) {
            Logger.getLogger(TransactionImpl.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void forget() {
    }

    @Override
    public void useMessageExplicit(Message m, Control control_) {
        this.m = m;
    }
}
