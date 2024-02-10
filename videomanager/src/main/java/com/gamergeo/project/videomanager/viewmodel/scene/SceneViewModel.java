package com.gamergeo.project.videomanager.viewmodel.scene;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.service.TagService;
import com.gamergeo.project.videomanager.service.VideoService;
import com.gamergeo.project.videomanager.viewmodel.screen.ScreenViewModel;
import com.gamergeo.project.videomanager.viewmodel.search.SearchViewModel;
import com.gamergeo.project.videomanager.viewmodel.table.TableRowViewModel;
import com.gamergeo.project.videomanager.viewmodel.table.TableViewModel;
import com.gamergeo.project.videomanager.viewmodel.taglist.TagListViewModel;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Cursor;

@Component
public class SceneViewModel implements ViewModel {
	
	private SearchViewModel search;
	private ScreenViewModel screen;
	private TableViewModel table;
	private TagListViewModel tagList;
	
	private final VideoService videoService;
	private final TagService tagService;
	
	private final ObjectProperty<Video> selectedVideo = new SimpleObjectProperty<Video>();
	private final ObjectProperty<Cursor> cursor = new SimpleObjectProperty<Cursor>();

	private final BooleanProperty dragInProgress = new SimpleBooleanProperty();
	private final BooleanProperty droppable = new SimpleBooleanProperty();
	private final BooleanProperty dragReleased = new SimpleBooleanProperty();
	
	public SceneViewModel(VideoService videoService, TagService tagService) {
      	this.videoService = videoService;
      	this.tagService = tagService;
      	
		// Bind droppable and cursor
      	FXUtils.addSimpleChangeListener(droppable, this::onDragOver);
      	FXUtils.addSimpleChangeListener(dragReleased, this::onDragReleased);
	}
	
	public void initSearch(SearchViewModel search) {
		this.search = search;
		
		// Filter table through search property
      	FXUtils.addEmptyChangeListener(search.titleProperty(), this::filter);
      	FXUtils.addEmptyChangeListener(search.ratingProperty(), this::filter);
      	FXUtils.addSimpleListChangeListener(search.getWithTags(), (list) -> this.filter());
      	FXUtils.addSimpleListChangeListener(search.getWithoutTags(), (list) -> this.filter());
      	
      	// Random handler
      	FXUtils.addSimpleChangeListener(search.randomClickProperty(), this::random);
      	
		// Drag and drop
		FXUtils.addEmptyChangeListener(search.dragOverProperty(), this::droppable);
		FXUtils.addSimpleChangeListener(search.dragReleasedProperty(true), (dragReleased) -> this.onDragSearchReleased(true, dragReleased));
		FXUtils.addSimpleChangeListener(search.dragReleasedProperty(false), (dragReleased) -> this.onDragSearchReleased(false, dragReleased));
	}
	
	/**
	 * Drag released on with tag
	 */
	private void onDragSearchReleased(boolean with, boolean dragReleased) {
		if (dragReleased) {
			endDrag();
			search.addTags(tagList.getSelectedTags(), with);
			search.setDragReleased(with, false);
			tagList.unselectAllTag();
		}
	}
	
	public void random(boolean isClicked) {
		if (isClicked) {
			setSelectedVideo(table.random());
		}
		search.setRandomClick(false);
	}
	
	private void filter() {
		table.filter(search.getTitle(), search.getRating(), search.getWithTags(), search.getWithoutTags());
	}
	
	public void initScreen(ScreenViewModel screen) {
		this.screen = screen;

      	// Bind selected and screen
		selectedVideoProperty().addListener((observable, oldValue, newValue) -> screen.render(oldValue, newValue));
		
		// Drag and drop
		FXUtils.addEmptyChangeListener(screen.droppableProperty(), this::droppable);
		FXUtils.addSimpleChangeListener(screen.dragReleasedProperty(), this::onDragScreenReleased);
		
		// Disable
		FXUtils.addSimpleChangeListener(screen.disabledProperty(), this::disableVideo);
	}
	
	private void disableVideo(boolean disabled) {
		if (disabled) {
			Video selectedVideo = getSelectedVideo();
			setSelectedVideo(null);
			selectedVideo.setDisabled(true);
			videoService.save(selectedVideo);
			table.loadTable();
		}
	}
	
	/**
	 * Drag released on screen
	 */
	private void onDragScreenReleased(boolean dragReleased) {
		if (dragReleased) {
			endDrag();
			screen.addTags(tagList.getSelectedTags());
			screen.setDragReleased(false);
			tagList.unselectAllTag();
		}
	}
	
	public void initTable(TableViewModel table) {
		this.table = table;

      	// Bind selected video and row
      	FXUtils.addSimpleChangeListener(selectedVideo, table::setSelectedRow);
      	FXUtils.addSimpleChangeListener(table.selectedRowProperty(), (newValue) -> rowSelected(newValue));
	}
	
	private void rowSelected(final TableRowViewModel selectedRow) {
		selectedVideo.set(selectedRow == null ? null : selectedRow.getVideo());
	}
	
	public void initTagList(TagListViewModel tagList) {
		this.tagList = tagList;
		
		FXUtils.addSimpleChangeListener(tagList.dragDetectedProperty(), this::onDragDetected);
		// Delete tag and refresh all
		FXUtils.addSimpleChangeListener(tagList.tagToDeleteProperty(), this::deleteTag);
	}
	
	/**
	 * Dqelete tag and refresh videos
	 */
	private void deleteTag(Tag tag) {
		
		if (tag != null) {
			Video selectedVideo = this.selectedVideo.get();
			setSelectedVideo(null);
			tagService.delete(tag);
			tagList.setTagToDelete(null);
			table.loadTable();
			search.deleteTag(tag);
			setSelectedVideo(videoService.refresh(selectedVideo));
		}
	}
	
	private void onDragDetected(Boolean dragDetected) {
		if (dragDetected) {
			setCursor(Cursor.CLOSED_HAND);
			tagList.setDragDetected(false);
			setDragInProgress(true);
		}
	}
	
	private void onDragOver(Boolean droppable) {
		if (isDragInProgress()) {
			if (droppable) {
				setCursor(Cursor.CLOSED_HAND);
			} else {
				setCursor(Cursor.OPEN_HAND);
			}
		}
	}
	
	/**
	 * Drag released on non valid location
	 */
	private void onDragReleased(boolean dragReleased) {
		if (dragReleased) {
			endDrag();
			setDragReleased(false);
		}
	}
	
	/**
	 * Check if current drag is droppable
	 */
	private void droppable() {
		if (isDragInProgress()) {
			setDroppable(screen.isDroppable() || search.isDragOver());
		}
	}
	
	private void endDrag() {
		setDragInProgress(false);
		setCursor(Cursor.DEFAULT);
	}
	
	/*************************************************************************************************************************************/
	/*													       																		     */
	/*													        GETTER SETTER														     */
	/*													       																		     */
	/*************************************************************************************************************************************/
	public final ObjectProperty<Video> selectedVideoProperty() {
		return this.selectedVideo;
	}

	public final Video getSelectedVideo() {
		return this.selectedVideoProperty().get();
	}

	public final void setSelectedVideo(final Video selectedVideo) {
		this.selectedVideoProperty().set(selectedVideo);
	}

	public final ObjectProperty<Cursor> cursorProperty() {
		return this.cursor;
	}
	
	public final Cursor getCursor() {
		return this.cursorProperty().get();
	}
	
	public final void setCursor(final Cursor cursor) {
		this.cursorProperty().set(cursor);
	}

	public final BooleanProperty dragInProgressProperty() {
		return this.dragInProgress;
	}

	public final boolean isDragInProgress() {
		return this.dragInProgressProperty().get();
	}

	public final void setDragInProgress(final boolean dragInProgress) {
		this.dragInProgressProperty().set(dragInProgress);
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
}
