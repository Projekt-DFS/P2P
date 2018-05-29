package can_network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Bootstrap {

	//Variables
	public LinkedList<User> userList;
	//IP-Adresse?
	private Zone initialZone;
	
	
	public Bootstrap() {
		//TODO if present, deserialize userList
		userList = new LinkedList<User>();
		try {
			importUserList();
		} catch (FileNotFoundException e){
				System.out.println("File not found");
		} catch (IOException | ClassNotFoundException e) {
			
		}
		
	}
	
	
	public String createUser(int id, String name, String password) {
		can_network.User newUser;
		newUser = new can_network.User(id, name, password);
		for(User user : userList) {
			if(user.getID() == id || user.getName().equals(name)) {
				return ("User already exists");
			}
		}
		userList.add(newUser);
		return ("User has been added");
	}
	
	
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
	 * 
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
	
	
	public String getAllUsers() {
		StringBuffer sb = new StringBuffer();
		for (User user : userList) {
			sb.append(user.getName()).append(", ").append(user.getPassword()).append(" | ");
		}
		return sb.toString();
	}
	
	public int getUserCount() {
		int count = 0;
		for(@SuppressWarnings("unused") User user : userList) {
			count++;
		}
		return count;
	}
	
	public void dumpUsers() {
		userList.removeAll(userList);
	}
	
	
	public void exportUserList() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(
            new BufferedOutputStream(
            new FileOutputStream("userList.dat")));
        out.writeObject(userList);
        out.close();
    }
    
    
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
