package com.gamergeo.project.videomanager.service.parse;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;

public class Folder {
	
	private String name;
	
	private List<Folder> childFolder = new ArrayList<Folder>();
	
	private Node contentNode;
	
	public Folder(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Folder> getChildFolder() {
		return childFolder;
	}

	public void setChildFolder(List<Folder> childFolder) {
		this.childFolder = childFolder;
	}
	
	public Node getContentNode() {
		return contentNode;
	}

	public void setContentNode(Node contentNode) {
		this.contentNode = contentNode;
	}

	@Override
	public String toString() {
		return getName();
	}
}
