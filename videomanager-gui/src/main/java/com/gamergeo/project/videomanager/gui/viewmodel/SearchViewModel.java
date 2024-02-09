package com.gamergeo.project.videomanager.gui.viewmodel;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.viewmodel.DefaultChildViewModel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Component
public class SearchViewModel extends DefaultChildViewModel<SceneViewModel> {
	
	private final StringProperty title = new SimpleStringProperty();
	private final DoubleProperty rating = new SimpleDoubleProperty();

	@Override
	public void init() {
		// Semi rating
		rating.addListener((observable, oldValue, newValue) -> setRoundedRating(oldValue.doubleValue(), newValue.doubleValue()));
	}
	
	public void random() {
		parent.random();
	}
	
	public void reset() {
		title.set("");
		rating.set(0);
	}
	
	/**
	 * Round rating to 0.5
	 */
	private void setRoundedRating(Double oldValue, Double newValue) {
	    double roundedValue = Math.round(newValue * 2) / 2.0;
		if (oldValue != newValue && !newValue.equals(roundedValue)) {
	    	setRating(roundedValue);
		}
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
