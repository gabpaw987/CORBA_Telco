package telco.general;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Testet den Propertiesreader mit einem temporaeren File
 * @author Timon Hoebert
 */
public class TelcoPropertiesTest {
    
    private File file;
    
    /**
     * Erstellt ein tempraeres Propertiesfile mit testdaten
     */
    @Before
    public void setUp() {
        try {
            file = File.createTempFile("test", ".properties");
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            br.write("prefix:0664\n");
            br.write("name:bob\n");
            br.write("123456:1234\n");
            br.write("234567:2345\n");
            br.flush();
            System.out.println("Created "+file.toString());
            System.out.println("Filepath "+file.getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("Couldnt create tempfile");
        }
    }
    
    /**
     * Loescht das tempraere file
     */
    @After
    public void tearDown() {
        if (file.exists()) file.delete();
    }

    /**
     * Test of readPropertiesFromFile method, of class TelcoProperties.
     */
    @Test
    public void testReadPropertiesFromFile() {
        System.out.println("readPropertiesFromFile");
        TelcoProperties instance = new TelcoProperties();
        boolean result = false;
        try {
            result = instance.readPropertiesFromFile(new FileInputStream(file));
        } catch (FileNotFoundException ex) {
            fail("File not found: "+ex.toString());
        }
        assertTrue(result);
        assertEquals("bob",instance.getName());
        assertEquals("0664", instance.getPrefix());
        assertEquals("{123456=0664/123456 [offline] 0 INBOX, 234567=0664/234567 [offline] 0 INBOX}", instance.getUser().toString());
    }
}
