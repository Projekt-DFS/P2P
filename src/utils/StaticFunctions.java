package utils;

import java.awt.geom.Point2D;
import java.awt.image.*;
import java.awt.*;

public class StaticFunctions {

	/**
	 * Creates a String out of the Coordinate. Form: X,Y
	 * @param coordinate the Coordinate to transform 
	 * @return the String
	 */
	public static String pointToString(Point2D.Double coordinate) {
		StringBuffer sb = new StringBuffer();
		sb.append(String.valueOf(coordinate.getX())).append(",").append(String.valueOf(coordinate.getY()));
		return sb.toString();
	}
	
	
	
	/**
	 * TODO implement
	 * @return
	 */
	public static Point2D.Double hashCoordinate() {
		return null;
	}
	
	
	/**
	 * Creates a BufferedImage out of an Image
	 * @param img the Image to Change
	 * @return the BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage)
	    {
	        return (BufferedImage) img;
	    }

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}
	
	
	
	
	
	
	
	
	
	
}
