package com.gamergeo.project.videomanager.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.FXUtils;
import com.gamergeo.project.videomanager.viewmodel.SceneViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

@Component
public class SceneView implements FxmlView<SceneViewModel>, Initializable {
	
	@FXML 
	private HBox root;
	
	@FXML
	private BorderPane main;

    @InjectViewModel
    private SceneViewModel viewModel;
	
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	main.setTop(FXUtils.getRoot(SearchView.class));
    	main.setBottom(FXUtils.getRoot(TableView.class));
    	main.setCenter(FXUtils.getRoot(ScreenView.class));
    	
    	
//    	main.setTop(getRoot(SearchView.class));
//    	main.setBottom(getRoot(TableView.class));
//    	main.setCenter(getRoot(ScreenView.class));
//    	root.getChildren().add(getRoot(TagListView.class));
    	
//    	root.cursorProperty().bind(viewModel.cursorProperty());
    	
//    	root.setOnMouseDragReleased(this::onDragDropped);
	}
	
	private void onDragDropped(MouseEvent e) {
//		viewModel.onDragDropped();
	}
}
