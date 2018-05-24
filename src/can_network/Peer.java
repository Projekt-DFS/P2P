package can_network;
import java.util.*;
import java.net.*;
import java.io.*;

/**
 * @author Thomas Spanier
 *
 */
public class Peer {

	//Variablen
	private InetAddress ipAddress;
	//private HashMap photos;
	
	
	
	
	//Constructor
	public Peer() throws UnknownHostException{
		
			ipAddress = java.net.Inet4Address.getLocalHost();

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Peer peer = new Peer();
			System.out.println("IP-Addresse: " + peer.ipAddress.getHostAddress() );
			
			
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
	}

}
