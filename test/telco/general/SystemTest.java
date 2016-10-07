package telco.general;

import java.util.Date;
import static org.junit.Assert.*;
import org.junit.Test;
import telco.client.Client;
import telco.general.gen.Message;
import telco.general.gen.TelNr;

/**
 * Die Testklasse testet die Methoden des Clients und baut eine Verbindung zum
 * <br> TMobile-Server auf. <br>
 *
 * @author Josef Sochovsky
 * @author Himanshu Mongia
 */
public class SystemTest {

    /**
     * Testen der Methode connect des Clients. Der Client soll sich mit dem
     * Server <br> in Verbindung setzen und die Kommunikation aufbauen.
     */
    @Test
    public void testConnect() {
        System.out.println("Connect");
        Client c = new Client();
        assertTrue(c.connect("0676"));
    }

    /**
     * Testen der Methode login des Clients. Der Client soll sich beim
     * Telco-Server <br> anmelden um SMSs versenden zu koennen.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        Client c = new Client();
        c.connect("0676");
        c.login("123456", "1234");
    }

    /**
     * Testen der Methode logout des Clients. Der Client kann sich bei Server
     * nach <br> der erfolgreichen Anmeldung auch wieder abmelden.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        Client c = new Client();
        c.connect("0676");
        c.login("123456", "1234");
        c.logout();
    }

    /**
     * Dieser Test soll eine SMS versenden. Hierbei ist die Nummer des
     * Empfaengers <br> korrekt die Nachricht kann zugestellt werden.
     */
    @Test
    public void testSendSmsPos() {
        System.out.println("sendSms");
        Client c = new Client();
        c.connect("0676");
        c.login("123456", "1234");
        TelNr[] array = new TelNr[1];
        array[0] = new TelNr("0676", "123456");
        c.sendSms(new Message("TestCase: testSendSmsPos", new TelNr("0676", "123456"), new Date().getTime(), array));
    }
    
    /**
     * Dieser Test soll eine SMS versenden. Hierbei ist die Nummer des
     * Empfaengers <br> korrekt die Nachricht kann zugestellt werden.
     * Hierbei kommen ausserdem explizite Transaktionen zum Einsatz.
     */
    @Test
    public void testSendSmsExplicitPos() {
        System.out.println("sendSms");
        Client c = new Client();
        c.connect("0664");
        c.login("123456", "1234");
        TelNr[] array = new TelNr[1];
        array[0] = new TelNr("0664", "123456");
        c.sendSmsExplicit(new Message("TestCase: testSendSmsPos", new TelNr("0664", "123456"), new Date().getTime(), array));
    }

    /**
     * Bei diesem Test wird versucht eine SMS zu versenden, jedoch wird ein <br>
     * falscher Empfaenger angegeben. Somit wird das senden unmoeglich und die
     * <br> Transaktion wird gerollbacked.
     */
    @Test
    public void testSendSmsNeg() {
        System.out.println("sendSms");
        Client c = new Client();
        c.connect("0676");
        c.login("123456", "1234");
        TelNr[] array = new TelNr[1];
        array[0] = new TelNr("0676", "1234567890");
        c.sendSms(new Message("TestCase: testSendSmsNeg", new TelNr("0676", "123456"), new Date().getTime(), array));
    }
    
    /**
     * Bei diesem Test wird versucht eine SMS zu versenden, jedoch wird ein <br>
     * falscher Empfaenger angegeben. Somit wird das senden unmoeglich und die
     * <br> Transaktion wird gerollbacked.
     * Hierbei kommen ausserdem explizite Transaktionen zum Einsatz.
     */
    @Test
    public void testSendSmsExplicitNeg() {
        System.out.println("sendSms");
        Client c = new Client();
        c.connect("0664");
        c.login("123456", "1234");
        TelNr[] array = new TelNr[1];
        array[0] = new TelNr("0664", "1234567890");
        c.sendSmsExplicit(new Message("TestCase: testSendSmsNeg", new TelNr("0664", "123456"), new Date().getTime(), array));
    }
}
