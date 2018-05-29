package can_network;
/**
 * 
 */

/**
 * @author Thomas Spanier
 * @deprecated
 */
public class Coordinate {
	private double x;
	private double y;
	
	public Coordinate(double x, double y) {
		setX(x);
		setY(y);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		if (x > 0.0 && x < 10.0) {
			this.x = x;
		}
	}
	
	public void setY(double y) {
		if (y > 0.0 && y < 10.0) {
			this.y = y;
		}
	}
	
	
	public boolean equals(Object o) {
		if(!(o instanceof Coordinate)) {
			return false;
		}
		Coordinate c = (Coordinate) o;
		if(c.x == this.x && c.y == this.y) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("(").append(x).append(", ").append(y).append(")");
		return sb.toString();
	}
}
