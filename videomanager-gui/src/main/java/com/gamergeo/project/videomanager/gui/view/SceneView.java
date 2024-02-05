package com.gamergeo.project.videomanager.gui.view;

import org.springframework.beans.factory.annotation.Autowired;

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
	
	@Autowired
	private SearchView search;

	@Autowired
	private TableView table;
	
	@Autowired
	private ScreenView screen;
		
	@FXML
    public void initialize() {
    	main.setTop(search.load().getRoot());
    	main.setBottom(table.load().getRoot());
    	main.setCenter(screen.load().getRoot());
	}
}
