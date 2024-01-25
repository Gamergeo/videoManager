package com.gamergeo.project.videomanager.gui.controller;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLComponentController;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

@FXMLComponentController
public class VideoTagController {
	
	@FXML
	private Label videoTagLabel;
	
    public void setTag(VideoTagViewModel videoTag) {
		videoTagLabel.textProperty().bind(videoTag.textProperty());
    }
}
