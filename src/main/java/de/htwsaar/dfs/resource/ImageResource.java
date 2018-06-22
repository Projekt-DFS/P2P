package de.htwsaar.dfs.resource;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.htwsaar.dfs.model.Image;
import de.htwsaar.dfs.model.Metadata;
import de.htwsaar.dfs.service.ImageService;
/**
 * 
 * @author Aude Nana
 *
 */
@Path("/")
public class ImageResource {

	private ImageService imageService = new ImageService();
	
	/**
	 * this method returns all images that are actually in the database
	 * @return
	 * */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//funktioniert nicht: getPaths in Bootstrap klappt nicht
	public List<Image> getListOfImages(@PathParam("username") String username){
		return imageService.getAllImages(username);
	}
	
	/**
	 * this method allows to add a picture in the database
	 * @param image
	 * @return
	 */
	//funktioniert
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON )
	public Image addImage(@PathParam("username") String username, Image image) {
		imageService.addImage(username, image);
		System.out.println(image.getImageSource());
		return image;
		
	}

	/**
	 * this method returns a special picture Object 
	 * @param id
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */
	//funktioniert
	@GET
	@Path("/{imageName}")
	@Produces(MediaType.APPLICATION_JSON)
	public Image getImageObject( @PathParam("username") String username, 
			@PathParam("imageName") String imageName) 
			throws FileNotFoundException, ClassNotFoundException, IOException {
		return imageService.getImage(username, imageName);
	}
	
	/**
	 * this method returns the Picture als BufferedImage
	 * @param username
	 * @param imageName
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	//funktioniert
	@GET
	@Path("/{username}/{imageName}")
	@Produces({ "image/png" , "image/jpg"})
	public BufferedImage getImage( @PathParam("username") String username, 
			@PathParam("imageName") String imageName) 
			throws FileNotFoundException, ClassNotFoundException, IOException {
		return imageService.getBufferedImage(username, imageName);
	}
	
	/**
	 * this method allows to update a picture in the database
	 * @param id
	 * @param image
	 * @return
	 */
	@PUT
	@Path("/{imagename}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON )
	//funktioniert zum teil es fehlt noch zu prufen ob das Bild schon existiert
	public Image updateImage(@PathParam("username") String username, 
			@PathParam("imageName") String imageName, Image image) {
		return imageService.updateImage(username,imageName, image);
	}
	
	/**
	 * this method deletes a picture in the database
	 * @param id
	 */
	@DELETE
	@Path("/{imageName}")
	@Produces({MediaType.APPLICATION_JSON })
	//funktioniert nicht : keine Delete methode in Bootstrap
	public void deleteImage(@PathParam("username") String username, 
			@PathParam("imageName") String imageName) {
		 imageService.removeImage(username, imageName);
	}
	
	
	/**
	 * This method returns the metadata of a picture
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */ 
	@GET
	@Path("/{imageName}/metadata")
	@Produces(MediaType.APPLICATION_JSON)
	//funktioniert nicht : unmöglich Metadata von ein ImageContainer zu lesen
	public Metadata getMetadata(@PathParam("username") String username, 
			@PathParam("imageName") String imageName) 
					throws FileNotFoundException, ClassNotFoundException, IOException {
		return imageService.getMetadata(username, imageName);
	}
	
	
	@PUT
	@Path("/{imagename}/metadata")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON })
	//funktioniert nicht : unmöglich Metadata von ein ImageContainer zu lesen
	public Metadata updateMetadata(@PathParam("username") String username, 
			@PathParam("imageName") String imageName, Metadata metadata ) {
		return imageService.updateMetadata(username, imageName, metadata);
	}
	
	
}
