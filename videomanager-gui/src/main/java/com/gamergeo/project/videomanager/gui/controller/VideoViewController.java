package com.gamergeo.project.videomanager.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.project.videomanager.gui.view.VideoSceneView;
import com.gamergeo.project.videomanager.gui.view.VideoTagView;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;

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
	private VideoSceneView videoSceneView;
	
	@FXML
	private TextField videoTitleField;
	
	@FXML
	private TextField videoUrlField;
	
	@FXML
	private Label addedDateLabel;
	
	@FXML
	private FlowPane videoTagsPane;
	
	@Autowired
	ApplicationContext applicationContext;
	
	private VideoViewModel selectedVideo;
	
	public void setVideo(VideoViewModel video) {
		log.info("Change video view infos: " + video.getId());

		// Attention à bien unbind avant de changer la vidéo selectionné ou la table sera actualisé en conséquence
		if (selectedVideo != null) {
			videoTitleField.textProperty().unbindBidirectional(selectedVideo.titleProperty());
			videoUrlField.textProperty().unbindBidirectional(selectedVideo.urlProperty());
			addedDateLabel.textProperty().unbindBidirectional(selectedVideo.addedDateProperty());
		}
		
		this.selectedVideo = video;
		videoTitleField.textProperty().bindBidirectional(selectedVideo.titleProperty());
		videoUrlField.textProperty().bindBidirectional(selectedVideo.urlProperty());
		addedDateLabel.textProperty().bindBidirectional(selectedVideo.addedDateProperty(), new LocalDateStringConverter());
		
		videoTagsPane.getChildren().clear();
		
		video.getVideoTagList().forEach((videoTag) -> {
			VideoTagView videoTagView = applicationContext.getBean(VideoTagView.class);
			videoTagView.load();
			videoTagView.getController().setTag(videoTag);
			videoTagView.getRoot().getStyleClass().add("videoTagComponent");
			videoTagsPane.getChildren().add(videoTagView.getRoot());
		});
	}
	
	@FXML
	private void save() {
		videoSceneView.getController().save(selectedVideo);
	}
}

