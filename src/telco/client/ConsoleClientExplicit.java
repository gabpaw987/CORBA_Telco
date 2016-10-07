package telco.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import telco.general.exceptions.ConsoleParseException;
import telco.general.gen.Message;
import telco.general.gen.TelNr;

/**
 * Konsolen-Client mit main-Methode, der es ermoeglicht die Client-Klasse zu benutzen.<br>
 * Es wird zuerst ein Client erstellt und sich mit dem Naming verbunden. Danach<br>
 * kann der Benutzer so lange Kommandos eingeben, bis er dem Client beendet.
 * 
 * @author Gabriel Pawlowsky
 * @version 1.0
 */
public class ConsoleClientExplicit {
    
    /**
     * main-Methode des Console-Client, die die im Klassenkommentar beschriebenen <br>
     * Funktionen ermoeglicht
     * 
     * @param args hier muss der prefix angegeben werden, mit dem sich der Client<br>
     *  verbinden soll.
     */
    public static void main(String args[]) {
        try {
            if (args.length == 1) {
                //Verbindung zum Client aufbauen
                Client c = new Client();
                c.connect(args[0]);
                boolean stopped = false;

                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                //auf Benutzereingaben warten, bis gestoppt wird
                while (!stopped) {
                    try {                        
                        System.out.println();
                        System.out.println("Please enter a command:");
                        String line = br.readLine();
                        String[] input = (line).split(" ");

                        //if login, log in
                        if (input.length == 3 && input[0].equalsIgnoreCase("login")) {
                            c.login(input[1], input[2]);
                        //if logout log out
                        } else if (input.length == 1 && input[0].equalsIgnoreCase("logout")) {
                            c.logout();
                        //if sendsms, send the message
                        } else if (input.length >= 3 && input[0].equalsIgnoreCase("sendSms")) {
                            boolean messageFinished = false;
                            Message message = new Message();

                            //getting text between paragraphs
                            message.text = input[1].replaceFirst("\"", "") + " ";
                            int i;
                            if (message.text.contains("\"") && !line.replaceFirst("\"", "").replaceFirst("\"", "").contains("\"")) {
                                message.text = message.text.replace("\"", "");
                                i = 2;
                            } else if (line.replaceFirst("\"", "").replaceFirst("\"", "").contains("\"")) {
                                throw new ConsoleParseException("Message contains more than two \"!");
                            } else {
                                for (i = 2; !messageFinished; i++) {
                                    if (i >= input.length) {
                                        throw new ConsoleParseException("Second \" missing?");
                                    } else {
                                        message.text += input[i] + " ";
                                        if (message.text.contains("\"")) {
                                            message.text = message.text.replace("\"", "");
                                            message.text = message.text.substring(0, message.text.length() - 1);
                                            messageFinished = true;
                                        }
                                    }

                                }
                            }
                            //Parse Recepients
                            TelNr[] nrs = new TelNr[input.length - i];
                            for (int j = 0; i < input.length; i++, j++) {
                                String[] nr = input[i].split("/");
                                nrs[j] = new TelNr(nr[0], nr[1]);
                            }
                            message.reciever = nrs;

                            //Send the message
                            message.time = System.currentTimeMillis();
                            c.sendSmsExplicit(message);
                        //if stop, stop the client
                        } else if (input.length == 1 && input[0].equalsIgnoreCase("stop")) {
                            stopped = true;
                            c.stop();
                        } else {
                            System.err.println("Please enter a valid command.");
                        }
                    } catch (IllegalArgumentException ex) {
                        System.err.println(ex.getMessage());
                    } catch (Exception ex) {
                        if (ex.getCause() == null) {
                            if(!ex.getMessage().equals("1")) {
                                System.err.println(ex.getMessage());
                            }
                            else {
                                System.err.println("Please enter a valid command.");
                            }
                        } else {
                            if(!ex.getCause().getMessage().equals("1")) {
                                System.err.println(ex.getCause().getMessage());
                            }
                            else {
                                System.err.println("Please enter a valid command.");
                            }
                        }
                    }
                }


            } else {
                System.out.println("No Parameter given, needing prefix to connect");
            }
        } catch (Error e) {
            System.err.println("Could not connect to Naming... Is server running?");
        }
    }
}