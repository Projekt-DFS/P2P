/**
 * 
 */
package can_network;

import static org.junit.Assert.*;
import java.io.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Thomas Spanier
 *
 */
public class UserTest {

	private Bootstrap bt;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		bt = new Bootstrap();
		bt.dumpUsers();
		bt.createUser(2, "Tommi", "TS");
		bt.createUser(3, "Thomas", "pw");
		
	}

	
	
	
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/*@Test
	public void test() {
		fail("Not yet implemented");
	}*/
	
	@Test
	public void testAuthenticate() {
		System.out.println(bt.getAllUsers());
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
		assertEquals(1, bt.getUserCount());
		
		bt.deleteUser("Thomi");
		assertEquals(1, bt.getUserCount());
	}
	
}
