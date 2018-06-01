package test;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;


@Path( PeerService.webContextPath )
public class PeerServiceC
{
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
   
  /* @GET
   @Path("/routing")
   public Response routing(@QueryParam("x") double x, @QueryParam("y") double y)
   {
	   
       return Response;
               
   }*/
}