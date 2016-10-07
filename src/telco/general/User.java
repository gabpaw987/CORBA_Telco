package telco.general;

import java.util.ArrayList;
import telco.general.gen.IClient;
import telco.general.gen.Message;
import telco.general.gen.TelNr;

/**
 * Der User bildet einen User des Telcodienstes ab welcher Nachrichten senden
 * und empfangen darf.
 *
 * @author Timon Hoebert
 */
public class User {
    
    private TelNr nr;
    private String pin;
    private IClient callback;
    private ArrayList<Message> mailbox;

    /**
     * Erstellt einene neuen User mit Nummer und Pin
     *
     * @param nr die Nummer des Users
     * @param pin der Pin des Users
     */
    public User(TelNr nr, String pin) {
        this.nr = nr;
        this.pin = pin;
        callback = null; // = offline
        mailbox = new ArrayList<>();
    }

    /**
     * Ueberprueft den Pin mit dem eingegebenen Versuch
     *
     * @param trypin der versuchte Pin
     * @return true falls die beiden gleich sind, andernfalls false
     */
    public boolean checkPin(String trypin) {
        return (this.pin.equalsIgnoreCase(trypin));
    }

    /**
     * Der Login setzt das Callback des Users
     *
     * @param callback das zu setzende Callback
     */
    public void loginWithCallback(IClient callback) {
        this.callback = callback;
    }

    /**
     * Der Logout loescht das gesetzte Callback
     */
    public void logout() {
        this.callback = null;
    }
    
    public IClient getCallBack() {
        return this.callback;
    }

    /**
     * Liefert die Userdaten ohne Pin in einem lesbaren Format
     *
     * @return die Userdaten ohne Pin in einem lesbaren Format
     */
    @Override
    public String toString() {
        return getNr().prefix + "/" + getNr().suffix + " " + (callback != null ? "[online]" : "[offline]" + " " + mailbox.size() + " INBOX");
    }

    /**
     * @return the nr
     */
    public TelNr getNr() {
        return nr;
    }

    /**
     * @return the mailbox as array
     */
    public Message[] getMailbox() {
        return mailbox.toArray(new Message[mailbox.size()]);
    }
    
    public void addToMailbox(Message m) {
        mailbox.add(m);
    }
}
