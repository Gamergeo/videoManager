package com.gamergeo.project.videomanager.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.project.videomanager.gui.view.VideoSceneView;
import com.gamergeo.project.videomanager.gui.view.VideoTagView;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;
import com.gamergeo.project.videomanager.service.VideoTagService;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@FXMLController
public class VideoSearchController {

	@Autowired
	private VideoSceneView videoSceneView;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private VideoTagService videoTagService;
	
	@FXML
	TextField titleSearchField;
	
	@FXML
	FlowPane withFlowPane;
	
	@FXML
	FlowPane withoutFlowPane;
	
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
	
	@FXML
	private void dragOver(DragEvent event) {
//        if (event.getGestureSource() != circle2 && event.getDragboard().hasString()) {
        if (event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }

        event.consume();
	}

	@FXML
	private void dropTagWith(DragEvent event) {
        if (event.getDragboard().hasString()) {
            System.out.println("Dropped: " + event.getDragboard().getString());
            event.setDropCompleted(true);
            
        	VideoTagViewModel tag = new VideoTagViewModel(videoTagService.findById(Long.valueOf(event.getDragboard().getString())));
	   		VideoTagView tagView = applicationContext.getBean(VideoTagView.class);
	   			tagView.load();
	   			tagView.getController().setTag(tag);
	   			withFlowPane.getChildren().add(tagView.getRoot());
        } else {
            event.setDropCompleted(false);
        }
        event.consume();
	}

	@FXML
	private void dropTagWithout(DragEvent event) {
        if (event.getDragboard().hasString()) {
            System.out.println("Dropped: " + event.getDragboard().getString());
            event.setDropCompleted(true);
            
        	VideoTagViewModel tag = new VideoTagViewModel(videoTagService.findById(Long.valueOf(event.getDragboard().getString())));
	   		VideoTagView tagView = applicationContext.getBean(VideoTagView.class);
	   			tagView.load();
	   			tagView.getController().setTag(tag);
	   			withoutFlowPane.getChildren().add(tagView.getRoot());
        } else {
            event.setDropCompleted(false);
        }
        event.consume();
	}
}
