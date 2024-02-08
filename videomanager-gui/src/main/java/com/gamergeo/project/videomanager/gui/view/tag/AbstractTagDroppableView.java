package com.gamergeo.project.videomanager.gui.view.tag;

import org.springframework.context.ApplicationContext;

import com.gamergeo.project.videomanager.gui.VideoManagerParameters;
import com.gamergeo.project.videomanager.gui.viewmodel.tag.TagDroppableViewModel;

import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;

/**
 * Abstract Class for droppable tag
 */
public abstract class AbstractTagDroppableView<VM extends TagDroppableViewModel> extends AbstractTagParentView<VM> {

	public AbstractTagDroppableView(ApplicationContext applicationContext, VM viewModel, VideoManagerParameters applicationParameters) {
		super(applicationContext, viewModel, applicationParameters);
	}

	protected void initialize() {
		super.initialize();
		
		getDropTagPane().setOnDragOver(this::onDragOver);
		getDropTagPane().setOnDragDropped(this::onDragDropped);
	}

	private void onDragOver(DragEvent event) {
		viewModel.onDragOver();
		event.consume();
	}

	private void onDragDropped(DragEvent event) {
		viewModel.onDragDropped();
		event.consume();
	}
	
	protected abstract Pane getDropTagPane();
}
