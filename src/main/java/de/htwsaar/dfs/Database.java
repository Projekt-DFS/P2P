package de.htwsaar.dfs;

import java.util.HashMap;
import java.util.Map;

import de.htwsaar.dfs.model.Image;
import de.htwsaar.dfs.model.Peer;
import de.htwsaar.dfs.model.User;

/**
 * Dummy Database 
 * @author Aude
 *
 */
public class Database {

	private static Map<Integer , User> users = new HashMap<>();
	private static Map<Long , Image> images = new HashMap<>();
	private static Map<Integer , Peer> peers = new HashMap<>();
	
	public static Map <Long , Image> getImages(){
		return images;
	}
	
	public static Map <Integer , User> getUsers(){
		return users;
	}
	
	public static Map <Integer , Peer> getPeers(){
		return peers;
	}
}

