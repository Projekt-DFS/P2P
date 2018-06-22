package test;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;



@Path( PeerServiceA.webContextPath )
public class PeerServiceA
{
   public PeerA tmpPeer;
  
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

  public PeerServiceA(Zone tmpZone) {
	   	tmpPeer = new PeerA(tmpZone); 
	 
  }
  public PeerServiceA() {
	  
  }
  public void start() {
	  
	
	
  }
}