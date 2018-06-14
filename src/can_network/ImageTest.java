package can_network;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ImageTest {

	Bootstrap bt;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		bt = new Bootstrap();
		
		BufferedImage img = ImageIO.read(new File("Classdiagramm.jpg"));
		String photographer = "Knecht";
		User owner = new User(1,"user1", "pw");
		Date date = new Date();
		LinkedList<String> tagList = new LinkedList<String>();
		tagList.add("Sommer2017");
		bt.createImage(img, owner.getName(),"img_001", photographer, date, tagList);
		
		img = ImageIO.read(new File("Test.jpg"));
		photographer = "Knecht";
		owner = new User(1,"Testuser2", "pw");
		date = new Date();
		tagList = new LinkedList<String>();
		tagList.add("Winter2018");
		bt.createImage(img, owner.getName(),"img_002", photographer, date, tagList);
		
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testImageList() {
		ArrayList<String> paths = bt.getPaths("user1");
		String ip = bt.getIP();
		System.out.println(paths.get(0).toString());
		assertEquals(1, paths.size());
		assertEquals("http://" + ip + "//images//user1|img_001", paths.get(0).toString());
		
		assertEquals(1, bt.getPaths("Testuser2").size());
	}

}
