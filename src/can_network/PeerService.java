package can_network;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;



@Path( PeerService.webContextPath )
public class PeerService
{
	 public Peer tmpPeer;
	  
	   PeerServer tmpServer;
	   static final String webContextPath = "/start";
	  
	   @GET @Produces( MediaType.TEXT_PLAIN )
	   public String halloPlainText( @QueryParam("name") String name )
	   {
	      return "Plain-Text: Hallo " + name;
	   }

	   @GET @Produces( MediaType.TEXT_HTML )
	   public String halloHtml( @QueryParam("name") String name )
	   {
	      return "<html><title>HelloWorld</title><body><h2>Html: Hallo " + name + "</h2></body></html>";
	      
	   }
	   
	  @GET
	   @Path("/routing")  
	   public String routing(@QueryParam("x") double x, @QueryParam("y") double y)
	   {
		 //tmpPeer.checkZone(x, y);
		 return PeerServer.testPeer.checkZone(0.4d, 0.6d);	 
		  
	   }

	  public PeerService(Zone tmpZone) {
		   	tmpPeer = new Peer(tmpZone); 
		 
	  }
	  public PeerService() {
		  
	  }
	  public void start() {
		  
		
		
	  }
}

