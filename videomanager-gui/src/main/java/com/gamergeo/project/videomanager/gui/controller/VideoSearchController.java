package com.gamergeo.project.videomanager.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.lib.gamlib.javafx.controller.SceneChildController;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SceneChildController
public class VideoSearchController implements FXMLController {
	
	@Autowired
	private VideoSceneController videoSceneController;
	
	@FXML
	TextField titleSearchField;
	
	public void search() {
		log.info("Search for video");
		videoSceneController.refreshVideoList(titleSearchField.getText());
	}
	
	public void reset() {
		log.info("Reset search");
		videoSceneController.refreshVideoList();
	}
	
}
