package can_network;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class PeerServer {
	
	/*
	 * 5 statische Zone die auf 5 Peers verteilt werden können
	 */
	
	static Zone zoneA = new Zone (new Point2D.Double(0.0, 0.0), new Point2D.Double(0.5, 0.0), new Point2D.Double(0.0, 0.5), new Point2D.Double(0.5, 0.5));
	static Zone zoneB = new Zone (new Point2D.Double(0.5, 0.0), new Point2D.Double(1.0, 0.0), new Point2D.Double(0.5, 0.5), new Point2D.Double(1.0, 0.5));
    static Zone zoneC = new Zone (new Point2D.Double(0.0, 0.5), new Point2D.Double(0.5, 0.5), new Point2D.Double(0.0, 1.0), new Point2D.Double(0.5, 1.0)); 
	static Zone zoneD = new Zone (new Point2D.Double(0.5, 0.5), new Point2D.Double(0.75, 0.5), new Point2D.Double(0.5, 1.0), new Point2D.Double(0.75, 1.0));
	static Zone zoneE = new Zone (new Point2D.Double(0.75, 0.5), new Point2D.Double(1.0, 0.5), new Point2D.Double(0.75, 1.0), new Point2D.Double(1.0, 1.0));
	
    /*
     * initialisier der Klasse Peer
     * übergabe der eigenen Zone
     */
	public static Peer testPeer = new Peer(zoneA);
	
	public static void main(String [] args ) throws IOException, InterruptedException 
	   {
		 
		
	   /*
	    * Es werden die Nachbar-Peers mit ihren verantwörtlichen Zonen in eine Hash-Map gespeichert
	    * 
	    * Muss für jeden Peer neu angepasst werden
	    * z.B 2.Peer 
	    * testPeer.createCoordinates(testPeer.ipToLong("192.168.2.109"), zoneA);
		  testPeer.createCoordinates(testPeer.ipToLong("192.168.2.112"), zoneD);
		  testPeer.createCoordinates(testPeer.ipToLong("192.168.2.113"), zoneE);
	    *    
	    */
		testPeer.createCoordinates(testPeer.ipToLong("192.168.2.110"), zoneB);
		testPeer.createCoordinates(testPeer.ipToLong("192.168.2.111"), zoneC);
	    
		String baseUrl ="http://"+ testPeer.getIP()+":"+Peer.port;
	     // final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create( baseUrl ), new ResourceConfig( PeerServiceA.class ), tmpPeer);
	    final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create( baseUrl ), new ResourceConfig( PeerService.class ), false );
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
		
		  Thread.currentThread().run();
	   }

}
