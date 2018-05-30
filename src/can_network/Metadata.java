package can_network;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

import can_network.User;

/**
 * 
 */


/**
 * @author Thomas Spanier
 * TODO serialVersionUID???
 *
 */
public class Metadata implements Serializable {

	private static final long serialVersionUID = 5637147796716729841L;
	//Variables
	private String photographer;
	private User user;
	private Date date;
	//private Ort location;
	private LinkedList<String> tagList;
	//TODO Location implementieren
	
	/**
	 * Constructor
	 * Sets metadata object
	 */
	public Metadata(String photographer, User user, Date date, LinkedList<String> tagList) {
		tagList = new LinkedList<String>();
		setPhotographer(photographer);
		setUser(user);
		setDate(date);
		setTagList(tagList);
	}

	
	
	// get-methods
	public String getPhotographer() {
		return photographer;
	}
	
	public User getUser() {
		return user;
	}
		
	public Date getDate() {
		return date;
	}
	
	public String getTagList () {
		return tagList.toString();
	}
	
	
	//set-methods
	public void setPhotographer(String photographer) {
		this.photographer = photographer;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public void setTagList(LinkedList<String> tagList) {
		this.tagList = tagList;
	}
	
	
	//Tag-editing-methods 
	/**
	 * adds a new Tag
	 * @param newtag
	 */
	public void addTag(String newtag) {
		//Deny empty tags
		if (newtag.trim().isEmpty()) {
			throw new Exceptions.EmptyStringException();
		}
		//If tag already present, skip
		for(String tag : tagList) {
			if(tag.equals(newtag)) {
				return;
			}
		}
		tagList.add(newtag);
	}
	
	/**
	 * Deletes a tag
	 * @param deletetag
	 */
	public void deleteTag(String deletetag) {
		//TODO Exception tag not found
		for(String tag : tagList) {
			if(tag.equals(deletetag)) {
				tagList.remove(tag);
				return;
			}
		}
	}
	
	/**
	 * Edits a tag
	 * @param oldTag
	 * @param newTag
	 */
	public void editTag(String oldTag, String newTag) {
		//Deny empty tags
		if (newTag.trim().isEmpty()) {
			throw new Exceptions.EmptyStringException();
		}
		//Search for tag
		for(String tag : tagList) {
			if(tag.equals(oldTag)) {
				tagList.remove(tag);
				tagList.add(newTag);
				return;
			}
		}
	}
	
}
