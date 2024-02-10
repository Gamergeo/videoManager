package com.gamergeo.project.videomanager.view.tag;

import com.gamergeo.project.videomanager.viewmodel.tag.TagDroppableViewModel;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Common interface for droppable tags view
 */
public interface TagDroppableView extends TagParentView {
	
	default public void setOnMouseDrag() {
		getDropPane().setOnMouseDragOver(this::onMouseDragOver);
		getDropPane().setOnMouseDragExited(this::onMouseDragExited);
		getDropPane().setOnMouseDragReleased(this::onMouseDragReleased);
	}
	
	default public void onMouseDragOver(MouseEvent event) {
		getViewModel().onMouseDragOver();
		event.consume();
	}

	default public void onMouseDragExited(MouseEvent event) {
		getViewModel().onMouseDragExited();
		event.consume();
	}

	default public void onMouseDragReleased(MouseEvent event) {
		getViewModel().setDragReleased(true);
		event.consume();
	}
	
	@Override
	public TagDroppableViewModel getViewModel();
	
	/**
	 * Where tag can be dropped
	 * default is tag pane
	 */
	default public Pane getDropPane() {
		return getTagPane();
	};
	
}