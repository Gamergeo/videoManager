package com.gamergeo.project.videomanager.gui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.project.videomanager.gui.service.VideoManagerApplicationService;
import com.gamergeo.project.videomanager.gui.view.VideoSceneView;
import com.gamergeo.project.videomanager.gui.view.VideoTagListView;
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
	private VideoTagListView videoTagListView;
	
	@Autowired
	private VideoManagerApplicationService applicationService;
	
	@Autowired
	private VideoTagService videoTagService;
	
	@FXML
	TextField titleSearchField;
	
	@FXML
	FlowPane withFlowPane;
	
	@FXML
	FlowPane withoutFlowPane;
	
	private List<Long> searchWithTagIds = new ArrayList<Long>();
	
	private List<Long> searchWithoutTagIds  = new ArrayList<Long>();
	
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
        dropTags(true, event);
	}

	@FXML
	private void dropTagWithout(DragEvent event) {
        dropTags(false, event);
	}
	
	private void dropTags(boolean isWithPane, DragEvent event) {
        if (event.getDragboard().hasString()) {
        	String content = event.getDragboard().getString();
	        log.info("Dropped: " + content);
	        
	        List<Long> idList = applicationService.getIdFromData(content);
	        
	        idList.forEach((id) -> {
	        	if (!getSearchVideoTagIds(isWithPane).contains(id)) {
	        		applicationService.addTagToNode(getPane(isWithPane), videoTagService.findById(id));
	        		getSearchVideoTagIds(isWithPane).add(id);
	        	}
	        });
	        videoTagListView.getController().unselectAllTag();
            event.setDropCompleted(true);
        } else {
            event.setDropCompleted(false);
        }
        event.consume();
	}
	
	private FlowPane getPane(boolean isWithPane) {
		return isWithPane ? withFlowPane : withoutFlowPane;
	}
	
	private List<Long> getSearchVideoTagIds(boolean isWithPane) {
		return isWithPane ? searchWithTagIds : searchWithoutTagIds;
	}
	
}
