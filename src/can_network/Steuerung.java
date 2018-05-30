package can_network;

import java.awt.geom.Point2D;

public class Steuerung {
    public static void main(String[] args) {
        Peer bootstrap = new Peer();
        bootstrap.createZone(new Point2D.Double(0.0, 0.0), new Point2D.Double(10.0, 10.0));
        System.out.println("bootstrapZone quadratisch: " + bootstrap.hasSquareZone());
        System.out.println("BootstrapZone initial: " + bootstrap.toStringZone());
        System.out.println();
        
        Peer secondPeer = new Peer();
        bootstrap.splitZone(secondPeer);
        
        System.out.println("bootstrapZone erste Teilung quadratisch: " + bootstrap.hasSquareZone());
        System.out.println("bootstrapZone erste Teilung: " + bootstrap.toStringZone());
        System.out.println();
        
        System.out.println("secondPeerZone quadratisch: " + secondPeer.hasSquareZone());
        System.out.println("secondPeerZone: " + secondPeer.toStringZone());
        System.out.println();
        
        Peer thirdPeer = new Peer();
        bootstrap.splitZone(thirdPeer);
        
        System.out.println("bootstrapZone zweite Teilung quadratisch: " + bootstrap.hasSquareZone());
        System.out.println("bootstrapZone zweite Teilung: " + bootstrap.toStringZone());
        System.out.println();
        System.out.println("thirdPeerZone quadratisch: " + thirdPeer.hasSquareZone());
        System.out.println("thirdPeerZone: " + thirdPeer.toStringZone());
    }
}