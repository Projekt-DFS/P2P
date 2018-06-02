package test;
/**
 * 
 */

import java.awt.geom.Point2D;

public class Zone {
    public Point2D.Double bottomLeft, bottomRight, upperLeft, upperRight;
    
    public Zone() {
    
    }
    
    public Zone(Point2D.Double bottomLeft, Point2D.Double bottomRight, Point2D.Double upperLeft, Point2D.Double upperRight) {
    	this.bottomLeft = bottomLeft;
    	this.bottomRight = bottomRight;
    	this.upperLeft = upperLeft;
    	this.upperRight = upperRight;
    }
    
    public void setZone(Point2D.Double bottomLeft, Point2D.Double upperRight) {
        this.bottomLeft = bottomLeft;
        this.upperRight = upperRight;
        calculateRest();
    }
    
    public void calculateRest() {
        upperLeft = new Point2D.Double(bottomLeft.getX(), upperRight.getY());
        bottomRight = new Point2D.Double(upperRight.getX(), bottomLeft.getY());
    }
    
    public Point2D.Double calculateCentrePoint() {
        return new Point2D.Double((bottomRight.getX() - bottomLeft.getX()) / 2, (upperRight.getY() - bottomRight.getY()) / 2);
    }
    
    public Point2D.Double calculateCentrePoint(Zone tmpZone) {
        return new Point2D.Double((tmpZone.bottomRight.getX() - tmpZone.bottomLeft.getX()) / 2, (tmpZone.upperRight.getY() - tmpZone.bottomRight.getY()) / 2);
    }
    
    public double getHeight() {
        return upperLeft.getY() - bottomLeft.getY();
    }
    public double distanz(double x1, double y1, double x2, double y2){
    	return Point2D.distanceSq(x1, y1, x2, y2);
    }
    
    public double getWidth() {
        return bottomRight.getX() - bottomLeft.getX(); 
    }
    
    public double getZoneVolume() {
        return getHeight() * getWidth();
    }
    
    public boolean isSquare() {
        return getHeight() == getWidth();
    }
    
    public Point2D.Double getUpperLeft() {
        return upperLeft;
    }
    
    public Point2D.Double getBottomLeft() {
        return bottomLeft;
    }
    
    public Point2D.Double getBottomRight() {
        return bottomRight;
    }
    
    public Point2D.Double getUpperRight() {
        return upperRight;
    }
    
    public String toString() {
		return "bottomLeft: " + bottomLeft + " bottomRight: " + bottomRight + 
				           " upperLeft: " + upperLeft + " upperRight: " + upperRight;
	}
}

