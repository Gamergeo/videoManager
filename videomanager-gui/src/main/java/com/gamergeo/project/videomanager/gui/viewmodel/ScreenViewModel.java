package com.gamergeo.project.videomanager.gui.viewmodel;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.javafx.viewmodel.AbstractChildViewModel;
import com.gamergeo.project.videomanager.model.Video;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScreenViewModel extends AbstractChildViewModel<SceneViewModel> {

	private Video video;

	private final StringProperty title = new SimpleStringProperty();
	private final DoubleProperty rating = new SimpleDoubleProperty();
	
	private final BooleanProperty visible = new SimpleBooleanProperty();

	@Override
	public void init() {
		// We need to update listener on video change
		parent.selectedVideoProperty().addListener((observable, oldValue, newValue) -> {
	    		log.info("Selected video changed");
	    		
				// Unbind old selected video
				if (oldValue != null) {
					title.unbindBidirectional(oldValue.titleProperty());
					rating.unbindBidirectional(oldValue.ratingProperty());
					visible.set(false);
				}

				this.video = newValue;
				
				if (newValue == null) {
					title.set("");
					rating.set(0);
				} else {
					title.bindBidirectional(video.titleProperty());
					rating.bindBidirectional(video.ratingProperty());
					visible.set(true);
				}
	    });
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

	public final BooleanProperty visibleProperty() {
		return this.visible;
	}
	
	public final boolean isVisible() {
		return this.visibleProperty().get();
	}
	
	public final void setVisible(final boolean visible) {
		this.visibleProperty().set(visible);
	}
	
	
	
	
//	public final ObjectProperty<VideoViewModel> selectedVideoProperty() {
//		return scene.selectedVideoProperty();
//	}
//	
//	public final DoubleProperty ratingProperty() {
//		return selectedVideoProperty().get().ratingProperty();
//	}
}
