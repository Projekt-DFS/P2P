package can_network;

import java.awt.geom.Point2D;
import java.util.Date;
import java.util.LinkedList;
import java.io.*;
import java.awt.image.BufferedImage;



/**
 * @author Thomas Spanier
 *
 */
public class Image implements Serializable {
//TODO Metadaten in Image-Klasse speichern?
	/**
	 * 
	 */
	private static final long serialVersionUID = 4903375720434123881L;
	//Variables
	private BufferedImage img;
	private Point2D.Double canCoordinate;
	
	private Metadata meta;
	
	/**
	 * Constructor
	 * Sets image object including metadata
	 */
	public Image(BufferedImage img, Point2D.Double canCoordinate, 
			String photographer, User user, Date date, LinkedList<String> tagList) {
		
		meta = new Metadata(photographer, user, date, tagList);
		setImage(img);
		setCoordinate(canCoordinate);
	}

	// get-methods
	public BufferedImage getImage() {
		return img;
	}
	
	public Point2D.Double getCoordinate() {
		return canCoordinate;
	}
	
	
	
	
	//set-methods
	public void setImage(BufferedImage img) {
		this.img = img;
	}
	
	public void setCoordinate(Point2D.Double canCoordinate) {
		if (canCoordinate.x > 1.0 || canCoordinate.y > 1.0 || canCoordinate.x < 0.0 || canCoordinate.y < 0.0) {
			throw new IllegalArgumentException("Bad Coordinate");
		} else {
			this.canCoordinate= canCoordinate;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
