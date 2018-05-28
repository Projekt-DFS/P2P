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
   @Path("/img")
   public Response downloadPdfFile()
   {
       StreamingOutput fileStream =  new StreamingOutput()
       {
           @Override
           public void write(java.io.OutputStream output) throws IOException, WebApplicationException
           {
               try
               {
                   java.nio.file.Path path = Paths.get("C:/Users/Memphis/Dropbox/IMG_0425.JPG");
                   byte[] data = Files.readAllBytes(path);
                   output.write(data);
                   output.flush();
               }
               catch (Exception e)
               {
                   throw new WebApplicationException("File Not Found !!");
               }
           }
       };
       return Response
               .ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
               .header("content-disposition","attachment; filename = myfile.JPG")
               .build();
   }
}

