package com.gamergeo.project.videomanager.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.project.videomanager.gui.VideoManagerApplication;
import com.gamergeo.project.videomanager.gui.service.VideoManagerApplicationService;
import com.gamergeo.project.videomanager.gui.view.VideoSceneView;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.util.converter.LocalDateStringConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@FXMLController
public class VideoController {
	
	@Autowired
	@Lazy
	private VideoManagerApplication application;
	
	@Autowired
	private VideoSceneView videoSceneView;
	
	@Autowired
	private VideoManagerApplicationService applicationService;

	@FXML
	private Label videoTitleLabel;
	
	@FXML
	private Hyperlink videoUrl;
	
	@FXML
	private Label addedDateLabel;
	
	@FXML
	private FlowPane tagsPane;
	
	private VideoViewModel selectedVideo;
	
	@FXML
	private void initialize() {
		videoUrl.setOnAction(a->application.getHostServices().showDocument(selectedVideo.getUrl()));
	}
	
	public void setVideo(VideoViewModel video) {
		log.info("Change video view infos: " + video.getId());

		// Attention à bien unbind avant de changer la vidéo selectionné ou la table sera actualisé en conséquence
		if (selectedVideo != null) {
			videoTitleLabel.textProperty().unbindBidirectional(selectedVideo.titleProperty());
			addedDateLabel.textProperty().unbindBidirectional(selectedVideo.addedDateProperty());
		}
		
		this.selectedVideo = video;
		videoTitleLabel.textProperty().bindBidirectional(selectedVideo.titleProperty());
		addedDateLabel.textProperty().bindBidirectional(selectedVideo.addedDateProperty(), new LocalDateStringConverter());
		
		tagsPane.getChildren().clear();
		
		applicationService.addTagsToNode(tagsPane, video.getTagList());
	}
	
	@FXML
	private void save() {
		videoSceneView.getController().save(selectedVideo);
	}
}

