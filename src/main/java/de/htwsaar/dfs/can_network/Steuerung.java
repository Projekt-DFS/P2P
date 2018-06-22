package de.htwsaar.dfs.can_network;

import java.awt.geom.Point2D;

import de.htwsaar.dfs.model.Peer;

public class Steuerung {
    public static void main(String[] args) {
        Peer bootstrap = new Peer();

        bootstrap.createZone(new Point2D.Double(0.0, 0.0), new Point2D.Double(1.0, 1.0));
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
        
        Peer fourthPeer = new Peer();
        bootstrap.splitZone(fourthPeer);
        
        System.out.println("bootstrapZone dritte Teilung quadratisch: " + bootstrap.hasSquareZone());
        System.out.println("bootstrapZone dritte Teilung: " + bootstrap.toStringZone());
        System.out.println();
        System.out.println("fourthPeerZone quadratisch: " + fourthPeer.hasSquareZone());
        System.out.println("fourthPeerZone: " + fourthPeer.toStringZone());
        
        Peer fivthPeer = new Peer();
        bootstrap.splitZone(fivthPeer);
        
        System.out.println("bootstrapZone vierte Teilung quadratisch: " + bootstrap.hasSquareZone());
        System.out.println("bootstrapZone vierte Teilung: " + bootstrap.toStringZone());
        System.out.println();
        System.out.println("fivthPeerZone quadratisch: " + fivthPeer.hasSquareZone());
        System.out.println("fivthPeerZone: " + fivthPeer.toStringZone());
        

        bootstrap.createZone(new Point2D.Double(0.0, 0.0), new Point2D.Double(10.0, 10.0));
        System.out.println("bootstrapZone quadratisch: " + bootstrap.hasSquareZone());
        System.out.println("BootstrapZone initial: " + bootstrap.toStringZone());
        System.out.println();
   
    }
}