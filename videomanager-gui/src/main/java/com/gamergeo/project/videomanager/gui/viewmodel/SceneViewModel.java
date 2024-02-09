package com.gamergeo.project.videomanager.gui.viewmodel;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.viewmodel.AbstractViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.tag.TagListViewModel;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.model.Video;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;

@Component
public class SceneViewModel extends AbstractViewModel {
	
	private final SearchViewModel search;
	private final ScreenViewModel screen;
	private final TableViewModel table;
	private final TagListViewModel tagList;
	
	private final ObjectProperty<Video> selectedVideo = new SimpleObjectProperty<Video>();
	private final ObjectProperty<Cursor> cursor = new SimpleObjectProperty<Cursor>();
	private final BooleanProperty dragDetected = new SimpleBooleanProperty();
	private final BooleanProperty droppable = new SimpleBooleanProperty();
	
	
	public SceneViewModel(SearchViewModel search, ScreenViewModel screen, TableViewModel table, TagListViewModel tagList) {
		this.search = search;
		this.screen = screen;
		this.table = table;
		this.tagList = tagList;
		
		// Filter table through search property
      	addEmptyChangeListener(search.titleProperty(), this::filter);
      	addEmptyChangeListener(search.ratingProperty(), this::filter);
      	
      	// Bind selected video and row
      	addSimpleChangeListener(selectedVideo, table::setSelectedRow);
      	addSimpleChangeListener(table.selectedRowProperty(), (newValue) -> rowSelected(newValue));
      	
      	// Bind droppable and cursor
      	addSimpleChangeListener(droppable, this::onDragOver);
	}
	
	public void onDragDetected() {
		setDragDetected(true);
		setCursor(Cursor.CLOSED_HAND);
	}
	
	public void onDragOver(Boolean droppable) {
		if (isDragDetected()) {
			if (droppable) {
				setCursor(Cursor.CLOSED_HAND);
			} else {
				setCursor(Cursor.OPEN_HAND);
			}
		}
	}
	
	public void onDragDropped() {
		setDragDetected(false);
		setCursor(Cursor.DEFAULT);
	}
	
	private void rowSelected(final TableRowViewModel selectedRow) {
  		if (selectedVideo != null) {
  			selectedVideo.set(selectedRow.getVideo());
  		}
	}
	
	public ObservableList<Tag> getSelectedTags() {
		return tagList.getSelectedTags();
	}
	
	private void filter() {
		table.filter(search.getTitle(), search.getRating());
	}
	
	public void random() {
		setSelectedVideo(table.random());
	}
	
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

	public final BooleanProperty droppableProperty() {
		return this.droppable;
	}

	public final boolean isDroppable() {
		return this.droppableProperty().get();
	}

	public final void setDroppable(final boolean droppable) {
		this.droppableProperty().set(droppable);
	}

	public final BooleanProperty dragDetectedProperty() {
		return this.dragDetected;
	}

	public final boolean isDragDetected() {
		return this.dragDetectedProperty().get();
	}
	
	public final void setDragDetected(final boolean dragDetected) {
		this.dragDetectedProperty().set(dragDetected);
	}
}
