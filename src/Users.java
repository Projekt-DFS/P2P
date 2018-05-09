import java.util.concurrent.ConcurrentHashMap;
import java.io.Serializable;

/**
 * 
 */

/**
 * @author Thomas Spanier
 *
 */

@SuppressWarnings("serial")
public class Users implements Serializable {

	
	//Variables
	private ConcurrentHashMap<Integer, User> list;
	private User user1;
	
	/**
	 * 
	 */
	public Users() {
		
	}
	public static void main(String[] args) {
		new Users().start();
	}
	
	private void start() {
		list = new ConcurrentHashMap<>();
		user1 = new User(1, "Knecht", "pw");
		list.put(user1.getID(), user1);
		
	}
	
	
	/*
	 * Methoden:
	 * add		putIfAbsent(k,v)
	 * remove	remove(k)
	 * change	replace(k,v)
	 * search	
	 * getUser	get(key)
	 * toString
	 */

}
