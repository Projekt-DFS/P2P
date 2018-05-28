package can_network;
import java.io.Serializable;

/**
 * 
 */

/**
 * @author z002vb6m
 *
 */
@SuppressWarnings("serial")
public class User implements Serializable {

	
	//Variables
	private int id;
	private String name;
	private String password;
	
	
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
	
	
	
	
	//ToDo: Exception richtig machen
	public void setName(String name) {
		/* if(name ="") {
			throw new EmptyStringException;
		}*/
		this.name=name;
		
	}
	
	public void setPassword(String password) {
		this.password=password;
	}

}
