package com.gamergeo.project.videomanager.gui.viewmodel;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.model.Video;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

@Component
public class VideoViewModel {
	
	private Video model;
	
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty url = new SimpleStringProperty();
    private final DoubleProperty rating = new SimpleDoubleProperty();
    private final ObjectProperty<LocalDate> addedDate = new SimpleObjectProperty<LocalDate>();
	
    private final ListProperty<TagViewModel> tags = new SimpleListProperty<TagViewModel>(FXCollections.observableArrayList());
    
    public VideoViewModel() {}
    
    public VideoViewModel(Video video) {
    	setVideo(video);
    	this.title.addListener((x, oldValue, newValue) -> model.setTitle(newValue));
    	this.url.addListener((x, oldValue, newValue) -> model.setUrl(newValue));
    	this.addedDate.addListener((x, oldValue, newValue) -> model.setAddedDate(newValue));
    	this.rating.addListener((x, oldValue, newValue) -> model.setRating(newValue.doubleValue()));
    	tags.addListener((ListChangeListener.Change<? extends TagViewModel> change) -> {
    	    while (change.next()) { 
    	        if (change.wasAdded()) { 
    	            List<? extends TagViewModel> addedSubList = change.getAddedSubList();
    	            for (TagViewModel addedItem : addedSubList) {
    	            	model.getTags().add(addedItem.getModel());
    	            }
    	        }
    	        if (change.wasRemoved()) {
    	            List<? extends TagViewModel> removedSubList = change.getRemoved();
    	            for (TagViewModel removedItem : removedSubList) {
    	            	model.getTags().remove(removedItem.getModel());
    	            }
    	        }
    	    }
    	});
    }
    
    public void setVideo(Video video) {
    	this.title.set(video.getTitle());
    	this.url.set(video.getUrl());
    	this.addedDate.set(video.getAddedDate());
		this.rating.set(video.getRating());
    	tags.clear();
    	video.getTags().forEach((tag) -> tags.add(new TagViewModel(tag)));
    	this.model = video;
    }
    
	public final StringProperty urlProperty() {
		return this.url;
	}
	
	public final String getUrl() {
		return this.urlProperty().get();
	}
	
	public final void setUrl(final String url) {
		this.urlProperty().set(url);
	}

	public final ObjectProperty<LocalDate> addedDateProperty() {
		return this.addedDate;
	}

	public final String getAddedDate() {
		return this.addedDateProperty().get().toString();
	}
	
	public final void setAddedDate(final LocalDate addedDate) {
		this.addedDateProperty().set(addedDate);
	}
	
	public Video getModel() {
		return model;
	}

	public void setModel(Video model) {
		this.model = model;
	}

	public final StringProperty titleProperty() {
		return this.title;
	}

	public final String getTitle() {
		return this.titleProperty().get();
	}
	
	public final void setTitle(final String title) {
		this.titleProperty().set(title);
	}
	
	public final Long getId() {
		return model.getId();
	}

    public ObservableList<TagViewModel> getTags() {
        return tags.get();
    }

    public ListProperty<TagViewModel> tagsProperty() {
        return tags;
    }

	public final DoubleProperty ratingProperty() {
		return this.rating;
	}
	
	public final double getRating() {
		return this.ratingProperty().get();
	}
	
	public final void setRating(final double rating) {
		this.ratingProperty().set(rating);
	}
	
	public List<Long> getTagListIds() {
		return this.model.getTags().stream().map(Tag::getId).collect(Collectors.toList());
	}
	
}
