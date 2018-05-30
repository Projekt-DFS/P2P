package can_network;

import java.io.IOException;
import java.net.URI;


import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import java.awt.geom.Point2D;

/**
 * @author Thomas Spanier
 *
 */
public class Peer {
	
	
	
	//Variablen
	private Zone ownZone;
	private static final int port = 4434;
	// Aktuelle IP-Adresse des Servers
	private static final String ip_adresse = "127.0.0.1";
	
	
	
	
	//Constructor
	public Peer() {
		
	}
	
	

	   public static void main( String[] args ) throws IOException, InterruptedException {
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
	   
	   
	   
	   //Zone functions
	   public void createZone(Point2D.Double bottomLeft, Point2D.Double upperRight) {
	        ownZone = new Zone();
	        ownZone.setZone(bottomLeft, upperRight);
	    }
	    
	    public void splitZone(Peer newPeer) {
	        if (ownZone.isSquare()) {
	            newPeer.createZone(new Point2D.Double(ownZone.calculateCentrePoint().getX(), ownZone.getBottomRight().getY()), ownZone.getUpperRight());
	            ownZone.setZone(ownZone.getBottomLeft(), new Point2D.Double(ownZone.calculateCentrePoint().getX(), ownZone.getUpperLeft().getY()));    
	        } else {
	            newPeer.createZone(ownZone.getBottomLeft(), (new Point2D.Double(ownZone.getBottomRight().getX(), ownZone.calculateCentrePoint().getY())));
	            ownZone.setZone(new Point2D.Double(ownZone.getUpperLeft().getX(), ownZone.calculateCentrePoint().getY()), ownZone.getUpperRight());    
	        }
	    }
	    
	    public String toStringZone() {
	        return ownZone.toString();
	    }
	    
	    public boolean hasSquareZone() {
	        return ownZone.isSquare();
	    }
	   
	   
	   
}


