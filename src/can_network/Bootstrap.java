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
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;



public class Bootstrap extends Peer {

	//Variables
	private ArrayList<User> userList;
	long userCount;
	private HashSet<String> imageList;				//Form: <username>|<imageName>
	//private Zone initialZone;
	
	/**
	 * HashMap: Speichert die Nachbarn vom Peer mit zugehöhrigen Zonen
	 * 
	 */
	private  HashMap <Long, Zone> coordinates = new HashMap <Long, Zone>();

	/**
	 * Constructor
	 * If a userList is already present, this list will be deserialized and be used
	 * 
	 */
	public Bootstrap() {
		//Create or load UserList
		userList = new ArrayList<User>();
		imageList = new HashSet<String>();
		try {
			loadUserCount();
			importUserList();
		} catch (FileNotFoundException e){
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			this.inet = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Create a new Zone
		createZone(new Point2D.Double(0.0, 0.0), new Point2D.Double(1.0, 1.0));
	}
	
	public Bootstrap(Zone tmpZone) {
		super(tmpZone);
	}
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public User getUser(long id) {
		//TODO unschön
		return userList.get((int) id);
	}
	
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	public User getUser(String username) {
		//TODO: what, if username does not exist?
		for(User user : userList) {
			if(user.getName() == username) {
				return user;
			}
		}
		
		throw new IllegalArgumentException("User does not exist");
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
		newUser = new can_network.User(userCount++, name, password);
		for(User user : userList) {
			if(user.getName().equals(name)) {
				return ("User already exists");
			}
		}
		userList.add(newUser);
		try {
			exportUserList();
			saveUserCount();
		} catch (Exception e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ("User has been added");
	}

	/**
	 * Deletes the User
	 * @param name of the deleting User
	 */
	public void deleteUser(String username) {
		User user = getUser(username);
		
		//TODO: getter auf user.imageList
		//////////ALT: 	ArrayList<String> paths = getPaths(username);
		//TODO: Routing zum Peer anhand der ArrayList
		//TODO: REMOVE FOLDER FROM PEER (PEER)
		
		userList.remove(user);
		
		try {
			exportUserList();
		} catch (IOException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
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
		userList = new ArrayList<User>();
		in= new ObjectInputStream(
				new BufferedInputStream(
						new FileInputStream("userList.dat")));
		userList= (ArrayList<User>)in.readObject();
		in.close();

	}
	
	/**
	 * Deserializes the UserCount
	 * @return
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private long loadUserCount() throws FileNotFoundException, ClassNotFoundException, IOException {
		ObjectInputStream in;
		in= new ObjectInputStream(
				new BufferedInputStream(
						new FileInputStream("userCount.dat")));
		long userCount= (long)in.readObject();
		
		in.close();
		return userCount;
	}
	
	/**
	 * Serializes the UserCount
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private void saveUserCount() throws FileNotFoundException, ClassNotFoundException, IOException {
		ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(
						new FileOutputStream("userCount.dat")));
		out.writeObject(userCount);
		out.close();
	}
	
	
	
	
	
	
	
	
	
	//Image functions iOS -> Bootstrap
	/**
	 * Creates an ImageContainer and sends it into the network
	 * @param img the image to be saved
	 * @param canCoordinate the calculated coordinate in the network
	 * @param photographer the image's photographer 
	 * @param user the user who uploaded the image
	 * @param date the date when the image was shot
	 * @param tagList a list of tags
	 */
	public void createImage(BufferedImage img, String username, String imageName, 
			String photographer, Date date, LinkedList<String> tagList) {
		
		String imageID = username + "|" + imageName;
		ImageContainer ic = new ImageContainer(img, username, imageName, photographer, date, tagList);
		imageList.add(imageID);
		//TODO Weiterleiten an die peers
		try {
			//forwardMessage().getIP()
			saveImageContainer(ic);						//TODO: temporary (routing)
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}							

	}

	/**
	 * TODO uses the imageList to search and filter all images in network
	 * @param username the image's owner
	 * @return a List with paths of all images from an user
	 */
	private ArrayList<String> getListOfImages(String username){
		//Creates a filtered List with all Images from the user
		List<String> paths = imageList.stream().
				filter(s -> s.startsWith(username+ "|")).collect(Collectors.toList());
		
		return (ArrayList<String>) paths;
	}
	
	/**
	 * returns a List with all paths to the images
	 * @param username
	 * @return
	 */
	public ArrayList<String> getPaths(String username) {
		String path;
		ArrayList<String> filteredList = getListOfImages(username);
		ArrayList<String> paths = new ArrayList<String>();
		//TODO forwarding to the peers
		for(String imageName : filteredList) {
			path = "http://" + getIP() + "//images//" + imageName;
			paths.add(path);
		}
		return paths;
	}
	
	
	
	/**
	 * returns the metadata of an image
	 * @param username
	 * @param fileName
	 */
	public void getMeta(String username, String fileName) {
		//TODO implement
		return;
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
		File folder = new File("images");
		
		if(!folder.exists()) {
			folder.mkdir();
		}
		
		File userFolder = new File("images//" + ic.getUsername());
		if(!userFolder.exists()) {
			userFolder.mkdir();
		}
		
		
		ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(
						new FileOutputStream(ic.getPath() + ".data")));
		out.writeObject(ic);
		out.close();
		
		//Save image
		File outputFile = new File(ic.getPath() + ".jpg");
		ImageIO.write(ic.getImage(), "jpg", outputFile);
		
		//Save thumbnail
		outputFile = new File(ic.getPath() + "_thumbnail.jpg");
		ImageIO.write(ic.getThumbnail(), "jpg", outputFile);	
	}
	
	
	/**
	 * Deserialize imageContainer  
	 * @param canCoordinate
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 */
	public ImageContainer loadImageContainer(String username, String imageName) throws FileNotFoundException, IOException, ClassNotFoundException {
		//TODO routing
		Point2D.Double coordinate = utils.StaticFunctions.hashToPoint(username, imageName);
		
		//Get location
		StringBuffer fileName = new StringBuffer();
		fileName.append("Images//").append(username).append(",")
				.append(imageName);
		
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
		
	/**
	 * Sends the ImageContainer object
	 * @param ic
	 */
	public void sendImageContainer(ImageContainer ic, Point2D.Double destinationCoordiante) {
		//TODO implement
	}
	
	/**
	 *  The sends coordinates Hashmap of the Bootstrap
	 * @return coordinates
	 */
	public HashMap<Long, Zone> getRoutingTbl () {
		return coordinates;
		
	}



}
