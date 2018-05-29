package can_network;
import java.io.Serializable;

/**
 * 
 */

/**
 * @author z002vb6m
 *
 */
public class User implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3153801662101748013L;
	//Variables
	private int id;
	private String name;
	private String password;
	//imageList?
	
	
	/**
	 * Konstruktor
	 */
	public User(int id, String name, String password) {
		this.id=id;
		setName(name);
		setPassword(password);
	}
	
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	
	public void setName(String name) {
		if (name.trim().isEmpty()) {
			throw new Exceptions.EmptyStringException();
		}
		this.name=name;
		
	}
	
	public void setPassword(String password) {
		if (password.trim().isEmpty()) {
			throw new Exceptions.EmptyStringException();
		}
		this.password=password;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getName()).append(getPassword());
		
		return sb.toString();
	}
	
}
