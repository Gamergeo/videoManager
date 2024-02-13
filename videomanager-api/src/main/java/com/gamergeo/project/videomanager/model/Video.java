package com.gamergeo.project.videomanager.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

@Entity
public class Video extends DatabaseModel {

	private StringProperty title = new SimpleStringProperty();
    private StringProperty url = new SimpleStringProperty();
    private BooleanProperty disabled = new SimpleBooleanProperty();
    private final ObjectProperty<LocalDate> addedDate = new SimpleObjectProperty<>();
    private final DoubleProperty rating = new SimpleDoubleProperty();
    
    private ListProperty<Tag> tags = new SimpleListProperty<Tag>(FXCollections.observableArrayList());
    
	@Column(length = 2000)
	public String getTitle() {
		return this.title.get();
	}

	@Column(length = 2000)
	public String getUrl() {
		return this.urlProperty().get();
	}

    @ManyToMany(fetch = FetchType.EAGER)
	public List<Tag> getTags() {
    	return tags.get();
	}

	public boolean isDisabled() {
		return this.disabledProperty().get();
	}

	public void setTitle(String title) {
		this.title.set(title);
	}
	
	public StringProperty titleProperty() {
		return this.title;
	}

	public StringProperty urlProperty() {
		return this.url;
	}

	public void setUrl(final String url) {
		this.urlProperty().set(url);
	}

	public final BooleanProperty disabledProperty() {
		return this.disabled;
	}
	
	public void setDisabled(final boolean disabled) {
		this.disabledProperty().set(disabled);
	}

	public ObjectProperty<LocalDate> addedDateProperty() {
		return this.addedDate;
	}
	
	public LocalDate getAddedDate() {
		return this.addedDateProperty().get();
	}

	public void setAddedDate(final LocalDate addedDate) {
		this.addedDateProperty().set(addedDate);
	}
	
	public DoubleProperty ratingProperty() {
		return this.rating;
	}
	
	public Double getRating() {
		return this.ratingProperty().get();
	}

	public void setRating(final Double rating) {
		this.ratingProperty().set(rating);
	}
	
    public void setTags(List<Tag> tags) {
	   this.tags.set(FXCollections.observableArrayList(tags));
    }
	
	public ListProperty<Tag> tagsProperty() {
		return this.tags;
	}
}
