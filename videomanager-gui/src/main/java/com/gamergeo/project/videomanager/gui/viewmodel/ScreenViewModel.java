package com.gamergeo.project.videomanager.gui.viewmodel;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.javafx.viewmodel.AbstractChildViewModel;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.service.VideoService;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScreenViewModel extends AbstractChildViewModel<SceneViewModel> {
	
	private final VideoService videoService;
	private Video video;

	private final StringProperty title = new SimpleStringProperty();
	private final DoubleProperty rating = new SimpleDoubleProperty();
	private final BooleanProperty visible = new SimpleBooleanProperty();
	private final ObservableList<Tag> videoTags = FXCollections.observableArrayList();
	
	public ScreenViewModel(VideoService videoService) {
		this.videoService = videoService;
	}

	@Override
	public void init() {
		visible.set(false);
		rating.addListener((observable, oldValue, newValue) -> {
			if (oldValue != newValue) {
				save();
			}
		});
		
		// We need to update listener on video change
		parent.selectedVideoProperty().addListener((observable, oldValue, newValue) -> {
	    		log.info("Selected video changed");
	    		
				// Unbind old selected video
				if (oldValue != null) {
					title.unbindBidirectional(video.titleProperty());
					rating.unbindBidirectional(video.ratingProperty());
				}

				this.video = newValue;
				if (newValue == null) {
					title.set("");
					rating.set(0);
					videoTags.clear();
					visible.set(false);
				} else {
					title.bindBidirectional(video.titleProperty());
					rating.bindBidirectional(video.ratingProperty());
					visible.set(true);
					videoTags.setAll(video.getTags());
				}
	    });
	}
	
	private void save() {
		videoService.save(video);
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
	
	public ObservableList<Tag> videoTags() {
		return videoTags;
	}
	
}
