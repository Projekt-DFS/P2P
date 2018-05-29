package can_network;

import java.util.Date;
import java.util.LinkedList;

import can_network.Coordinate;
import can_network.User;

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
	private Coordinate canCoordinate;
	
	private Metadata meta;
	
	/**
	 * Constructor
	 * Sets image object including metadata object
	 */
	public Image(BufferedImage img, Coordinate canCoordinate, 
			String photographer, User user, Date date, LinkedList<String> tagList) {
		
		meta = new Metadata(photographer, user, date, tagList);
		setImage(img);
		setCoordinate(canCoordinate);
	}

	// get-methods
	public BufferedImage getImage() {
		return img;
	}
	
	public Coordinate getCoordinate() {
		return canCoordinate;
	}
	
	
	
	
	//set-methods
	public void setImage(BufferedImage img) {
		this.img = img;
	}
	
	public void setCoordinate(Coordinate canCoordinate) {
		this.canCoordinate= canCoordinate;
	}
	
	
	
	
	
	
	
	
	
	
	
}
