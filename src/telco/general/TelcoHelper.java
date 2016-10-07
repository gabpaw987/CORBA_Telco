
package telco.general;

import telco.general.gen.Message;

/**
 * Der TelcoHelper stellt statische Hilfsmethoden fuer Server und Client zur Verfuegung
 *
 * @author Timon Hoebert
 */
public class TelcoHelper {
    
    /**
     * Formatiert die uebergebene Message im Format dd.mm.yyyy @ hh:mm | PREFIX/SUFFIX: TEXT.
     * @param m die zu formatierende Message
     * @return die Formatierte Message im Format dd.mm.yyyy @ hh:mm | PREFIX/SUFFIX: TEXT.
     */
    public static String formatMessage(Message m){
        
        return String.format("%1$td.%1$tm.%1$tY @ %1$tR | %2$s/%3$s: %4$s", m.time, m.sender.prefix, m.sender.suffix, m.text);
    }
}
