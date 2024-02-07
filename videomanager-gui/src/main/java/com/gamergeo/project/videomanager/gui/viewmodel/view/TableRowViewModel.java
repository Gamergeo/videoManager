package com.gamergeo.project.videomanager.gui.viewmodel.view;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.model.Video;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Component
@Scope("prototype")
public class TableRowViewModel {

	private Video video;
	
	private final StringProperty title = new SimpleStringProperty();
	private final DoubleProperty rating = new SimpleDoubleProperty();
	
	public TableRowViewModel(Video video) {
		this.video = video;
		title.bindBidirectional(video.titleProperty());
		rating.bindBidirectional(video.ratingProperty());
	}

	public Video getVideo() {
		return video;
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

	public final DoubleProperty ratingProperty() {
		return this.rating;
	}
	
	public final double getRating() {
		return this.ratingProperty().get();
	}

	public final void setRating(final double rating) {
		this.ratingProperty().set(rating);
	}
	

}
