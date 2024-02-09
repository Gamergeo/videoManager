//	package com.gamergeo.project.videomanager.gui.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.controlsfx.control.Rating;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
//import com.gamergeo.project.videomanager.gui.service.VideoManagerApplicationService;
//import com.gamergeo.project.videomanager.gui.view.VideoSceneView;
//import com.gamergeo.project.videomanager.service.TagService;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.TextField;
//import javafx.scene.input.DragEvent;
//import javafx.scene.input.TransferMode;
//import javafx.scene.layout.TilePane;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@FXMLController
//public class VideoSearchController {
//
//	@Autowired
//	private VideoSceneView videoSceneView;
//	
//	@Autowired
//	private VideoManagerApplicationService applicationService;
//	
//	@Autowired
//	private TagService tagService;
//	
//	@FXML
//	TextField titleSearchField;
//	
//	@FXML
//	Rating searchRating;
//	
//	@FXML
//	TilePane withFlowPane;
//	
//	@FXML
//	TilePane withoutFlowPane;
//	
//	private List<Long> searchWithTagIds = new ArrayList<Long>();
//	
//	private List<Long> searchWithoutTagIds  = new ArrayList<Long>();
//	
//	@FXML
//	private void initialize() {
//		searchRating.setRating(0d);
//		applicationService.semiValueRating(searchRating);
//	}
//	
//	@FXML
//	private void search() {
//		log.info("Search for video");
//		videoSceneView.getController().refreshVideoList(titleSearchField.getText(), searchRating.getRating(), searchWithTagIds, searchWithoutTagIds);
//	}
//	
//	@FXML
//	private void random() {
//		log.info("Random search");
//		videoSceneView.getController().randomVideo(titleSearchField.getText(), searchRating.getRating(), searchWithTagIds, searchWithoutTagIds);
//	}
//	
//	@FXML
//	private void reset() {
//		log.info("Reset search");
//		titleSearchField.setText("");
//		withFlowPane.getChildren().clear();
//		withoutFlowPane.getChildren().clear();
//		searchWithTagIds = new ArrayList<Long>();
//		searchWithoutTagIds  = new ArrayList<Long>();
//		searchRating.setRating(0d);
//		
//		videoSceneView.getController().resetVideoList();
//	}
//	
//	@FXML
//	private void dragOver(DragEvent event) {
//        if (event.getDragboard().hasString()) {
//            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
//        }
//
//        event.consume();
//	}
//
//	@FXML
//	private void dropTagWith(DragEvent event) {
//        dropTags(true, event);
//	}
//
//	@FXML
//	private void dropTagWithout(DragEvent event) {
//        dropTags(false, event);
//	}
//	
//	private void dropTags(boolean isWithPane, DragEvent event) {
//        if (event.getDragboard().hasString()) {
//        	String content = event.getDragboard().getString();
//	        log.info("Dropped on search: " + content);
//	        
//	        List<Long> idList = applicationService.getIdFromData(content);
//	        
//	        idList.forEach((id) -> {
//	        	if (!getSearchVideoTagIds(isWithPane).contains(id)) {
//	        		applicationService.addTagToNode(getPane(isWithPane), tagService.findById(id));
//	        		getSearchVideoTagIds(isWithPane).add(id);
//	        	}
//	        });
//	        videoSceneView.getController().unselectAllTag();
//            event.setDropCompleted(true);
//        } else {
//            event.setDropCompleted(false);
//        }
//        event.consume();
//	}
//	
//	private TilePane getPane(boolean isWithPane) {
//		return isWithPane ? withFlowPane : withoutFlowPane;
//	}
//	
//	private List<Long> getSearchVideoTagIds(boolean isWithPane) {
//		return isWithPane ? searchWithTagIds : searchWithoutTagIds;
//	}
//	
//}