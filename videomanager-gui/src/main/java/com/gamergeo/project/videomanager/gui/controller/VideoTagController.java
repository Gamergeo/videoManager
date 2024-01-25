package com.gamergeo.project.videomanager.gui.controller;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.lib.gamlib.javafx.controller.FXMLSceneComponentController;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

@FXMLSceneComponentController
public class VideoTagController implements FXMLController {
	
	@FXML
	private Label videoTagLabel;
	
    public void setTag(VideoTagViewModel videoTag) {
		videoTagLabel.textProperty().bind(videoTag.textProperty());
    }
}
