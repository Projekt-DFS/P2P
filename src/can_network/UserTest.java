/**
 * 
 */
package can_network;

import static org.junit.Assert.*;
import java.io.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Thomas Spanier
 * JUNIT Test for Users
 *
 */
public class UserTest {

	private Bootstrap bt;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		bt = new Bootstrap();
		bt.dumpUsers();
		bt.createUser("Tommi", "TS");
		bt.createUser("Thomas", "pw");
		
	}

	
	@Test
	public void testAuthenticate() {
		assertEquals(1, bt.getUser("Thomas").getID());
		assertEquals(true, bt.authenticateUser("Tommi", "TS"));
		assertEquals(false, bt.authenticateUser("Thomas", "Pw"));
		assertEquals(true, bt.authenticateUser("Thomas", "pw"));
	}

	@Test
	public void testSerialize() throws IOException, ClassNotFoundException{
		bt.exportUserList();
		
		Bootstrap newBT = new Bootstrap();
		newBT.importUserList();
		assertEquals(true, newBT.authenticateUser("Tommi", "TS"));
	}
	
	@Test
	public void testDelete() {
		bt.deleteUser("Thomas");
		System.out.println(bt.getUserCount());
		assertEquals(1, bt.getUserCount());
		
		bt.deleteUser("Tommi");
		assertEquals(0, bt.getUserCount());
	}
	
}
