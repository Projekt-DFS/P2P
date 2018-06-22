package can_network;
import java.awt.geom.Point2D;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Zone {
    private Point2D.Double bottomLeft, bottomRight, upperLeft, upperRight, center;
    private Interval leftY, rightY, bottomX, upperX;
    
    public Zone() {
    
    }
    
    //Konstruktor
    
    public Zone(Point2D.Double bottomLeft, Point2D.Double bottomRight, Point2D.Double upperLeft, Point2D.Double upperRight) {
    	this.bottomLeft = bottomLeft;
    	this.bottomRight = bottomRight;
    	this.upperLeft = upperLeft;
    	this.upperRight = upperRight;
    	this.center = calculateCentrePoint();
    }
    
    public void setZone(Point2D.Double bottomLeft, Point2D.Double upperRight) {
        this.bottomLeft = bottomLeft;
        this.upperRight = upperRight;
        
        calculateRest();
        calculateAxis(bottomLeft, upperRight);
        
        this.center = calculateCentrePoint();
    }
    
    public void calculateRest() {
        upperLeft = new Point2D.Double(bottomLeft.getX(), upperRight.getY());
        bottomRight = new Point2D.Double(upperRight.getX(), bottomLeft.getY());
    }
    
    public Point2D.Double calculateCentrePoint() {
        return new Point2D.Double(((bottomRight.getX() - bottomLeft.getX()) / 2) + bottomLeft.getX(), ((upperRight.getY() - bottomRight.getY()) / 2) + bottomRight.getY());
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
    public Point2D.Double getCenter(){
    	return center;
    }
    
    /**
     * Calculate the distance between the middle of one zone to a another point
     * @param x1 x point of the middle of the zone
     * @param y1 y point of the middle of the zone
     * @param x2 x point of request point
     * @param y2 y point of request point
     * @return distance between the point
     */
    public double distanz(double x1, double y1, double x2, double y2){
    	return Point2D.distanceSq(x1, y1, x2, y2);
    }
    
    public String toString() {
		return "bottomLeft: " + bottomLeft + " bottomRight: " + bottomRight + 
				           " upperLeft: " + upperLeft + " upperRight: " + upperRight;
	}
    
    public void calculateAxis(Point2D.Double bottomLeft, Point2D.Double upperRight) {
    	leftY = new Interval();
    	leftY.setInterval(bottomLeft.getY(), upperRight.getY(), bottomLeft.getX());
    	
    	rightY = new Interval();
    	rightY.setInterval(bottomLeft.getY(), upperRight.getY(), upperRight.getX());
    	
    	upperX = new Interval();
    	upperX.setInterval(bottomLeft.getX(), upperRight.getX(), upperRight.getY());
    	
    	bottomX = new Interval();
    	bottomX.setInterval(bottomLeft.getX(), upperRight.getX(), bottomLeft.getY());
    	
    }
    
    public Interval getLeftY() {
    	return leftY;
    }
    
    public Interval getRightY() {
    	return rightY;
    }
    
    public Interval getUpperX() {
    	return upperX;
    }
    
    public Interval getBottomX() {
    	return bottomX;
    }
    @XmlRootElement
    public class Interval {
    	private double min;
    	private double max;
    	private double anchor;
    	
    	public Interval() {
    		
    	}
    	
    	public void setInterval(double min, double max, double anchor) {
    		if (min < max) {
    			this.min = min;
    			this.max = max;
    			this.anchor = anchor;
    		} else {
    			throw new IllegalArgumentException("Illegal interval");
    		}
    	}
    	
    	public double getMin() {
    		return min;
    	}
    	
    	public double getMax() {
    		return max;
    	}
    	
    	public boolean intersects(Interval interval) {
    		if (checkAnchor(interval.getAnchor()) == false) 
    			return false;
    	    if (this.max < interval.min)
    			return false;
    		if (this.min > interval.max)
    			return false;
    		if (this.min == interval.max)
    			return false;
    		if (this.max == interval.min)
    			return false;
    		return true;
    	}
    	
    	public boolean containsValue(double value) {
    		if ((min <= value) && (max >= value))
    			return true;
    		else
    			return false;
    	}
    	
    	public double getLength() {
    		return max - min;
    	}
    	
    	public boolean checkAnchor(double otherAnchor) {
    		return anchor == otherAnchor;
    	}
    	
    	public double getAnchor() {
    		return anchor;
    	}
    	
    	public String intervalToString() {
    		return "[" + min + ", " + max + "]";
    	}
    }
}
