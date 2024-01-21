package com.gamergeo.project.videomanager.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.gamergeo.lib.gamlib.model.Model;

@XmlRootElement(name=XMLStructure.TAG.ROOT)
@XmlType(propOrder = {XMLStructure.TAG.TEXT})
public class VideoTag implements Serializable, Model {

	private static final long serialVersionUID = 6257375419629830569L;
	
	@XmlAttribute(name = XMLStructure.TAG.TEXT)
	protected String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
