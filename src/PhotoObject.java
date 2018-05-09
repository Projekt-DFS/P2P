import java.util.Date;
import java.io.*;
import java.awt.image.BufferedImage;



/**
 * @author Thomas Spanier
 *
 */
@SuppressWarnings("serial")
public class PhotoObject implements Serializable {

	//Variables
	
	private User owner;
	private Date date; 
	//private Ort ort;
	private BufferedImage img;
	private Coordinate canCoordinate;
	
	/**
	 * 
	 */
	public PhotoObject() {
		// TODO Auto-generated constructor stub
	}

	
	// get-methods
	public User getOwner() {
		return owner;
	}
	
	public Date getDate() {
		return date;
	}
	
	public Coordinate getCoordinate() {
		return canCoordinate;
	}
	
	public BufferedImage getImage() {
		return img;
	}
	
	
	
	//set-methods
	public void setOwner(User user) {
		this.owner = user;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setImage(BufferedImage img) {
		this.img = img;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
