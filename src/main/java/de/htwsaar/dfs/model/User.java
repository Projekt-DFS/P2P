package de.htwsaar.dfs.model;
import java.io.Serializable;

import de.htwsaar.dfs.exceptions.EmptyStringException;

/**
 * 
 */

/**
 * @author Thomas Spanier
 *
 */
public class User implements Serializable {

	
	//TODO Serializable???
	private static final long serialVersionUID = -3153801662101748013L;
	//Variables
	private long id;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private String name;
	private String password;
	//imageList?
	
	
	/**
	 * Constructor
	 * @param id
	 * @param name
	 * @param password
	 */
	//public User(int id, String name, String password) {
	public User(long id, String name, String password) {
		//TODO id sinnvoll?
		this.id=id;
		setName(name);
		setPassword(password);
	}

	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	
	//set-methods
	
	
	
	public void setName(String name) {
		if (name.trim().isEmpty()) {
			throw new EmptyStringException();
		}
		this.name=name;
		
	}
	
	public void setPassword(String password) {
		if (password.trim().isEmpty()) {
			throw new EmptyStringException();
		}
		this.password=password;
	}

	/**
	 * ToString method
	 * @return Username and Password
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getName()).append(", ").append(getPassword());
		
		return sb.toString();
	}
	
}