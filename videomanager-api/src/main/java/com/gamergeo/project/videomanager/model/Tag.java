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
	private StringProperty title = new SimpleStringProperty();
	
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
	
	public StringProperty titleProperty() {
		return this.title;
	}
	
	public String getTitle() {
		return this.titleProperty().get();
	}
	
	public void setTitle(final String title) {
		this.titleProperty().set(title);
	}
	
	
	
}
