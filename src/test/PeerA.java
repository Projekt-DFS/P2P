package test;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
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

import com.sun.xml.internal.bind.v2.runtime.Coordinator;

public class PeerA {
	//Variablen
	private  static final int port = 4434;
	// Aktuelle IP-Adresse des Servers
	private  static final String ip_adresse = "192.168.2.109";
	
	private  Zone ownZone;
	private  HashMap neighbours = new HashMap();
	private  HashMap <Long, Zone> coordinates = new HashMap <Long, Zone>();

   
	public void setZone(Zone tmpZone) {
		this.ownZone = tmpZone;
	}
    public  void createZone(Point2D.Double bottomLeft, Point2D.Double upperRight) {
      //  ownZone = new Zone();
        ownZone.setZone(bottomLeft, upperRight);
    }
	
    public  void splitZone(PeerA newPeer) {
        if (ownZone.isSquare()) {
            newPeer.createZone(new Point2D.Double(ownZone.calculateCentrePoint().getX(), ownZone.getBottomRight().getY()), ownZone.getUpperRight());
            ownZone.setZone(ownZone.getBottomLeft(), new Point2D.Double(ownZone.calculateCentrePoint().getX(), ownZone.getUpperLeft().getY()));    
        } else {
            newPeer.createZone(ownZone.getBottomLeft(), (new Point2D.Double(ownZone.getBottomRight().getX(), ownZone.calculateCentrePoint().getY())));
            ownZone.setZone(new Point2D.Double(ownZone.getUpperLeft().getX(), ownZone.calculateCentrePoint().getY()), ownZone.getUpperRight());    
        }
    }

	
	//Constructor
	public PeerA(Zone tmpZone) {
			this.ownZone = tmpZone;
			
		
	}
	
	/**
	 * 
	 * @param x ist die Richtungskomponente in x-Richtung (nach rechts ;) ) 	 
	 * @param y ist die Richtungskomponente in y-Richtung 
	 * @return Ip-Adresse des zuständigen Peers
	 */
	public  String checkZone (double x, double y) {
	//	if(tmpZone.bottomLeft.getX() >= ownZone.bottomLeft.getX() && tmpZone.bottomRight.getX() <= ownZone.bottomRight.getX() && tmpZone.bottomRight.getY() >= ownZone.bottomRight.getY() && tmpZone.upperRight.getY() <= ownZone.upperRight.getY()) {
		
		String ausgabe_ip ="";

 		
		if(x >= ownZone.bottomLeft.getX() && x <= ownZone.bottomRight.getX() && y >= ownZone.bottomRight.getY() && y <= ownZone.upperRight.getY()) {
		 return ip_adresse;
		}
		else
		{
			
			for(Map.Entry<Long, Zone> entry : coordinates.entrySet()) {
				
				if(x >= entry.getValue().bottomLeft.getX() && x <= entry.getValue().bottomRight.getX()) {
	
			      String webContextPath = "routing";
			      String baseUrl ="http://"+ longToIp(entry.getKey())+":4434/start/";
			     // String baseUrl        = "http://"+ip_adresse+":"+port;
			      
			      System.out.println( "\nAngefragte URL: " + baseUrl + webContextPath + "?x=" + x + "&y=" + y );

			      Client c = ClientBuilder.newClient();
			      WebTarget  target = c.target( baseUrl );
			      
			      System.out.println( "\nTextausgabe:" );
			      
			  
			      ausgabe_ip = (target.path(webContextPath).queryParam("x",x).queryParam("y", y).request( MediaType.TEXT_PLAIN ).get( String.class ));
			      System.out.println( target.path( webContextPath ));
			      
				}
				
			}
		}
		return ausgabe_ip;
	}
	
	public void createNeighbours() {
		neighbours.put(new Long(ipToLong("192.168.2.110")), ipToLong(ip_adresse));
		neighbours.put(new Long(ipToLong("192.168.2.111")), ipToLong(ip_adresse));
		
	}
	
	public void createCoordinates(Long key, Zone zone) {
		coordinates.put(key, zone);
	}
	
	/**
	 * Wandelt eine übergeben IP-Adresse vom String zu long um
	 * @param ipAddress IP-Adresse als String
	 * @return IP-Adresse long
	 */
	public long ipToLong(String ipAddress) {

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
		
	/**
	 * Wandelt long zu String um
	 * @param IP-Adresse als long
	 * @return IP-Adresse als String
	 */
		public String longToIp(long i) {

			return ((i >> 24) & 0xFF) + 
	                   "." + ((i >> 16) & 0xFF) + 
	                   "." + ((i >> 8) & 0xFF) + 
	                   "." + (i & 0xFF);

		}
		
	    public String toStringZone() {
	        return ownZone.toString();
	    }
	    
	    public boolean hasSquareZone() {
	        return ownZone.isSquare();
	    }
	
	
}
