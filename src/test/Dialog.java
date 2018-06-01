package test;

import java.awt.geom.Point2D;
import java.io.IOException;

public class Dialog {
	
	public void tmp() {
		System.out.println("Test-methode");
	}

	public static void main(String[] args) {

	      
		Zone zoneA = new Zone (new Point2D.Double(0.5, 0.0), new Point2D.Double(0.5, 0.0), new Point2D.Double(0.0, 0.5), new Point2D.Double(0.5, 0.5));
	    Zone zoneB = new Zone (new Point2D.Double(0.0, 0.5), new Point2D.Double(1.0, 0.0), new Point2D.Double(0.5, 0.5), new Point2D.Double(1.0, 0.5));
	    Zone zoneC = new Zone (new Point2D.Double(0.0, 0.5), new Point2D.Double(0.5, 0.5), new Point2D.Double(0.0, 1.0), new Point2D.Double(0.5, 1.0));
	    Zone zoneD = new Zone (new Point2D.Double(0.5, 0.5), new Point2D.Double(0.75, 0.5), new Point2D.Double(0.5, 1.0), new Point2D.Double(0.75, 1.0));
	    Zone zoneE = new Zone (new Point2D.Double(0.75, 0.5), new Point2D.Double(1.0, 0.5), new Point2D.Double(0.75, 1.0), new Point2D.Double(1.0, 1.0));
	    
	    PeerA peerA = new PeerA();
	    PeerB peerB = new PeerB();
	    PeerC peerC = new PeerC();
	    PeerD peerD = new PeerD();
	    PeerE peerE = new PeerE();
	    /*
	    try {
			PeerA.main(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	    /*
	    peerA.createCoordinates(zoneB, peerA.ipToLong("192.168.2.110"));
	    peerA.createCoordinates(zoneC, peerA.ipToLong("192.168.2.111"));
	    */
	    peerA.createCoordinates(peerA.ipToLong("192.168.2.110"), zoneB);
	    peerA.createCoordinates(peerA.ipToLong("192.168.2.111"), zoneC);
	    
	    peerB.createCoordinates(peerB.ipToLong("192.168.2.109"), zoneA);
	    peerB.createCoordinates(peerB.ipToLong("192.168.2.112"), zoneD);
	    peerB.createCoordinates(peerB.ipToLong("192.168.2.113"), zoneE);
	    
	    
	    peerC.createCoordinates(peerC.ipToLong("192.168.2.109"), zoneA);
	    peerC.createCoordinates(peerC.ipToLong("192.168.2.112"), zoneD);
	    /*
	    peerD.createCoordinates(zoneC, peerD.ipToLong("192.168.2.111"));
	    peerD.createCoordinates(zoneE, peerD.ipToLong("192.168.2.113"));
	    
	    peerE.createCoordinates(zoneB, peerE.ipToLong("192.168.2.110"));
	    peerE.createCoordinates(zoneD, peerE.ipToLong("192.168.2.112"));
	    */
	   // peerA.start();
	    System.out.println("Ende");
	}

}
