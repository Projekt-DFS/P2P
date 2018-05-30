package can_network;

import java.awt.geom.Point2D;

public class Zone {
    private Point2D.Double bottomLeft, bottomRight, upperLeft, upperRight;
    
    public Zone() {
    
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
    
    public double getHeight() {
        return upperLeft.getY() - bottomLeft.getY();
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
