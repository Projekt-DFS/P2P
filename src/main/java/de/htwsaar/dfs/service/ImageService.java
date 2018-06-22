package de.htwsaar.dfs.service;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.htwsaar.dfs.can_network.Bootstrap;
import de.htwsaar.dfs.can_network.ImageContainer;
import de.htwsaar.dfs.model.Image;
import de.htwsaar.dfs.model.Metadata;
import de.htwsaar.dfs.utils.RestUtils;


public class ImageService {
	
	//URI for Image
	private String baseUri = "http://" + Bootstrap.getIP() + ":" + Bootstrap.port +"/iosbootstrap/v1/users/";
	
	public ImageService(){
//		bootstrap.createImage(img, bootstrap.hashToPoint("name", "Nana"), "AN",
//				user, new Date(), null);
	}
	
	
	/**
	 * This Method return a copy of all the images 
	 * that are actually in the database without metadata
	 * @return
	 */
	
	public List<Image> getAllImages( String username){
		List<Image> result = new ArrayList<>();
	
		ArrayList<String> list = Bootstrap.getPaths(username);
		for( String str : list) {
			Image img = new Image();
			img.setThumbnail( str+"_thumbnail.jpg");
			img.setMetaData(new Metadata(username, null, null, null));
			img.setImageSource(str+".jpg");
			result.add(img);
		}
		return result; 
	}

	
	/**
	 * This method returns a special image as object
	 * @param username
	 * @param imageName
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */
	public Image getImage(String username , String imageName)  {
		ImageContainer ic = null;
		try {
			ic = Bootstrap.loadImageContainer(username, imageName);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		Image img = new Image( ic.getImageName().toString(), 
				new Metadata(username, null, null, null),
				baseUri + username +"/"+ ic.getPath(), 
				baseUri + username +"/"+ ic.getThumbnailPath());
		
		return img;
	}
	
	
	public Image addImage(String username, Image image) {
		Bootstrap.createImage(RestUtils.decodeToImage(image.getImageSource()),
				username, image.getImageName(), image.getMetaData().getLocation(),
				image.getMetaData().getTagList());
		return image;
	}
	
	public Image updateImage(String username, String imageName, Image image) {
		//pruefen ob image existiert
		return addImage(username, image);
	}
	
	public void removeImage(String username, String imageName) {
		 //Bootstrap.remove(username, imageName);
	}

	public Metadata getMetadata(String username, String imageName) 
			throws FileNotFoundException, ClassNotFoundException, IOException {
		ImageContainer ic = Bootstrap.loadImageContainer(username, imageName);
		Metadata metadata = new Metadata(username, ic.getDate(), ic.getLocation(), ic.getTagList());
		return metadata;
	}

	public Metadata updateMetadata(String username, String imageName, Metadata metadata) {
		
		return null;
	}


	/**
	 * this method returns the Picture als BufferedImage
	 * @param username
	 * @param imagename
	 * @return
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws FileNotFoundException 
	 */
	public BufferedImage getBufferedImage(String username, String imageName) 
			throws FileNotFoundException, ClassNotFoundException, IOException {
		ImageContainer ic = Bootstrap.loadImageContainer(username, imageName);
		return ic.getImage();
	}


	
}
