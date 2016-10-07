/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package telco.general;

import static org.junit.Assert.*;
import org.junit.Test;
import telco.general.gen.Message;
import telco.general.gen.TelNr;

/**
 * Testet den TelcoHelper und dessen Hilfsmethoden
 *
 * @author Timon Hoebert
 */
public class TelcoHelperTest {
    
    /**
     * Test of formatMessage method, of class TelcoHelper.
     */
    @Test
    public void testFormatMessage() {
        System.out.println("formatMessage");
        Message m = new Message("MESSAGE", new TelNr("PREFIX", "SUFFIX"), 1363936901896L, new TelNr[]{new TelNr("EGAL", "EGAL")});
        String expResult = "22.03.2013 @ 08:21 | PREFIX/SUFFIX: MESSAGE";
        String result = TelcoHelper.formatMessage(m);
        assertEquals(expResult, result);
    }
}
