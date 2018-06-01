package test;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class PeerA {
	//Variablen
	private static final int port = 4434;
	// Aktuelle IP-Adresse des Servers
	private static final String ip_adresse = "192.168.2.104";
	
	private static Zone ownZone;
	private static HashMap neighbours = new HashMap();
	private static HashMap <Long, Zone> coordinates = new HashMap <Long, Zone>();

   
    public static void createZone(Point2D.Double bottomLeft, Point2D.Double upperRight) {
      //  ownZone = new Zone();
        ownZone.setZone(bottomLeft, upperRight);
    }
	
    public static void splitZone(Peer newPeer) {
        if (ownZone.isSquare()) {
            newPeer.createZone(new Point2D.Double(ownZone.calculateCentrePoint().getX(), ownZone.getBottomRight().getY()), ownZone.getUpperRight());
            ownZone.setZone(ownZone.getBottomLeft(), new Point2D.Double(ownZone.calculateCentrePoint().getX(), ownZone.getUpperLeft().getY()));    
        } else {
            newPeer.createZone(ownZone.getBottomLeft(), (new Point2D.Double(ownZone.getBottomRight().getX(), ownZone.calculateCentrePoint().getY())));
            ownZone.setZone(new Point2D.Double(ownZone.getUpperLeft().getX(), ownZone.calculateCentrePoint().getY()), ownZone.getUpperRight());    
        }
    }

	
	//Constructor
	public PeerA() {
			
		
	}
	public static String checkZone (double x, double y) {
	//	if(tmpZone.bottomLeft.getX() >= ownZone.bottomLeft.getX() && tmpZone.bottomRight.getX() <= ownZone.bottomRight.getX() && tmpZone.bottomRight.getY() >= ownZone.bottomRight.getY() && tmpZone.upperRight.getY() <= ownZone.upperRight.getY()) {
	
 		if(x >= ownZone.bottomLeft.getX() && x <= ownZone.bottomRight.getX() && y >= ownZone.bottomRight.getY() && y <= ownZone.upperRight.getY()) {
		 return "hjhjh";
		}
		else
		{
			for(Map.Entry<Long, Zone> entry : coordinates.entrySet()) {
				if(x >= entry.getValue().bottomLeft.getX() && x <= entry.getValue().bottomRight.getX()) {
					
				        
			     
			      String webContextPath = "/routing";
			      String baseUrl        = "http://"+ip_adresse+":"+port;
			      
			    //  System.out.println( "\nAngefragte URL: " + baseUrl + webContextPath + "?x=" + x + "?y=" + y );

			      Client c = ClientBuilder.newClient();
			      WebTarget target = c.target( baseUrl );
			      
			      System.out.println( "\nTextausgabe:" );
			      System.out.println( target.path( webContextPath ).queryParam( "y", y ).request( MediaType.TEXT_PLAIN ).get( String.class ) );
			      System.out.println( "\nHTML-Ausgabe:" );
			      System.out.println( target.path( webContextPath ).queryParam( "x", x ).request( MediaType.TEXT_HTML ).get( String.class ) );
			      
				}
				
			}
		}
		return "foo";
	}
	
	public static void createNeighbours() {
		neighbours.put(new Long(ipToLong("192.168.2.110")), ipToLong(ip_adresse));
		neighbours.put(new Long(ipToLong("192.168.2.111")), ipToLong(ip_adresse));
		
	}
	
	public static void createCoordinates(Long key, Zone zone) {
		coordinates.put(key, zone);
	}
	
	public static long ipToLong(String ipAddress) {

		// ipAddressInArray[0] = 192
		String[] ipAddressInArray = ipAddress.split("\\.");

		long result = 0;
		for (int i = 0; i < ipAddressInArray.length; i++) {

			int power = 3 - i;
			int ip = Integer.parseInt(ipAddressInArray[i]);

			// 1. 192 * 256^3
			// 2. 168 * 256^2
			// 3. 1 * 256^1
			// 4. 2 * 256^0
			result += ip * Math.pow(256, power);

		}

		return result;

	}
		
	
		public static String longToIp(long i) {

			return ((i >> 24) & 0xFF) + 
	                   "." + ((i >> 16) & 0xFF) + 
	                   "." + ((i >> 8) & 0xFF) + 
	                   "." + (i & 0xFF);

		}
		
	    public static String toStringZone() {
	        return ownZone.toString();
	    }
	    
	    public static boolean hasSquareZone() {
	        return ownZone.isSquare();
	    }
	
	  /* public void start() {
		   String baseUrl = "http://"+ip_adresse+":"+port;

		    System.out.println("Ende");
	      
	      final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
	            URI.create( baseUrl ), new ResourceConfig( PeerServiceA.class ), false );
	      Runtime.getRuntime().addShutdownHook( new Thread( new Runnable() {
	         @Override
	         public void run() {
	            server.shutdownNow();
	         }
	      } ) );

		  try {
			  server.start();
			  Thread.currentThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		};
		
		System.out.println( String.format( "\nGrizzly-HTTP-Server gestartet mit der URL: %s\n"
                + "Stoppen des Grizzly-HTTP-Servers mit:      Strg+C\n",
                baseUrl + PeerService.webContextPath ) );

		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	   }*/

	   public static void main( String[] args ) throws IOException, InterruptedException 
	   {
	      String baseUrl = ( args.length > 0 ) ? args[0] : "http://"+ip_adresse+":"+port;

	      
	      Zone zoneB = new Zone (new Point2D.Double(0.0, 0.5), new Point2D.Double(1.0, 0.0), new Point2D.Double(0.5, 0.5), new Point2D.Double(1.0, 0.5));
		  Zone zoneC = new Zone (new Point2D.Double(0.0, 0.5), new Point2D.Double(0.5, 0.5), new Point2D.Double(0.0, 1.0), new Point2D.Double(0.5, 1.0));  
		  PeerA peerA = new PeerA();
		  
		  peerA.createCoordinates(peerA.ipToLong("192.168.2.110"), zoneB);
		  peerA.createCoordinates(peerA.ipToLong("192.168.2.111"), zoneC);
		  
		  System.out.println("Ende");
	      
	      final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
	            URI.create( baseUrl ), new ResourceConfig( PeerServiceA.class ), false );
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
