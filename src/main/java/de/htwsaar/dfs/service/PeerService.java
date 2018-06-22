package de.htwsaar.dfs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.htwsaar.dfs.Database;
import de.htwsaar.dfs.model.Peer;
import de.htwsaar.dfs.model.Zone;

public class PeerService {
	
	public static Map<Integer, Peer> neighbors = Database.getPeers();

	public List<Peer> getAllNeighbors() {
		//Peer.getNeighbors();
		return new ArrayList<>(neighbors.values());
	}

	public Peer getPeer(int pid) {
		return neighbors.get(pid);
	}

	public Peer addPeer(Peer newPeer) {
		neighbors.put(neighbors.size() + 1, newPeer);
		return newPeer;
	}

	public Peer updatePeer(int pid, Peer peer) {
		if( !neighbors.containsKey(pid))
			return null;
		neighbors.replace(pid, peer);
		return peer;
	}

	public String deletePeer(int pid) {
		if( !neighbors.containsKey(pid))
			return "Peer not found ";
		neighbors.remove(pid);
		return "Peer successfully removed!";
	}

	public List<Peer> getPeerNeighbors(int pid) {
		return new ArrayList<Peer> (neighbors.get(pid).getNeighbours().values());
	}

	public Zone getOwnZone(int pid) {
		//return Peer.getOwnZone();
		return null;
	}

	public Zone updateOwnZone(int pid, Zone zone) {
		//Peer.setZone(zone);
		return zone;
	}

}
