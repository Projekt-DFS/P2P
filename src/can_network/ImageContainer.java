package can_network;

import java.awt.geom.Point2D;
import java.util.Date;
import java.util.LinkedList;

import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.Image;

/**
 * @author Thomas Spanier
 *
 */
public class ImageContainer implements Serializable {

	private static final long serialVersionUID = 4903375720434123881L;	//TODO was ist das?
	//Liste mit keys von Bildern
	
	//Variables
	transient private BufferedImage img;
	transient private BufferedImage thumbnail;
	private String fileName;
	private String imgPath;
	private String thumbnailPath;
	private Point2D.Double canCoordinate;
	
	
	//Meta-Data
	private String photographer;
	private User user;
	private Date date;
	//private Ort location;
	private LinkedList<String> tagList;
	//TODO Location implementieren

	
	
	/**
	 * Constructor
	 * Sets image object
	 */
	public ImageContainer(BufferedImage img, Point2D.Double canCoordinate,
			String photographer, User user, Date date, LinkedList<String> tagList) {
		
		setImage(img);
		setFileName(fileName);
		setCoordinate(canCoordinate);
		
		tagList = new LinkedList<String>();
		setPhotographer(photographer);
		setUser(user);
		setDate(date);
		setTagList(tagList);
		setPath();
		
	}

	
	
	// get-methods
	public BufferedImage getImage() {
		return img;
	}
	
	public BufferedImage getThumbnail() {
		return thumbnail;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public Point2D.Double getCoordinate() {
		return canCoordinate;
	}
	
	public String getImgPath() {
		return imgPath;
	}
	
	public String getThumbnailPath() {
		return thumbnailPath;
	}
	
	// get-methods meta
	public String getPhotographer() {
		return photographer;
	}
	
	public User getUser() {
		return user;
	}
		
	public Date getDate() {
		return date;
	}
	
	public String getTagList () {
		return tagList.toString();
	}



	//set-methods
	public void setImage(BufferedImage img) {
		this.img = img;
		
		if(!img.equals(null) ) {
			createThumbnail(img);
		}
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setCoordinate(Point2D.Double canCoordinate) {
		if (canCoordinate.x > 1.0 || canCoordinate.y > 1.0 || canCoordinate.x < 0.0 || canCoordinate.y < 0.0) {
			throw new IllegalArgumentException("Bad Coordinate");
		} else {
			this.canCoordinate= canCoordinate;
		}
		
	}
	
	public void setPath() {
		StringBuffer fileName = new StringBuffer();
		fileName.append("Images//").append(user.getName()).append("_")
				.append(utils.StaticFunctions.pointToString(canCoordinate));
		this.imgPath = fileName.toString();// + ".jpg";
		this.thumbnailPath = fileName.toString() + "_thumbnail.jpg";
	}
	
	//set-methods meta
	public void setPhotographer(String photographer) {
		this.photographer = photographer;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setTagList(LinkedList<String> tagList) {
		this.tagList = tagList;
	}

	
	
	
	//Tag-editing-methods 
	/**
	 * adds a new Tag
	 * @param newtag
	 */
	public void addTag(String newtag) {
		//Deny empty tags
		if (newtag.trim().isEmpty()) {
			throw new Exceptions.EmptyStringException();
		}
		//If tag already present, skip
		for(String tag : tagList) {
			if(tag.equals(newtag)) {
				return;
			}
		}
		tagList.add(newtag);
	}
	
	/**
	 * Deletes a tag
	 * @param deletetag
	 */
	public void deleteTag(String deletetag) {
		//TODO Exception tag not found
		for(String tag : tagList) {
			if(tag.equals(deletetag)) {
				tagList.remove(tag);
				return;
			}
		}
	}
	
	/**
	 * Edits a tag
	 * @param oldTag
	 * @param newTag
	 */
	public void editTag(String oldTag, String newTag) {
		//Deny empty tags
		if (newTag.trim().isEmpty()) {
			throw new Exceptions.EmptyStringException();
		}
		//Search for tag
		for(String tag : tagList) {
			if(tag.equals(oldTag)) {
				tagList.remove(tag);
				tagList.add(newTag);
				return;
			}
		}
	}


	//Thumbnails
	/**
	 * creates a Thumbnail and saves it in this object
	 * @param img the original image
	 */
	private void createThumbnail(BufferedImage img) {
		Image temp = img.getScaledInstance(img.getWidth() / 10, img.getHeight() / 10, BufferedImage.SCALE_DEFAULT);
		thumbnail = utils.StaticFunctions.toBufferedImage(temp);
	}
	
	
}
