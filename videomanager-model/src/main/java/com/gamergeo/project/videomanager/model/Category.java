package com.gamergeo.project.videomanager.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name=XMLStructure.CATEGORY.ROOT)
@XmlType(propOrder = {XMLStructure.CATEGORY.TEXT})
public class Category implements Serializable {

	private static final long serialVersionUID = 6257375419629830569L;
	
	@XmlAttribute(name = XMLStructure.CATEGORY.TEXT)
	protected String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
