package com.gamergeo.project.videomanager.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.project.videomanager.gui.view.VideoTagView;
import com.gamergeo.project.videomanager.gui.viewmodel.ApplicationViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;
import com.gamergeo.project.videomanager.service.VideoService;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.util.converter.LocalDateStringConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@FXMLController
public class VideoViewController {
	
	@Autowired
	VideoService videoService;
	
	@Autowired
	ApplicationViewModel applicationViewModel;
	
	@FXML
	private TextField videoTitleField;
	
	@FXML
	private TextField videoUrlField;
	
	@FXML
	private Label addedDateLabel;
	
	@FXML
	private FlowPane videoTagsPane;
	
	@FXML
	private void initialize() {
		applicationViewModel.selectVideoProperty().addListener((o) -> unbind());
		applicationViewModel.selectedVideoProperty().addListener((o) -> render());
	}
	
	private void unbind() {
		VideoViewModel selectedVideo = applicationViewModel.getSelectedVideo();
		videoTitleField.textProperty().unbindBidirectional(selectedVideo.titleProperty());
		videoUrlField.textProperty().unbindBidirectional(selectedVideo.urlProperty());
		addedDateLabel.textProperty().unbindBidirectional(selectedVideo.addedDateProperty());
	}
	
	private void render() {
		VideoViewModel selectedVideo = applicationViewModel.getSelectedVideo();
		VideoViewModel video = applicationViewModel.getVideo();
		log.info("Render video: " + selectedVideo.getId());
		
		if (video != null) {
			videoTitleField.textProperty().unbindBidirectional(video.titleProperty());
			videoUrlField.textProperty().unbindBidirectional(video.urlProperty());
			addedDateLabel.textProperty().unbindBidirectional(video.addedDateProperty());
		}
		
		video = new VideoViewModel(selectedVideo.getVideo());
		videoTitleField.textProperty().bindBidirectional(video.titleProperty());
		videoUrlField.textProperty().bindBidirectional(video.urlProperty());
		addedDateLabel.textProperty().bindBidirectional(video.addedDateProperty(), new LocalDateStringConverter());
		
		videoTagsPane.getChildren().clear();
		
//		for (VideoTagViewModel videoTag : videoView.getVideoTagList()) {
//			VideoTagView videoTagView = applicationContext.getBean(VideoTagView.class);
//			videoTagView.load();
//			videoTagView.getController().setTag(videoTag);
//			videoTagView.getRoot().getStyleClass().add("videoTagComponent");
//			videoTagsPane.getChildren().add(videoTagView.getRoot());
//		}
	}
	
	@FXML
	private void save() {
		videoService.save(applicationViewModel.getSelectedVideo().getVideo());
	}
}

