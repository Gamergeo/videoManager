//package com.gamergeo.project.videomanager.gui.view.tag;
//
//import com.gamergeo.project.videomanager.gui.viewmodel.tag.TagDroppableViewModel;
//
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.Pane;
//
///**
// * Abstract Class for droppable tag
// */
//public abstract class TagDroppableView<VM extends TagDroppableViewModel> extends TagParentView<VM> {
//
//	protected void initialize() {
//		super.initialize();
//		
//		getDropTagPane().setOnMouseDragOver(this::onMouseDragOver);
//		getDropTagPane().setOnMouseDragExited(this::onMouseDragExited);
//		getDropTagPane().setOnMouseDragReleased(this::onMouseDragReleased);
//	}
//
//	private void onMouseDragOver(MouseEvent event) {
//		viewModel.onMouseDragOver();
//	}
//	
//	private void onMouseDragExited(MouseEvent event) {
//		viewModel.onMouseDragExited();
//	}
//
//	private void onMouseDragReleased(MouseEvent event) {
//		viewModel.onMouseDragReleased();
//	}
//	
//	protected abstract Pane getDropTagPane();
//}