/**
 * 
 */
package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.htwsaar.dfs.can_network.Bootstrap;
import de.htwsaar.dfs.model.Peer;

/**
 * @author Thomas Spanier
 * JUNIT Test for Peers
 *
 */
public class PeerTest {

	private Bootstrap bt;
	
	
	/**
	 * Creates the Bootstrap Peer
	 * 
	 */
	@Before
	public void setUp() {
		bt = new Bootstrap();
		
	}

	/**
	 * Adds a few new Peers and checks, if the Zones are square
	 */
	@Test
	public void testSplitZone() {
		assertEquals(true, bt.hasSquareZone());
		System.out.println(bt.toStringZone());
		Peer p1 = new Peer(bt);
		assertEquals(false, bt.hasSquareZone());
		assertEquals(false, p1.hasSquareZone());
		System.out.println(bt.toStringZone());
		Peer p2 = new Peer(bt);
		assertEquals(true, bt.hasSquareZone());
		assertEquals(false, p1.hasSquareZone());
		assertEquals(true, p2.hasSquareZone());
		System.out.println(bt.toStringZone());
	}

}
