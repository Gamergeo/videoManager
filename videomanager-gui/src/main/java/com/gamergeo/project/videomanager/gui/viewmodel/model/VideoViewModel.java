package com.gamergeo.project.videomanager.gui.viewmodel.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.javafx.viewmodel.AbstractModelBindable;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.service.VideoService;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.extern.slf4j.Slf4j;

@Component
@Scope("prototype")
@Slf4j
public class VideoViewModel extends AbstractModelBindable<Video, VideoService> {
	
	private final DoubleProperty rating = new SimpleDoubleProperty();
	private final StringProperty url = new SimpleStringProperty();;
	
	public VideoViewModel(Video video) {
		super(video);
		addBind(url, model::setUrl);
		addBind(rating, model::setRating);
	}
	
	/**
	 * Refresh information from model. Does not trigger any save, so do not use for update from viewmodel, only from model
	 */
	@Override
	protected void refreshViewModel() {
		setUrl(model.getUrl());
		setRating(model.getRating());
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
		log.info("get rating video");
		return this.ratingProperty().get();
	}
	
	public final void setRating(final double rating) {
		log.info("Change rating video model" + rating);
		this.rating.set(rating);
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
