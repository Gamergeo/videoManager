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
	private final BooleanProperty dragDetected = new SimpleBooleanProperty();
	private final BooleanProperty droppable = new SimpleBooleanProperty();
	
	public SceneViewModel() {
      	// Bind droppable and cursor
      	FXUtils.addSimpleChangeListener(droppable, this::onDragOver);
	}
	
	public void initSearch(SearchViewModel search) {
		this.search = search;
		
		// Filter table through search property
      	FXUtils.addEmptyChangeListener(search.titleProperty(), this::filter);
      	FXUtils.addEmptyChangeListener(search.ratingProperty(), this::filter);
      	FXUtils.addSimpleChangeListener(search.randomClickProperty(), this::random);
      	
	}
	
	public void initScreen(ScreenViewModel screen) {
		this.screen = screen;

      	// Bind selected and screen
		selectedVideoProperty().addListener((observable, oldValue, newValue) -> screen.render(oldValue, newValue));
	}
	
	public void initTable(TableViewModel table) {
		this.table = table;

      	// Bind selected video and row
      	FXUtils.addSimpleChangeListener(selectedVideo, table::setSelectedRow);
      	FXUtils.addSimpleChangeListener(table.selectedRowProperty(), (newValue) -> rowSelected(newValue));
	}
	
	public void initTagList(TagListViewModel tagList) {
		this.tagList = tagList;
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
	
//	public ObservableList<Tag> getSelectedTags() {
//		return tagList.getSelectedTags();
//	}
	
	private void filter() {
		table.filter(search.getTitle(), search.getRating());
	}
	
	public void random(boolean isClicked) {
		if (isClicked) {
			setSelectedVideo(table.random());
		}
		search.setRandomClick(false);
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
