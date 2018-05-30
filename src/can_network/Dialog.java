/**
 * 
 */
package can_network;

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
	private static final int USER_ANLEGEN		=	3;
	private static final int USER_AUTHENTICATE	=	4;
	
	
	
	
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
	
	
	private void start() {
		
	}
	
	
	
	
	
	

}
