package can_network;

import java.awt.geom.Point2D;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Bootstrap extends Peer {

	//Variables
	private LinkedList<User> userList;
	private int peerCount;									//wird bei jedem splitZone um 1 erhoeht, dient zur Bestimmung von id
	//IP-Adresse?
	//private Zone initialZone;
	
	/**
	 * Constructor
	 * If a userList is already present, this list will be deserialized and be used
	 * 
	 */
	public Bootstrap() {
		//TODO extend Peer Quatsch rein Save Images
		//Create or load UserList
		userList = new LinkedList<User>();
		try {
			importUserList();
		} catch (FileNotFoundException e){
				System.out.println("File not found");
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//Create a new Zone
		createZone(new Point2D.Double(0.0, 0.0), new Point2D.Double(1.0, 1.0));
		id = 0;					//Bootstrap has always ID 0
	}
	
	/**
	 * Creates a new User
	 * @param id identifier
	 * @param name of the new User
	 * @param password of the new User
	 * @return success or fail message
	 */
	//public String createUser(int id, String name, String password) {
	public String createUser(String name, String password) {
		can_network.User newUser;
		newUser = new can_network.User(name, password);
		for(User user : userList) {
			if(user.getName().equals(name)) {
				return ("User already exists");
			}
		}
		userList.add(newUser);
		return ("User has been added");
	}
	
	/**
	 * Deletes the User
	 * @param name of the deleting User
	 */
	public void deleteUser(String name) {
		// TODO Auto-generated method stub
		int i = 0;
		for(User user : userList) {
			if (user.getName().equals(name)) {
				userList.remove(i);
			}
			i++;
		}
	}
	
	
	/**
	 * Check, if Username and Password are correct
	 * @param name
	 * @param password
	 * @return true, if User & Password are correct, otherwise false
	 */
	public boolean authenticateUser(String name, String password) {
		for(User user : userList) {
			
			if(user.getName().equals(name) && user.getPassword().equals(password)) {
				return true;
			} 
		}
		return false;
	}
	
	/**
	 * 
	 * @return a List with all Users
	 */
	public String getAllUsers() {
		StringBuffer sb = new StringBuffer();
		for (User user : userList) {
			sb.append(user.toString()).append(" | ");
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @return how many Users are registered
	 */
	public int getUserCount() {
		int count = 0;
		for(@SuppressWarnings("unused") User user : userList) {
			count++;
		}
		return count;
	}
	
	/**
	 * Delete all Users
	 */
	public void dumpUsers() {
		userList.removeAll(userList);
	}
	
	
	/**
	 * Serialize the UserList in "userList.dat"
	 * @throws IOException
	 */
	public void exportUserList() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(
            new BufferedOutputStream(
            new FileOutputStream("userList.dat")));
        out.writeObject(userList);
        out.close();
    }
    
    
	/**
	 * Deserialize the UserList from "userList.dat"
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException if userList.dat does not exist
	 */
   @SuppressWarnings("unchecked")
   public void importUserList() throws IOException, ClassNotFoundException, FileNotFoundException {
        ObjectInputStream in;
        userList = new LinkedList<User>();
        in= new ObjectInputStream(
            new BufferedInputStream(
            new FileInputStream("userList.dat")));
        userList= (LinkedList<User>)in.readObject();
        in.close();
        
    }
	
	
	
	
	
	
	
	
	
	

}
