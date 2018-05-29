/**
 * 
 */
package can_network;
import java.io.*;
//import java.util.*;

/**
 * @author thoma
 *
 */
public class PlayGround {

	private Bootstrap bt;
	/**
	 * 
	 */
	public PlayGround() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new PlayGround().start();
		

	}
	
	
	private void start() {
		testExport();
		testImport();
		testDelete();
	}
	
	private void testDelete() {
		bt = new Bootstrap();
		bt.createUser(0, "0", "0");
		bt.createUser(1, "1", "1");

		System.out.println(bt.getAllUsers());
		bt.deleteUser("0");
		System.out.println(bt.getAllUsers());
	}
	
	private void testExport() {
		bt = new Bootstrap();
		bt.createUser(0, "0", "0");
		bt.createUser(1, "1", "1");
		
		try {
			bt.exportUserList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void testImport() {
		bt = new Bootstrap();
		
		try {
			bt.importUserList();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(bt.getAllUsers());
		System.out.println(bt.authenticateUser("0", "0"));
		System.out.println(bt.authenticateUser("1", "1"));
		
	}
	
}
