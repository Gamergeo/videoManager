//package com.gamergeo.project.videomanager.gui.view.tag;
//
//import org.springframework.stereotype.Component;
//
//import com.gamergeo.project.videomanager.gui.viewmodel.tag.TagListViewModel;
//import com.gamergeo.project.videomanager.model.Tag;
//
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.TextField;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.TilePane;
//
//@Component
//public class TagListView extends TagParentView<TagListViewModel> {
//	
//	@FXML
//	private TextField search;
//	
//	@FXML
//	private TilePane list;
//	
//	@FXML
//	protected void initialize() {
//		super.initialize();
//		
//		list.setOnDragDetected(this::onDragDetected);
//	}
//
//	@Override
//	protected Pane getTagPane() {
//		return list;
//	}
//
//	@Override
//	protected ObservableList<Tag> getTags() {
//		return viewModel.getDisplayedTags();
//	}
//
//	private void onDragDetected(MouseEvent event) {
//		list.startFullDrag();
//		viewModel.onDragDetected();
//	}
//	
//}