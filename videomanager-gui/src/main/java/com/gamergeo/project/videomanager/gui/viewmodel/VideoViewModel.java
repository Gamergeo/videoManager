package com.gamergeo.project.videomanager.gui.viewmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.service.VideoService;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Component
@Scope("prototype")
public class VideoViewModel {
	
	@Autowired
	private VideoService videoService;
	
	private Video model;

	private final DoubleProperty rating = new SimpleDoubleProperty();
	private final StringProperty url = new SimpleStringProperty();
	
	public VideoViewModel(Video video) {
		model = video;
		
		rating.set(video.getRating());
    	rating.addListener((x, oldValue, newValue) -> {
    		model.setRating(newValue.doubleValue());
    		save();
    	});
    	
    	url.set(video.getUrl());
    	url.addListener((x, oldValue, newValue) -> {
    		model.setUrl(newValue);
    		save();
    	});
	}
	
	private void save() {
		videoService.save(model);
	}
	
	public String getTitle() {
		return model.getTitle();
	}
	
	public String getDate() {
		return model.getAddedDate().toString();
	}
	
	public Long getId() {
		return model.getId();
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
	
	public final StringProperty urlProperty() {
		return this.url;
	}
	
	public final String getUrl() {
		return this.urlProperty().get();
	}
	
	public final void setUrl(final String url) {
		this.urlProperty().set(url);
	}
	
}
