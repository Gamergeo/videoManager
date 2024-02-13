package com.gamergeo.project.videomanager.model.parse;

import java.util.ArrayList;
import java.util.List;

public class Folder {
	
	private String name;
	
	private List<Folder> childFolder = new ArrayList<Folder>();
	
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
}
