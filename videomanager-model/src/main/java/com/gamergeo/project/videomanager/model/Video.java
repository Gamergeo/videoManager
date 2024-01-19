package com.gamergeo.project.videomanager.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name=XMLStructure.VIDEO.ROOT)
@XmlType(propOrder = {XMLStructure.VIDEO.ID})
public class Video implements Serializable {

	private static final long serialVersionUID = -2649188449485629485L;

//	@XmlAttribute(name = XMLStructure.VIDEO.ID)
//	protected Integer id;
	
	@XmlAttribute(name = XMLStructure.VIDEO.TITLE)
	protected String title;
	
	@XmlAttribute(name = XMLStructure.VIDEO.LINK)
	protected String link;

	@XmlAttribute(name = XMLStructure.VIDEO.BEST_OF)
	protected boolean bestof;
	
	@XmlAttribute(name = XMLStructure.VIDEO.DISABLED)
	protected boolean disabled;

	@XmlAttribute(name = XMLStructure.VIDEO.CATEGORIES)
	protected List<Category> categories;

//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isBestof() {
		return bestof;
	}

	public void setBestof(boolean bestof) {
		this.bestof = bestof;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
