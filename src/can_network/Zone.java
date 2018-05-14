package can_network;
/**
 * 
 */

/**
 * @author Thomas Spanier
 *
 */
public class Zone {
	Coordinate start;
	Coordinate end;
	/**
	 * 
	 */
	public Zone(Coordinate start, Coordinate end) {
		this.start=start;
		this.end=end;
	}
	
	public Coordinate getStart() {
		return start;
	}
	
	public Coordinate getEnd() {
		return end;
	}
	
	public void setStart(Coordinate start) {
		this.start=start;
	}
	
	public void setEnd(Coordinate end) {
		this.end= end;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Zone)) {
			return false;
		}
		
		Zone zone = (Zone) o;
		if(zone.start.equals(this.start) && zone.end.equals(this.end)) {
			return true;
		} else {
			return false;
		}
	}
	
	public String toString() {
		StringBuffer sb= new StringBuffer();
		sb.append("[").append(start.toString()).append(" | ").append(end.toString()).append("]");
		return sb.toString();
	}
	
	
}
