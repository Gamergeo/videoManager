package com.gamergeo.project.videomanager.viewmodel.video;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.service.UrlPatternService;
import com.gamergeo.project.videomanager.service.VideoService;
import com.gamergeo.project.videomanager.viewmodel.tag.TagParentViewModel;
import com.gamergeo.project.videomanager.viewmodel.tag.TagViewModel;

import de.saxsys.mvvmfx.ViewModel;
import javafx.application.HostServices;
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
public class ScreenViewModel implements ViewModel, TagParentViewModel {

	private final VideoService videoService;
	private final UrlPatternService urlService;
	private final HostServices hostServices;
	private final List<TagViewModel> tagViewModels = new ArrayList<TagViewModel>();
	
	private Video video;

	/** Video property */
	private final StringProperty title = new SimpleStringProperty();
	private final DoubleProperty rating = new SimpleDoubleProperty();
	private final StringProperty url = new SimpleStringProperty();
	private final ObservableList<Tag> renderedTags = FXCollections.observableArrayList();
	
	/** drag & drop properties */
	private final BooleanProperty droppable = new SimpleBooleanProperty();
	private final BooleanProperty dragReleased = new SimpleBooleanProperty();
	
	/** other properties */
	private final BooleanProperty visible = new SimpleBooleanProperty();
	
	public ScreenViewModel(VideoService videoService, UrlPatternService urlService, HostServices hostServices) {
		this.videoService = videoService;
		this.urlService = urlService;
		this.hostServices = hostServices;
		
		visible.set(false);

		// Semi rating + save
		rating.addListener((observable, oldValue, newValue) -> saveRating(oldValue.doubleValue(), newValue.doubleValue()));
		
		// Save on tag change
		FXUtils.addSimpleListChangeListener(renderedTags, this::saveTag);
	}
	
	public void render(Video oldValue, Video newValue) {
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
			renderedTags.clear();
			visible.set(false);
		} else {
			title.bindBidirectional(video.titleProperty());
			rating.bindBidirectional(video.ratingProperty());
			url.bindBidirectional(video.urlProperty());
			visible.set(true);
			renderedTags.setAll(video.getTags());
		}
	}
	
	private void unbindVideo(Video video) {
		title.unbindBidirectional(video.titleProperty());
		rating.unbindBidirectional(video.ratingProperty());
		url.unbindBidirectional(video.urlProperty());
	}
	
	/**
	 * Open url TODO
	 */
	public void openUrl(MouseEvent event) {
		String url = !event.isControlDown() ? getUrl() : urlService.getGoogleUrl(getUrl());
		hostServices.showDocument(url);
	}
	
	/**
	 * Save rating (rounded by 0.5)
	 */
	private void saveRating(Double oldValue, Double newValue) {
	    double roundedValue = Math.round(newValue * 2) / 2.0;
		if (oldValue != newValue && !newValue.equals(roundedValue)) {
	    	setRating(roundedValue);
	    	video.setRating(roundedValue);
			videoService.save(video);
		}
	}
	
	private void saveTag(ObservableList<Tag> tags) {
		video.setTags(tags.stream().collect(Collectors.toList()));
		videoService.save(video);
	}
	
	public void onMouseDragOver() {
		setDroppable(video != null);
	}
	
	public void onMouseDragExited() {
		setDroppable(false);
	}

	/**
	 * Add tags to selected video
	 * @param tags
	 */
	public void addTags(ObservableList<Tag> tags) {
		
		// Add dropped tag to video
		if (video != null) {
		
			if (tags.isEmpty()) {
				return;
			}
			
			// Add tag not present
			tags.stream()
		    	.filter(tag -> !renderedTags.contains(tag))
		    	.forEach(renderedTags::add);
		}
	}
	
	/**
	 * Init child view model list / bind 
	 */
	public void initTagViewModel(TagViewModel tagViewModel, Tag tag) {
		tagViewModels.add(tagViewModel);
		tagViewModel.setTag(tag);
		tagViewModel.setSelectable(false);
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

	public final StringProperty urlProperty() {
		return this.url;
	}

	public final String getUrl() {
		return this.urlProperty().get();
	}
	
	public final void setUrl(final String url) {
		this.urlProperty().set(url);
	}

	public final BooleanProperty droppableProperty() {
		return this.droppable;
	}
	
	public final boolean isDroppable() {
		return this.droppableProperty().get();
	}

	public final void setDroppable(final boolean droppable) {
		this.droppableProperty().set(droppable);
	}

	public final BooleanProperty dragReleasedProperty() {
		return this.dragReleased;
	}

	public final boolean isDragReleased() {
		return this.dragReleasedProperty().get();
	}

	public final void setDragReleased(final boolean dragReleased) {
		this.dragReleasedProperty().set(dragReleased);
	}

	@Override
	public ObservableList<Tag> getRenderedTags() {
		return renderedTags;
	}
}