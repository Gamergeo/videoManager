package com.gamergeo.project.videomanager.model;

import jakarta.persistence.Entity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
public class Tag extends DatabaseModel {
	
	private StringProperty label = new SimpleStringProperty();
	
	public StringProperty labelProperty() {
		return this.label;
	}
	
	public String getLabel() {
		return this.labelProperty().get();
	}
	
	public void setLabel(final String label) {
		this.labelProperty().set(label);
	}
}
