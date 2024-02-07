package com.gamergeo.project.videomanager.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Entity
@Table
@Access(AccessType.PROPERTY)
public class Video {

	private LongProperty id = new SimpleLongProperty();
	private StringProperty title = new SimpleStringProperty();
    private StringProperty url = new SimpleStringProperty();
    private BooleanProperty disabled = new SimpleBooleanProperty();
    private final ObjectProperty<LocalDate> addedDate = new SimpleObjectProperty<>();
    private final DoubleProperty rating = new SimpleDoubleProperty();
    
	private List<Tag> tags;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id.get();
	}

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
		return tags;
	}

	public boolean isDisabled() {
		return this.disabledProperty().get();
	}
	
	public void setId(long id) {
		this.id.set(id);
	}
	
	public LongProperty idProperty() {
		return this.id;
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
		this.tags = tags;
	}
}
