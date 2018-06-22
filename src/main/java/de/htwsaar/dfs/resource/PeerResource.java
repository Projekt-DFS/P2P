package de.htwsaar.dfs.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.htwsaar.dfs.model.Peer;
import de.htwsaar.dfs.model.Zone;
import de.htwsaar.dfs.service.PeerService;

/**
 * 
 * @author Aude Nana
 *
 */
@Path("/peers")
public class PeerResource {

	private PeerService ps = new PeerService();
	
	/**
	 * This method returns all neighbors of a peer
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Peer> getAllNeighbors(){
		return ps.getAllNeighbors();
	}
	
	/**
	 * This method allows to add a new peer in the neigbor list of a peer
	 * @param peer
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON )
	public Peer addPeer(Peer peer) {
		ps.addPeer(peer);
		return peer;
	}
	

	/**
	 * This method returns a special peer from the neighbors 
	 * @param pid
	 * @return
	 */
	@GET
	@Path("/{peerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Peer getPeer(@PathParam("peerId") int pid){
		return ps.getPeer(pid);
	}
	
	/**
	 * This method allows to update a peer in the neighbor list
	 * @param peer
	 * @return
	 */
	@PUT
	@Path("/{peerId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON )
	public Peer updatePeer(@PathParam("peerId") int pid, Peer peer) {
		ps.updatePeer(pid, peer);
		return peer;
	}
	
	/**
	 * This method removes a Peer from the neighbor list
	 * @param pid
	 * @return
	 */
	
	@DELETE
	@Path("/{peerId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePeer(@PathParam("peerId") int pid){
		 return ps.deletePeer(pid);
	}
	
	/**
	 * This method returns the neigbor list of a peer in the neighbour list
	 * @param pid
	 * @return
	 */
	@GET
	@Path("/{peerId}/neighbors")
	@Produces(MediaType.APPLICATION_JSON)
	//ist normalerweise unnötig
	public List<Peer> getPeerNeighbors(@PathParam("peerId") int pid){
		return ps.getPeerNeighbors(pid);
	}
	
	/**
	 * this method returns the own zone related the peer
	 * @param pid
	 * @return
	 */
	@GET
	@Path("/{peerId}/ownzone")
	@Produces(MediaType.APPLICATION_JSON)
	//unmoglich
	public Zone getOwnZone(@PathParam("peerId") int pid){
		return ps.getOwnZone(pid);
	}
	
	/**
	 * this method allows to update the own zone of the peer
	 * @param pid
	 * @param zone
	 * @return
	 */
	@PUT
	@Path("/{peerId}/ownzone")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON )
	//unmoeglich
	public Zone updateOwnZone(@PathParam("peerId") int pid, Zone zone) {
		ps.updateOwnZone(pid, zone);
		return zone;
	}
	
	@GET
	@Path("/{peerId}/images")
	@Produces(MediaType.APPLICATION_JSON)
	//unmoglich
	public ImageResource getImageResouce(){
		return new ImageResource();
	}
	

	
}
