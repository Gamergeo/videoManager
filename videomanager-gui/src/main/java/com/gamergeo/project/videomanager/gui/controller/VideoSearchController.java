package com.gamergeo.project.videomanager.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.project.videomanager.gui.viewmodel.ApplicationViewModel;
import com.gamergeo.project.videomanager.service.VideoService;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@FXMLController
public class VideoSearchController {
	
	@Autowired
	ApplicationViewModel applicationViewModel;
	
	@Autowired
	VideoService videoService;
	
	@FXML
	TextField titleSearchField;
	
	@FXML
	private void initialize() {
		applicationViewModel.setVideoList(videoService.getVideoList());
	}
	
	public void search() {
		log.info("Search for video");
		
		String searchTitle = titleSearchField.getText();
		applicationViewModel.setVideoList(videoService.getVideoList(searchTitle));
	}
	
	public void reset() {
		log.info("Reset search");
		
		applicationViewModel.setVideoList(videoService.getVideoList());
	}
}
