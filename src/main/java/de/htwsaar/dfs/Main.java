package de.htwsaar.dfs;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import de.htwsaar.dfs.can_network.Bootstrap;
import de.htwsaar.dfs.model.Image;
import de.htwsaar.dfs.model.Metadata;
import de.htwsaar.dfs.model.Peer;
import de.htwsaar.dfs.model.User;
import de.htwsaar.dfs.model.Zone;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.TargetDataLine;




/**
 * Main Class
 * Starts the Server
 * @author Aude Nana
 *
 */
public class Main {
	
	static Bootstrap bootstrap = new Bootstrap();
	
	//dummy fuer peers
	public static Map<Integer, Peer> peers = Database.getPeers();
	
	/**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
	
    public static HttpServer startServer() throws UnknownHostException {
        // create a resource config that scans for JAX-RS resources and providers
        // in de.htwsaar.dfs.iosbootstrap package
        final ResourceConfig rc = new ResourceConfig().packages("de.htwsaar.dfs.resource");
        rc.register(MultiPartFeature.class);
        rc.register(LoggingFilter.class);
        rc.register(SecurityFilter.class);

               
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create("http://"+getIP()+":8080/iosbootstrap/v1/"), rc);
    }
    
    //just let full the database
    private static void putInDb() {
    	
    	//users
    	bootstrap.createUser("user", "user");
    	bootstrap.createUser("user2", "password");
		
    	//images
		BufferedImage img = null , img2 = null;
		try {
			img = ImageIO.read(new File("C:/Users\\Aude\\Desktop\\downloadTest\\bild1.png"));
			img2 = ImageIO.read(new File("C:/Users\\Aude\\Desktop\\downloadTest\\bild1.png"));
			LinkedList<String> tagList = new LinkedList<String>();
			bootstrap.createImage(img, "user", "Noname.jpg", "Berlin", tagList);
			bootstrap.createImage(img, "user2", "bildUser1.jpg", "Milan", tagList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//peers
				Zone zoneA = new Zone (new Point2D.Double(0.0, 0.0), new Point2D.Double(0.5, 0.0), new Point2D.Double(0.0, 0.5), new Point2D.Double(0.5, 0.5));
				Zone zoneB = new Zone (new Point2D.Double(0.5, 0.0), new Point2D.Double(1.0, 0.0), new Point2D.Double(0.5, 0.5), new Point2D.Double(1.0, 0.5));
				peers.put(1, new Peer(zoneA));
				peers.put(2, new Peer(zoneB));
	}
    
    /**
     * read the IP address automatically
     * @return
     * @throws UnknownHostException
     */
    static public String getIP() throws UnknownHostException {
    	return InetAddress.getLocalHost().getHostAddress();
    }
    
    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
    	putInDb();
        startServer();
        System.in.read();
      
    }
}

