package com.gamergeo.project.videomanager.model;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table
@Access(AccessType.PROPERTY)
public class Tag {
	
	private LongProperty id = new SimpleLongProperty();
	private StringProperty label = new SimpleStringProperty();
	
	public LongProperty idProperty() {
		return this.id;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return this.idProperty().get();
	}
	
	public void setId(final long id) {
		this.idProperty().set(id);
	}

	public StringProperty labelProperty() {
		return this.label;
	}
	
	public String getLabel() {
		return this.labelProperty().get();
	}
	
	public void setLabel(final String label) {
		this.labelProperty().set(label);
	}
	
	@Override
	public String toString() {
		return "Tag " + getId() + " (" + getLabel() + " )";
	}
}
