package com.gamergeo.project.videomanager.model;

import jakarta.persistence.Entity;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
public class UrlPattern extends Model {
	
	private StringProperty pattern = new SimpleStringProperty();

	public StringProperty patternProperty() {
		return this.pattern;
	}

	public String getPattern() {
		return this.patternProperty().get();
	}

	public void setPattern(final String pattern) {
		this.patternProperty().set(pattern);
	}
	
}
