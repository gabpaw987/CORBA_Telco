package telco.general;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import telco.general.gen.TelNr;

/**
 * TelcoProperties liest diverse Einstellungen aus einem Properties-File ein
 * und stellt diese dem Client und dem Server zur Verfuegung.
 *
 * @author Timon Hoebert
 */
public class TelcoProperties {

    /**
     * Der Key des Prefixes
     */
    private static final String PREFIX = "prefix";
    /**
     * Der Key des Namens
     */
    private static final String NAME = "name";
    private String prefix;
    private String name;
    private ConcurrentHashMap<String, User> user;

    /**
     * Erstellt neue uneingelesene Properties
     */
    public TelcoProperties() {
        user = new ConcurrentHashMap<>();
    }

    /**
     * Liesst die einzelnen Einstellungen aus der Datei
     * und speichert diese als Attribute.
     */
    public boolean readPropertiesFromFile(InputStream is) {
        System.out.println("Reading Properties");
        if (is != null) {
            Properties props = new Properties();
            try {
                props.load(is);
                this.prefix = props.getProperty(PREFIX);
                this.name = props.getProperty(NAME);
                Set<String> keys = props.stringPropertyNames();
                for (String suffix : keys) {
                    if (suffix.matches("\\d*")) {
                        TelNr nr = new TelNr(getPrefix(), suffix);
                        String pin = props.getProperty(suffix);
                        getUser().put(suffix, new User(nr, pin));
                    }
                }
                return true;
            } catch (IOException ex) {
                System.err.println("An error occured while reading the"
                        + " properties file!");
            } finally {
                try {
                    is.close();
                } catch (IOException ex) {
                    // egal
                }
            }
        } else {
            System.err.println("The properties file could not be found!");
        }
        return false;
    }

    /**
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the user
     */
    public ConcurrentHashMap<String, User> getUser() {
        return user;
    }
}
