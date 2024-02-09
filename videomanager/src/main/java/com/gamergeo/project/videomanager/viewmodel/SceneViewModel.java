package com.gamergeo.project.videomanager.viewmodel;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.viewmodel.tag.TagListViewModel;
import com.gamergeo.project.videomanager.viewmodel.video.ScreenViewModel;
import com.gamergeo.project.videomanager.viewmodel.video.SearchViewModel;
import com.gamergeo.project.videomanager.viewmodel.video.TableRowViewModel;
import com.gamergeo.project.videomanager.viewmodel.video.TableViewModel;

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
	
	private final ObjectProperty<Video> selectedVideo = new SimpleObjectProperty<Video>();
	private final ObjectProperty<Cursor> cursor = new SimpleObjectProperty<Cursor>();
	
	private final BooleanProperty droppable = new SimpleBooleanProperty();
	private final BooleanProperty dragInProgress = new SimpleBooleanProperty();
	private final BooleanProperty dragReleased = new SimpleBooleanProperty();
	
	public SceneViewModel() {
      	// Bind droppable and cursor
      	FXUtils.addSimpleChangeListener(droppable, this::onDragOver);
      	FXUtils.addSimpleChangeListener(dragReleased, this::onDragReleased);
	}
	
	public void initSearch(SearchViewModel search) {
		this.search = search;
		
		// Filter table through search property
      	FXUtils.addEmptyChangeListener(search.titleProperty(), this::filter);
      	FXUtils.addEmptyChangeListener(search.ratingProperty(), this::filter);
      	FXUtils.addSimpleChangeListener(search.randomClickProperty(), this::random);
	}
	
	public void random(boolean isClicked) {
		if (isClicked) {
			setSelectedVideo(table.random());
		}
		search.setRandomClick(false);
	}
	
	private void filter() {
		table.filter(search.getTitle(), search.getRating());
	}
	
	public void initScreen(ScreenViewModel screen) {
		this.screen = screen;

      	// Bind selected and screen
		selectedVideoProperty().addListener((observable, oldValue, newValue) -> screen.render(oldValue, newValue));
		
		FXUtils.addSimpleChangeListener(screen.droppableProperty(), this::setDroppable);
		FXUtils.addSimpleChangeListener(screen.dragReleasedProperty(), this::onDragScreenReleased);
	}
	
	public void initTable(TableViewModel table) {
		this.table = table;

      	// Bind selected video and row
      	FXUtils.addSimpleChangeListener(selectedVideo, table::setSelectedRow);
      	FXUtils.addSimpleChangeListener(table.selectedRowProperty(), (newValue) -> rowSelected(newValue));
	}
	
	private void rowSelected(final TableRowViewModel selectedRow) {
  		if (selectedVideo != null) {
  			selectedVideo.set(selectedRow.getVideo());
  		}
	}
	
	public void initTagList(TagListViewModel tagList) {
		this.tagList = tagList;
		
		FXUtils.addSimpleChangeListener(tagList.dragDetectedProperty(), this::onDragDetected);
	}
	
	public void onDragDetected(Boolean dragDetected) {
		if (dragDetected) {
			setCursor(Cursor.CLOSED_HAND);
			tagList.setDragDetected(false);
			setDragInProgress(true);
		}
	}
	
	public void onDragOver(Boolean droppable) {
		if (isDragInProgress()) {
			if (droppable) {
				setCursor(Cursor.CLOSED_HAND);
			} else {
				setCursor(Cursor.OPEN_HAND);
			}
		}
	}
	
	/**
	 * Drag released on screen
	 */
	public void onDragScreenReleased(boolean dragReleased) {
		if (dragReleased) {
			endDrag();
			screen.addTags(tagList.getSelectedTags());
			screen.setDragReleased(false);
		}
	}
	
	/**
	 * Drag released on screen
	 */
	public void onDragReleased(boolean dragReleased) {
		if (dragReleased) {
			endDrag();
			setDragReleased(false);
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
