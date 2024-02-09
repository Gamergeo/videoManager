package com.gamergeo.project.videomanager.gui.viewmodel;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.viewmodel.DefaultChildViewModel;
import com.gamergeo.project.videomanager.gui.VideoManagerApplication;
import com.gamergeo.project.videomanager.gui.viewmodel.tag.TagDroppableViewModel;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.service.UrlPatternService;
import com.gamergeo.project.videomanager.service.VideoService;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScreenViewModel extends DefaultChildViewModel<SceneViewModel> implements TagDroppableViewModel {

	private final VideoService videoService;
	private final UrlPatternService urlService;
	private final VideoManagerApplication application;
	private Video video;

	private final StringProperty title = new SimpleStringProperty();
	private final DoubleProperty rating = new SimpleDoubleProperty();
	private final BooleanProperty visible = new SimpleBooleanProperty();
	private final StringProperty url = new SimpleStringProperty();
	private final ObservableList<Tag> videoTags = FXCollections.observableArrayList();
	
	public ScreenViewModel(VideoService videoService, UrlPatternService urlService, VideoManagerApplication application) {
		this.videoService = videoService;
		this.urlService = urlService;
		this.application = application;
	}

	@Override
	public void init() {
		visible.set(false);

		// Semi rating + save
		rating.addListener((observable, oldValue, newValue) -> saveRating(oldValue.doubleValue(), newValue.doubleValue()));
		
		// Save on tag change
		addSimpleListChangeListener(videoTags, this::saveTag);

		// Render on video change
		parent.selectedVideoProperty().addListener((observable, oldValue, newValue) -> render(oldValue, newValue));
	}
	
	public void openUrl(MouseEvent event) {
		String url = !event.isControlDown() ? getUrl() : urlService.getGoogleUrl(getUrl());
		application.getHostServices().showDocument(url);
	}
	
	public void dropTags() {
		videoTags.addAll(parent.getSelectedTags());
	}
	
	private void save() {
		videoService.save(video);
	}
	
	/**
	 * Save rating (rounded by 0.5)
	 */
	private void saveRating(Double oldValue, Double newValue) {
	    double roundedValue = Math.round(newValue * 2) / 2.0;
		if (oldValue != newValue && !newValue.equals(roundedValue)) {
	    	setRating(roundedValue);
	    	video.setRating(roundedValue);
			save();
		}
	}
	
	private void saveTag(ObservableList<Tag> tags) {
		video.setTags(tags.stream().collect(Collectors.toList()));
		videoService.save(video);
	}
	
	private void render(Video oldValue, Video newValue) {
		log.info("Selected video changed");
		// Unbind old selected video
		if (oldValue != null) {
			unbindVideo(oldValue);
		}
		
		renderVideo(newValue);
	}
	
	private void renderVideo(Video video) {
		this.video = video;
		if (video == null) {
			title.set("");
			rating.set(0);
			videoTags.clear();
			visible.set(false);
		} else {
			title.bindBidirectional(video.titleProperty());
			rating.bindBidirectional(video.ratingProperty());
			url.bindBidirectional(video.urlProperty());
			visible.set(true);
			videoTags.setAll(video.getTags());
		}
	}
	
	private void unbindVideo(Video video) {
		title.unbindBidirectional(video.titleProperty());
		rating.unbindBidirectional(video.ratingProperty());
		url.unbindBidirectional(video.urlProperty());
	}

	@Override
	public void onDragOver() {
		parent.setDroppable(true);
	}

	@Override
	public void onDragDropped() {
		// TODO Auto-generated method stub
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
	
	public ObservableList<Tag> getVideoTags() {
		return videoTags;
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
