package de.htwsaar.dfs.model;

import javax.xml.bind.annotation.XmlRootElement;


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

	//getters und setters fuer JSON
	public void setMin(double min) {
		this.min = min;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public void setAnchor(double anchor) {
		this.anchor = anchor;
	}
	

	public double getMin() {
		return min;
	}
	
	public double getMax() {
		return max;
	}
	
}
