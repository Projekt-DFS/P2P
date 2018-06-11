package can_network;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import javax.imageio.ImageIO;

public class Bootstrap extends Peer {

	//Variables
	private LinkedList<User> userList;
	//TODO ID von IP-Adresse ableiten
	//private Zone initialZone;

	/**
	 * Constructor
	 * If a userList is already present, this list will be deserialized and be used
	 * 
	 */
	public Bootstrap() {
		//TODO extend Peer Quatsch rein Save Images
		/*
		 * Eigener Ordner hash-funktion als name
		 * Peer sucht im ordner nach den image-objekten
		 * 
		 * BT - Create Image	--> Eher REST Problem?
		 * Peer - Save Image
		 * Peer - Load Image
		 * 
		 * 
		 */
		
		//Create or load UserList
		userList = new LinkedList<User>();
		try {
			importUserList();
		} catch (FileNotFoundException e){
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		//Create a new Zone
		createZone(new Point2D.Double(0.0, 0.0), new Point2D.Double(1.0, 1.0));
		id = 0;					//Bootstrap has always ID 0 TODO: Deprecated
	}

	/**
	 * Creates a new User
	 * @param id identifier
	 * @param name of the new User
	 * @param password of the new User
	 * @return success or fail message
	 */
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


	
	
	
	
	
	
	
	
	
	
	
	//Image functions iOS -> Bootstrap
	/**
	 * Creates an ImageContainer and sends it into the network
	 * @param ic the ImageContainer to be saved
	 * TODO: temporary
	 */
	public void createImageContainer(ImageContainer ic) {
		try {
			saveImageContainer(ic);						//TODO: temporary -> forward to peer
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates an ImageContainer and sends it into the network
	 * @param img the image to be saved
	 * @param canCoordinate the calculated coordinate in the network
	 * @param photographer the image's photographer 
	 * @param user the user who uploaded the image
	 * @param date the date when the image was shot
	 * @param tagList a list of tags
	 */
	public void createImageContainer(BufferedImage img, Point2D.Double canCoordinate,
			String photographer, User user, Date date, LinkedList<String> tagList) {
		//Koordinate jetzt erst berechnen?
		ImageContainer ic = new ImageContainer(img, canCoordinate, photographer, user, date, tagList);
		//TODO Weiterleiten an die peers
		try {
			saveImageContainer(ic);						//TODO: temporary (routing)
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}							

	}

	/**
	 * Edits the image's meta data
	 */
	public void editMeta() {
		//TODO implement
	}

	/**
	 * Sends the ImageContainer object
	 * @param ic
	 */
	public void sendImage(ImageContainer ic) {
		//TODO implement
	}
	
	/**
	 * Sends the ImageContainer's metadata
	 * @param ic
	 */
	public void sendMeta(ImageContainer ic) {
		//TODO implement
	}
	

	
	
	
	
	//Image functions P2P
	
	/**
	 * Saves an ImageContainer including the image and the thumbnail on the hdd
	 * @param ic the imageContainer to be saved
	 */
	public void saveImageContainer(ImageContainer ic) throws IOException {
		
		//Save imageContainer
		System.out.println(ic.getImgPath() + ".data");
		ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(
						new FileOutputStream(ic.getImgPath() + ".data")));
		out.writeObject(ic);
		out.close();
		
		//Save image
		File outputFile = new File(ic.getImgPath() + ".jpg");
		ImageIO.write(ic.getImage(), "jpg", outputFile);
		
		//Save thumbnail
		outputFile = new File(ic.getImgPath() + "_thumbnail.jpg");
		ImageIO.write(ic.getThumbnail(), "jpg", outputFile);	
	}
	
	/**
	 * Deserialize imageContainer  
	 * @param canCoordinate
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public ImageContainer loadImageContainer(User user, Point2D.Double canCoordinate) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		//Get location
		StringBuffer fileName = new StringBuffer();
		fileName.append("Images//").append(user.getName()).append("_")
				.append(utils.StaticFunctions.pointToString(canCoordinate));
		
		//Load image
		File inputFile = new File(fileName.toString() + ".jpg");
		BufferedImage img = ImageIO.read(inputFile);
		
		//Load imageContainer and set image and thumbnail 
		ImageContainer ic;
		ObjectInputStream in= new ObjectInputStream(
				new BufferedInputStream(
						new FileInputStream(fileName.toString() + ".data")));
		ic= (ImageContainer)in.readObject();
		ic.setImage(img);
		in.close();
		return ic;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Point2D.Double hashToPoint(String imageName, String userName) {
		final double multiplier = 1.0 / 2147483648.0;
		
		double x = imageName.hashCode() * multiplier;
		double y = userName.hashCode() * multiplier;
		
		Point2D.Double coordinatePoint = new Point2D.Double(x, y);
		
		return coordinatePoint;
	}

}
