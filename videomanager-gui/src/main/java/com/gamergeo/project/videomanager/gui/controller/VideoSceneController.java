package com.gamergeo.project.videomanager.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.project.videomanager.gui.view.VideoSearchView;
import com.gamergeo.project.videomanager.gui.view.VideoTableView;
import com.gamergeo.project.videomanager.gui.view.VideoView;
import com.gamergeo.project.videomanager.gui.viewmodel.ApplicationViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

@FXMLController
public class VideoSceneController {
	
	@Autowired
	private ApplicationViewModel applicationViewModel;
	
	@FXML
	private GridPane mainPane;
	
	@FXML
	private TitledPane videoSearchPane;
	
	@FXML
	private TitledPane videoTablePane;
	
	@FXML
	private TitledPane videoViewPane;
	
	@Autowired
	VideoSearchView videoSearchView;
	
	@Autowired
	VideoTableView videoTableView;
	
	@Autowired
	VideoView videoView;
	
    @FXML
    private void initialize() {
    	videoSearchPane.setContent(videoSearchView.load().getRoot());
    	bindHeight(videoSearchPane);

    	videoTablePane.setContent(videoTableView.load().getRoot());
    	bindHeight(videoTablePane);

    	videoViewPane.setContent(videoView.load().getRoot());
    	bindHeight(videoViewPane);
    	
		applicationViewModel.selectedVideoProperty().addListener((o) -> openVideoView());
    }
    
    private void bindHeight(TitledPane pane) {
    	pane.heightProperty().addListener((obs, oldHeight, newHeight) -> {
    		mainPane.getScene().getWindow().sizeToScene();
//    		double totalHeight = videoTable.getHeight() + videoView.getHeight() + videoSearch.getHeight();
//    		if (applicationContent.getScene().getHeight() > applicationContent.getScene().getWindow().getHeight()) {
//    			applicationContent.getScene().getWindow().setHeight(applicationContent.getScene().getHeight());
//    		}
    	});
//    				
    }
    
    public void openVideoView() {
    	videoViewPane.setExpanded(true);
    }
}
