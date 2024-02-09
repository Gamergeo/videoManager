package com.gamergeo.project.videomanager.gui.view;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXMLView;
import com.gamergeo.project.videomanager.gui.view.tag.TagListView;
import com.gamergeo.project.videomanager.gui.viewmodel.SceneViewModel;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

@Component
public class SceneView extends FXMLView<SceneViewModel> {
	
	@FXML 
	private HBox root;
	
	@FXML
	private BorderPane main;
	
	@FXML
    public void initialize() {
    	main.setTop(getRoot(SearchView.class));
    	main.setBottom(getRoot(TableView.class));
    	main.setCenter(getRoot(ScreenView.class));
    	root.getChildren().add(getRoot(TagListView.class));
    	
    	root.cursorProperty().bind(viewModel.cursorProperty());
    	
    	root.setOnMouseDragReleased(this::onDragDropped);
	}
	
	private void onDragDropped(MouseEvent e) {
		viewModel.onDragDropped();
	}
}
