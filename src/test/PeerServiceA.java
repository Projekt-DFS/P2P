package test;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import java.lang.*;
import java.lang.reflect.*;



@Path( PeerService.webContextPath )
public class PeerServiceA
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
   
  @GET
   @Path("/routing")
   public String routing(@QueryParam("x") double x, @QueryParam("y") double y)
   {
	  PeerA tmpPeer = new PeerA();
	  Class tmpClass = PeerA.class;
	  Method method;
	  method = null;
	  try {
	
			method = tmpClass.getMethod("checkZone",double.class, double.class);
		 
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchMethodException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
		  System.out.println(method.getName());
				  
	 
	  
	  String tmp ="";
	  
	  try {
		tmp =(String) method.invoke(tmpPeer, 1d,5d);
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalArgumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InvocationTargetException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
	  return tmp;
   }
}