
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class PlayGround {

	public PlayGround() {
		// TODO Auto-generated constructor stub
	}

	private User user1;
	

	public static void main(String[] args) {
		new PlayGround().start();
		
	}
	
	private void start() {
		serialisieren_User();
		deserialisieren_User();
	}
	
	private void serialisieren_User() {
		user1 = new User(1, "testUser", "pw");
		try {
			FileOutputStream fs = new FileOutputStream(user1.getID() + ".ser");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(user1);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void deserialisieren_User() {
		try {
			FileInputStream fs = new FileInputStream("1.ser");
			ObjectInputStream os = new ObjectInputStream(fs);
			user1 = (User)os.readObject();
			os.close();
			System.out.println(user1.getName());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}














