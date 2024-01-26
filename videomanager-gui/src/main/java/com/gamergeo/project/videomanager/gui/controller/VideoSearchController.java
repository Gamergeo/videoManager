package com.gamergeo.project.videomanager.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.project.videomanager.gui.view.VideoSceneView;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@FXMLController
public class VideoSearchController {

	@Autowired
	private VideoSceneView videoSceneView;
	
	@FXML
	TextField titleSearchField;
	
	@FXML
	private void search() {
		log.info("Search for video");
		
		String searchTitle = titleSearchField.getText();
		videoSceneView.getController().refreshVideoList(searchTitle);
	}
	
	@FXML
	private void random() {
		log.info("Random search");
		String searchTitle = titleSearchField.getText();
		videoSceneView.getController().randomVideo(searchTitle);
	}
	
	@FXML
	private void reset() {
		log.info("Reset search");
		videoSceneView.getController().resetVideoList();
	}
	
}
