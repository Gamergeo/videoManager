package com.gamergeo.project.videomanager.gui.controller;

import java.util.List;

import org.controlsfx.control.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.SerializationUtils;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.project.videomanager.gui.service.VideoManagerApplicationService;
import com.gamergeo.project.videomanager.gui.view.VideoSceneView;
import com.gamergeo.project.videomanager.gui.viewmodel.TagViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.service.TagService;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.util.converter.LocalDateStringConverter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@FXMLController
public class VideoController {
	
	@Autowired
	private VideoSceneView videoSceneView;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private VideoManagerApplicationService applicationService;

	@FXML
	private Label videoTitleLabel;
	
	@FXML
	private Hyperlink videoUrl;
	
	@FXML
	private Label videoAddedDateLabel;
	
	@FXML
	private Rating videoRating;
	
	@FXML
	private TilePane tagsPane;
	
	@FXML
	private HBox videoButtons;
	
	private VideoViewModel selectedVideo;
	
	private Video initialVideo;
	
	private ListChangeListener<TagViewModel> tagListChangeListener = change -> {
	    tagsPane.getChildren().clear();
	    applicationService.addTagsToNode(tagsPane, selectedVideo.getTags());
	};
	
	@FXML
	private void initialize() {
		videoUrl.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> 
									videoSceneView.getController().clickOnLink(selectedVideo, event.isControlDown()));
		
		videoUrl.setVisible(false);
		videoRating.setVisible(false);
		
		applicationService.semiValueRating(videoRating);
	}
	
	public void setVideo(VideoViewModel video) {
		log.info("Change video view infos: " + video.getId());

		// Attention à bien unbind avant de changer la vidéo selectionné ou la table sera actualisé en conséquence
		clear();
		
		this.initialVideo = (Video) SerializationUtils.clone(video.getModel());
		this.selectedVideo = video;
		videoTitleLabel.textProperty().bindBidirectional(selectedVideo.titleProperty());
		videoAddedDateLabel.textProperty().bindBidirectional(selectedVideo.addedDateProperty(), new LocalDateStringConverter());
		videoRating.ratingProperty().bindBidirectional(selectedVideo.ratingProperty());
		
		setVisible(true);
		
		applicationService.addTagsToNode(tagsPane, video.getTags());
		
		selectedVideo.getTags().addListener(tagListChangeListener);
	}
	
	public void clear() {
		// Attention à bien unbind avant de changer la vidéo selectionné ou la table sera actualisé en conséquence
		if (selectedVideo != null) {
			videoSceneView.getController().save(selectedVideo);
			videoTitleLabel.textProperty().unbindBidirectional(selectedVideo.titleProperty());
			videoAddedDateLabel.textProperty().unbindBidirectional(selectedVideo.addedDateProperty());
			videoRating.ratingProperty().unbindBidirectional(selectedVideo.ratingProperty());
			selectedVideo.getTags().removeListener(tagListChangeListener);
		}
		
		setVisible(false);

		videoTitleLabel.setText("");
		videoAddedDateLabel.setText("");
		tagsPane.getChildren().clear();
		
		this.initialVideo = null;
	}
	
	private void setVisible(boolean visible) {
		videoUrl.setVisible(visible);
		videoRating.setVisible(visible);
		videoButtons.setVisible(visible);
	}
	
	@FXML
	private void reset() {
		if (initialVideo == null) {
			log.error("Reset impossible !");
		} else {
			selectedVideo.setVideo(initialVideo);
		}
	}
	
	@FXML
	private void disable() {
		videoSceneView.getController().disable(selectedVideo);
	}
	
	@FXML
	private void dragOver(DragEvent event) {
        if (event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
	}

	@FXML
	private void dropTags(DragEvent event) {
        if (event.getDragboard().hasString()) {
        	String content = event.getDragboard().getString();
	        log.info("Dropped on video: " + content);
	        
	        List<Long> idList = applicationService.getIdFromData(content);
	        
	        idList.forEach((id) -> {
	        	if (!selectedVideo.getTagListIds().contains(id)) {
	        		TagViewModel tag = new TagViewModel(tagService.findById(id));
	        		selectedVideo.getTags().add(tag);
	        	}
	        });
	        videoSceneView.getController().unselectAllTag();
            event.setDropCompleted(true);
        } else {
            event.setDropCompleted(false);
        }
        event.consume();
	}
}

