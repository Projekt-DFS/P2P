package can_network;
import java.util.Calendar;

public class Metadata {

	String owner;
	Calendar datum = Calendar.getInstance();
	
	
	public Metadata() {
		// TODO Auto-generated constructor stub
	}
	
	public void set_owner(String owner) {
		this.owner = owner;
	}
	
	public void set_date(int day, int month, int year) {
		this.datum.set(year, month, day);
	}
	
	public Calendar get_datum() {
		return datum;
	}
	
	public String get_owner() {
		return owner;
	}
}
