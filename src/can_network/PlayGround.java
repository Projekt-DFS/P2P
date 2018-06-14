/**
 * 
 */
package can_network;
import java.awt.Image;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import javax.imageio.ImageIO;

/**
 * @author Thomas Spanier
 * 
 */
@SuppressWarnings("unused")
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
		//new PlayGround().startBootstrapTest();
		//new PlayGround().startUserTest();
		new PlayGround().startImageTest();
		//new PlayGround().startCanTest();
	}
	
	
	private void startCanTest() {
		bt = new Bootstrap();
		Peer p1 = new Peer(bt);
		System.out.println(p1.toStringZone());
	}
	
	
	
	//Image Playground
	private void startImageTest() {
		testSaveImage();
		testLoadImage();
	}
	
	private void testSaveImage() {
		ImageContainer ic;
		bt = new Bootstrap();
		BufferedImage img;
		try {
			
			img = ImageIO.read(new File("Classdiagramm.jpg"));
			String photographer = "Knecht";
			User owner = new User(1,"user1", "pw");
			Date date = new Date();
			LinkedList<String> tagList = new LinkedList<String>();
			bt.createImage(img, owner.getName(),"img_001", photographer, date, tagList);
			
			ArrayList<String> paths = bt.getPaths("user1");
			System.out.println(paths.size());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void testLoadImage() {
		ImageContainer ic;
		bt = new Bootstrap();
		Point2D.Double coordinate = new Point2D.Double(0.5, 0.8);
		User owner = new User(1,"user1", "pw");
		
		try {
			ic = bt.loadImageContainer(owner.getName(), "img_001");
			System.out.println(ic.getCoordinate());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
	
	//Peer & Bootstrap Playground
	private void startBootstrapTest() {
		Bootstrap booty = new Bootstrap();
		
	}
	
	
	
	
	//User Playground
	private void startUserTest() {
		testExport();
		testImport();
		testDelete();
	}
	
	private void testDelete() {
		bt = new Bootstrap();
		bt.createUser("0", "0");
		bt.createUser("1", "1");

		System.out.println(bt.getAllUsers());
		bt.deleteUser("0");
		System.out.println(bt.getAllUsers());
	}
	
	private void testExport() {
		bt = new Bootstrap();
		bt.createUser("0", "0");
		bt.createUser("1", "1");
		
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
