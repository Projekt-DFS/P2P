package can_network;
import java.util.*;
import java.net.*;
import java.io.*;

import java.io.IOException;
import java.net.URI;


import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author Thomas Spanier
 *
 */
public class Peer {
	
	private static final int port = 4434;
	// Aktuelle IP-Adresse des Servers
	private static final String ip_adresse = "10.9.41.197";
	
	
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
	/* 
	 *public static void main(String[] args) {
		
	
		try {
			Peer peer = new Peer();
			System.out.println("IP-Addresse: " + peer.ipAddress.getHostAddress() );
			
			
			InetAddress[] iad = InetAddress.getAllByName(InetAddress.getLocalHost().getHostName());
			for(InetAddress i : iad) {
				System.out.println(i);
			}

			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}*/
		

	   public static void main( String[] args ) throws IOException, InterruptedException 
	   {
	      String baseUrl = ( args.length > 0 ) ? args[0] : "http://"+ip_adresse+":"+port;
	     
	      final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
	            URI.create( baseUrl ), new ResourceConfig( PeerService.class ), false );
	      Runtime.getRuntime().addShutdownHook( new Thread( new Runnable() {
	         @Override
	         public void run() {
	            server.shutdownNow();
	         }
	      } ) );
	      server.start();
		
		  System.out.println( String.format( "\nGrizzly-HTTP-Server gestartet mit der URL: %s\n"
		                                     + "Stoppen des Grizzly-HTTP-Servers mit:      Strg+C\n",
		                                     baseUrl + PeerService.webContextPath ) );
		
		  Thread.currentThread().join();;
	}	
}


