package telco.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import telco.general.TelcoProperties;
import telco.general.gen.IPrivateTelco;
import telco.general.gen.IPrivateTelcoHelper;

/**
 * ConsoleServer repraesentiert die Konsolenschnittstelle zum Server.
 *
 * @author Himanshu Mongia
 */
public class ConsoleServer {

    /**
     * main-Methode
     *
     * @param args Konsolenargumente
     */
    public static void main(String args[]) {
        TelcoProperties p = new TelcoProperties();
        if (args.length != 1) {
            System.out.println("No Parameter given, needing *.properties");
        } else {
            System.out.println("Laoading properties from " + args[0]);
            if (p.readPropertiesFromFile(ClassLoader.getSystemResourceAsStream(args[0]))) {
                try {
                    System.out.println("Starting up " + p.getName() + " (" + p.getPrefix() + ")");
                    System.out.println("Loaded user: " + p.getUser().values());
                    final ORB orb = ORB.init(new String[0], null);
                    
                    POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
                    rootpoa.the_POAManager().activate();
                    
                    Server serverImpl = new Server(p);
                    serverImpl.setORB(orb);
//                    serverImpl.setORBExplicit(orb);
                    
                    org.omg.CORBA.Object ref = rootpoa.servant_to_reference(serverImpl);
                    IPrivateTelco cref = IPrivateTelcoHelper.narrow(ref);
                    
                    org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
                    final NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
                    serverImpl.setNaming(ncRef);
                    
                    String bindname = p.getPrefix();
                    System.out.println("Binding server to " + bindname);
                    final NameComponent path[] = ncRef.to_name(bindname);
                    ncRef.rebind(path, cref);
                    
                    Runtime.getRuntime().addShutdownHook(new Thread() {
                        @Override
                        public void run() {
                            try {
                                ncRef.unbind(path);
                                orb.shutdown(false);
                            } catch (Exception ex) {
                                Logger.getLogger(ConsoleServer.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                            }
                        }
                    });

                    //orb.run();

                    System.out.println("Server " + p.getPrefix() + " up. Hit enter to exit.");
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    String line = "";
                    while (true) {
                        line = br.readLine();
                        if (line != null && line.isEmpty()) {
                            break;
                        }
                    }
                    System.out.println("Stopping");
                    ncRef.unbind(path);
                    orb.shutdown(false);
                    
                } catch (IOException | NotFound | CannotProceed | org.omg.CosNaming.NamingContextPackage.InvalidName | AdapterInactive | ServantNotActive | WrongPolicy | InvalidName | org.omg.CORBA.UNKNOWN ex) {
                    Logger.getLogger(ConsoleServer.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
                } catch (Error e) {
                    System.err.println("Could't open connection to the server.");
                }
            }
        }
        
    }
}
