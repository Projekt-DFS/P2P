/**
 * 
 */
package can_network;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Thomas Spanier
 *
 */
public class Dialog {

	//Messages
	private static final int BEENDEN			=	0;
	private static final int BOOTSTRAP_ANLEGEN	=	1;
	private static final int PEER_ANLEGEN		=	2;
	
	private static final String MSG_BOOTSTRAP_FUNCTIONS = 
            "1= Show users, 2= Create User, 3= Delete User, 0= END: ";	//TODO Bootstrap functions
	
	//private static final int USER_ANLEGEN		=	3;
	//private static final int USER_AUTHENTICATE	=	4;
	
	private static final String MSG_FUNKTION = "Bitte nur sinnvolle Eingaben machen!";
    
	//Variables
	private Bootstrap bt;
	private Peer peer;
	
	
	private Scanner input;
	
	/**
	 * Konstruktor
	 */
	public Dialog() {
		//TODO all the stuff
		input = new Scanner(System.in);
	}

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		new Dialog().start();
	}
	
	
	/**
     * Start des eigentlichen Programms
     * 
     * Es wird eine Schleife durchlaufen, bis der Anwender das Programm mit "Ende" beendet
     * 
     * Fehlerbehandlung bei AssertionError, InputMismatchException, NullpointerException und generellen Exceptions
     * mit entsprechender Fehlermeldung
     */
	private void start() {
		int funktion = -1;
        
        while (funktion != BEENDEN) {
            try {
                funktion = funktionEinlesen();
                funktionAusfuehren(funktion);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }catch (AssertionError e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                input.next();
                System.out.println(MSG_FUNKTION);
            } catch (NullPointerException e) {
                System.out.println("Bitte zuerst das Objekt anlegen!");
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
	}
	
	
	
	/** 
     * Zeigt die Menueleite an und wartet auf eine Eingabe
     * @return die auszufuehrende Funktion
     */
	private int funktionEinlesen(){
		
        //Ausgabe der Moeglichkeiten
        System.out.print(
            BOOTSTRAP_ANLEGEN					+ ": Create Bootstrap, " + 
            PEER_ANLEGEN						+ ": Add a Peer, " + 
            BEENDEN								+ ": End: ");
        return input.nextInt();

    }
	
	/**
     * Startet die jeweiligen Funktionen
     * @param funktion ist die vorher ausgewaehlte Funktion
     */
    private void funktionAusfuehren(int funktion) {
        int btFunction;
        //char lagerUeberschreiben;
        //String line;
        //lagerUeberschreiben=' ';
        switch(funktion) {
            case BEENDEN:          
                System.out.println("Programm Ende!");
                break;
            case BOOTSTRAP_ANLEGEN:
            	//TODO Create a new Bootstrap peer
            	
            	bt = new Bootstrap();
            	btFunction = -1;
            	while(btFunction != 0) {
	            	System.out.print(MSG_BOOTSTRAP_FUNCTIONS);
	                btFunction = input.nextInt();
	                createBootstrap(btFunction);
            	}
                break;
            case PEER_ANLEGEN:
            	//TODO Add a Peer to the network
            	break;
        }
    }
	
	private void createBootstrap(int btFunction) {
		final int SHOW_USERS	= 1;
		final int CREATE_USER 	= 2;
		final int DELETE_USER 	= 3;
		final int END			= 0;
		
		String name;
		String password;
		
		
		
		switch(btFunction) {
			case END:
				//TODO Nachfragen, ob man das Programm wirklich beenden will
				//System.out.println("Good Bye!");
				try {
					bt.exportUserList();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
			case SHOW_USERS:
				System.out.println(bt.getAllUsers());
				break;
				
			case CREATE_USER:
				System.out.print("Username:");
				input.nextLine();
				name = input.nextLine();
				
				System.out.print("Password: ");
				password = input.nextLine();
				bt.createUser(name, password);
				break;
				
			case DELETE_USER:
				System.out.print("Username:");
				input.nextLine();
				name = input.nextLine();
				bt.deleteUser(name);
				break;
		}
	
	}

}
