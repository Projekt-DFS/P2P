package test;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class PeerServer {
	
	static Zone zoneA = new Zone (new Point2D.Double(0.0, 0.0), new Point2D.Double(0.5, 0.0), new Point2D.Double(0.0, 0.5), new Point2D.Double(0.5, 0.5));
	static Zone zoneB = new Zone (new Point2D.Double(0.5, 0.0), new Point2D.Double(1.0, 0.0), new Point2D.Double(0.5, 0.5), new Point2D.Double(1.0, 0.5));
    static Zone zoneC = new Zone (new Point2D.Double(0.0, 0.5), new Point2D.Double(0.5, 0.5), new Point2D.Double(0.0, 1.0), new Point2D.Double(0.5, 1.0)); 
	
    public static PeerA testPeer = new PeerA(zoneA);
	
	public static void main(String [] args ) throws IOException, InterruptedException 
	   {
		 
		
	   // PeerServiceA tmpPs = new PeerServiceA(zoneA);	    
		testPeer.createCoordinates(testPeer.ipToLong("192.168.2.110"), zoneB);
		testPeer.createCoordinates(testPeer.ipToLong("192.168.2.111"), zoneC);
	    
		String baseUrl ="http://192.168.2.109:4434";
	     // final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create( baseUrl ), new ResourceConfig( PeerServiceA.class ), tmpPeer);
	    final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create( baseUrl ), new ResourceConfig( PeerServiceA.class ), false );
		      Runtime.getRuntime().addShutdownHook( new Thread( new Runnable() {
		         @Override
		         public void run() {
		            server.shutdownNow();
		         }
		      } ) );
		      server.start();
		 
		  System.out.println( String.format( "\nGrizzly-HTTP-Server gestartet mit der URL: %s\n"
		                                     + "Stoppen des Grizzly-HTTP-Servers mit:      Strg+C\n",
		                                     baseUrl + PeerServiceA.webContextPath ) );
		
		  Thread.currentThread().run();
	   }

}
