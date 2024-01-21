package com.gamergeo.project.videomanager.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.gamergeo.lib.gamlib.model.Model;

@XmlRootElement(name=XMLStructure.VIDEO.ROOT)
@XmlType(propOrder = {XMLStructure.VIDEO.ID})
public class Video implements Serializable, Model {

	private static final long serialVersionUID = -2649188449485629485L;

//	@XmlAttribute(name = XMLStructure.VIDEO.ID)
//	protected Integer id;
	
	@XmlAttribute(name = XMLStructure.VIDEO.TITLE)
	protected String title;
	
	@XmlAttribute(name = XMLStructure.VIDEO.URL)
	protected String url;
	
//	@XmlAttribute(name = XMLStructure.VIDEO.LINK)
//	protected String link;

	@XmlAttribute(name = XMLStructure.VIDEO.TAGS)
	protected List<VideoTag> videoTags;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
//	public String getLink() {
//		return link;
//	}
//
//	public void setLink(String link) {
//		this.link = link;
//	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<VideoTag> getVideoTags() {
		return videoTags;
	}

	public void setVideoTags(List<VideoTag> videoTags) {
		this.videoTags = videoTags;
	}
	
}
