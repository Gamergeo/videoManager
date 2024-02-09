package com.gamergeo.project.videomanager.viewmodel.video;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Component
public class SearchViewModel implements ViewModel {
	private final StringProperty title = new SimpleStringProperty();
	private final DoubleProperty rating = new SimpleDoubleProperty();
	private final BooleanProperty randomClick = new SimpleBooleanProperty();
	private final BooleanProperty resetClick = new SimpleBooleanProperty();

	public SearchViewModel() {
		// Semi rating
		rating.addListener((observable, oldValue, newValue) -> setRoundedRating(oldValue.doubleValue(), newValue.doubleValue()));
		
		FXUtils.addSimpleChangeListener(resetClick, this::reset);
	}
	
	public void reset(boolean isClicked) {
		if (isClicked) {
			title.set("");
			rating.set(0);
			resetClick.set(false);
		}
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

	public final BooleanProperty randomClickProperty() {
		return this.randomClick;
	}
	
	public final boolean isRandomClick() {
		return this.randomClickProperty().get();
	}
	
	public final void setRandomClick(final boolean randomClick) {
		this.randomClickProperty().set(randomClick);
	}
	
	public final BooleanProperty resetClickProperty() {
		return this.resetClick;
	}

	public final boolean isResetClick() {
		return this.resetClickProperty().get();
	}

	public final void setResetClick(final boolean resetClick) {
		this.resetClickProperty().set(resetClick);
	}
	
}