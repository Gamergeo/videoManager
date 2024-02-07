package com.gamergeo.project.videomanager.gui.view;

import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;
import com.gamergeo.project.videomanager.gui.viewmodel.SceneViewModel;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

@FXMLView
public class SceneView extends AbstractFXMLView<SceneViewModel> {
	
	@FXML 
	private HBox root;
	
	@FXML
	private BorderPane main;
	
	public SceneView(ApplicationContext applicationContext, SceneViewModel viewModel) {
		super(applicationContext, viewModel);
	}
	
	@FXML
    public void initialize() {
    	main.setTop(getRoot(SearchView.class));
    	main.setBottom(getRoot(TableView.class));
    	main.setCenter(getRoot(ScreenView.class));
    	root.getChildren().add(getRoot(TagListView.class));
	}
}
