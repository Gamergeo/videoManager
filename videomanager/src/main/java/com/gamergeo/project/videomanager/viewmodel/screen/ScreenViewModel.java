package com.gamergeo.project.videomanager.viewmodel.screen;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.service.UrlPatternService;
import com.gamergeo.project.videomanager.service.VideoService;
import com.gamergeo.project.videomanager.viewmodel.tag.TagDroppableViewModel;

import de.saxsys.mvvmfx.ViewModel;
import javafx.application.HostServices;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScreenViewModel implements ViewModel, TagDroppableViewModel {

	private final VideoService videoService;
	private final UrlPatternService urlService;
	private final HostServices hostServices;
	
	private Video video;

	/** Video property */
	private final StringProperty title = new SimpleStringProperty();
	private final StringProperty date = new SimpleStringProperty();
	private final DoubleProperty rating = new SimpleDoubleProperty();
	private final StringProperty url = new SimpleStringProperty();
	private final StringProperty urlEdit = new SimpleStringProperty();
	private final ListProperty<Tag> renderedTags = new SimpleListProperty<Tag>(FXCollections.observableArrayList());
	
	/** drag & drop properties */
	private final BooleanProperty droppable = new SimpleBooleanProperty();
	private final BooleanProperty dragReleased = new SimpleBooleanProperty();
	
	/** other properties */
	private final BooleanProperty disabled = new SimpleBooleanProperty();
	private final BooleanProperty visible = new SimpleBooleanProperty();
	private final BooleanProperty edit = new SimpleBooleanProperty();
	
	
	public ScreenViewModel(VideoService videoService, UrlPatternService urlService, HostServices hostServices) {
		this.videoService = videoService;
		this.urlService = urlService;
		this.hostServices = hostServices;
		
		visible.set(false);

		// Semi rating + save
		rating.addListener((observable, oldValue, newValue) -> saveRating(oldValue.doubleValue(), newValue.doubleValue()));
		FXUtils.addSimpleChangeListener(urlEdit, this::saveUrl);
	}
	
	public void render(Video oldValue, Video newValue) {
		log.info("Selected video changed");
		
		setEdit(false);
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
			date.set("");
			rating.set(0);
			renderedTags.clear();
			visible.set(false);
		} else {
			title.bindBidirectional(video.titleProperty());
			date.set(video.getAddedDate().toString());
			rating.bindBidirectional(video.ratingProperty());
			url.bind(video.urlProperty());
			urlEdit.set(url.get());
			renderedTags.bindContent(video.tagsProperty());
			setDisabled(false);
			visible.set(true);
		}
	}
	
	private void unbindVideo(Video video) {
		title.unbindBidirectional(video.titleProperty());
		rating.unbindBidirectional(video.ratingProperty());
		url.unbind();
		renderedTags.unbindContent(video.tagsProperty());
	}
	
	/**
	 * Open url TODO
	 */
	public void openUrl(MouseEvent event) {
		String url = !event.isControlDown() ? getUrl() : urlService.getGoogleUrl(getTitle());
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

	/**
	 * Save url on enter
	 */
	public void saveUrl(String url) {
		
		if (isEdit()) {
			video.setUrl(url);
			videoService.save(video);
		}
	}

	@Override
	public void onMouseDragOver() {
		setDroppable(video != null);
	}

	@Override
	public void onMouseDragExited() {
		setDroppable(false);
	}

	@Override
	public void onMouseDragReleased() {
		setDragReleased(true);
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
		    	.filter(tag -> !video.tagsProperty().contains(tag))
		    	.forEach(video.getTags()::add);
			
			videoService.save(video);
		}
	}
	
	public void switchEdit() {
		if (isVisible()) {
			setEdit(!isEdit());
		}
	}
	
	public void deleteTag(Tag tag) {
		video.tagsProperty().remove(tag);
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

	public final ListProperty<Tag> renderedTagsProperty() {
		return this.renderedTags;
	}

	public final ObservableList<Tag> getRenderedTags() {
		return this.renderedTagsProperty().get();
	}
	
	public final void setRenderedTags(final ObservableList<Tag> renderedTags) {
		this.renderedTagsProperty().set(renderedTags);
	}

	public final BooleanProperty disabledProperty() {
		return this.disabled;
	}
	
	public final boolean isDisabled() {
		return this.disabledProperty().get();
	}
	
	public final void setDisabled(final boolean disabled) {
		this.disabledProperty().set(disabled);
	}

	public final StringProperty dateProperty() {
		return this.date;
	}
	
	public final String getDate() {
		return this.dateProperty().get();
	}
	
	public final void setDate(final String date) {
		this.dateProperty().set(date);
	}

	public final BooleanProperty editProperty() {
		return this.edit;
	}

	public final boolean isEdit() {
		return this.editProperty().get();
	}
	
	public final void setEdit(final boolean edit) {
		this.editProperty().set(edit);
	}

	public final StringProperty urlEditProperty() {
		return this.urlEdit;
	}

	public final String getUrlEdit() {
		return this.urlEditProperty().get();
	}

	public final void setUrlEdit(final String urlEdit) {
		this.urlEditProperty().set(urlEdit);
	}
}