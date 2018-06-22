package de.htwsaar.dfs.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is used to Parse ImageContainer zu Json with string
 * @author Aude Nana
 *
 */
@XmlRootElement
public class Image {

	private String imageName;
	private Metadata metaData;
	private String imageSource;
	private String thumbnail;
		
	//public Image() {}

	//constructor with values
	public Image(String imageName, 
			Metadata metadata, String imageSource, String thumbnailSource) {
		this.imageName = imageName;
		this.metaData = metadata;
		this.imageSource = imageSource;
		this.thumbnail = thumbnailSource;//createThumbnail(imageSource);
	}
	
	public Image() {
		//this.id= 0; this.imageName = ""; this.metaData = new Metadata(); this.imageSource = ""; this.thumbnail = "";
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Metadata getMetaData() {
		return metaData;
	}

	public void setMetaData(Metadata metadata) {
		this.metaData = metadata;
	}

	public String getImageSource() {
		return imageSource;
	}

	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnailSource) {
		this.thumbnail = thumbnailSource;
	}
		
}
