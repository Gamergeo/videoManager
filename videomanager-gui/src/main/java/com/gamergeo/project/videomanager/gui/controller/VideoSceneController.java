package com.gamergeo.project.videomanager.gui.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;

import com.gamergeo.project.videomanager.gui.application.VideoManagerGuiApplication;

import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class VideoSceneController {
		
	@FXML
	private GridPane applicationContent;
	
	@FXML
	private TitledPane videoSearch;
	
	@FXML
	private TitledPane videoTable;
	
	@FXML
	private TitledPane videoView;
	
    @FXML
    private void initialize() throws IOException {
    	
    	loadTitledPane(videoTable);
    	loadTitledPane(videoView);
    	loadTitledPane(videoSearch);
//    	videoTableController.getVideo();
    }
    
    private void loadTitledPane(TitledPane pane) throws IOException {
    	pane.setContent(VideoManagerGuiApplication.load(pane.getId()));
    	pane.heightProperty().addListener((obs, oldHeight, newHeight) -> {
    		applicationContent.getScene().getWindow().sizeToScene();
//    		double totalHeight = videoTable.getHeight() + videoView.getHeight() + videoSearch.getHeight();
//    		if (applicationContent.getScene().getHeight() > applicationContent.getScene().getWindow().getHeight()) {
//    			applicationContent.getScene().getWindow().setHeight(applicationContent.getScene().getHeight());
//    		}
    	});
//    				
    }
    
    public void openVideoView() {
    	videoView.setExpanded(true);
    }
    	
    	
    	
    	
//    	videoView.setContent(new Label("Bah"));
    	
//    	GridPane personPane = (GridPane) loadElement("Person");
//
//        // Bind selected
//        personScene.getSelectedPersonProperty().addListener((observable, oldValue, person)->{
//        	// Premier passage, on ajoute l'enfant
//    		if (oldValue == null) {
//    			applicationContent.getChildren().add(personPane);
//    		}
//        });
    
//    private Node loadElement(String fxmlFile) throws IOException {
//        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("/fxml/" + fxmlFile + ".fxml"));
//        Node node = loader.load();
//        ((IPersonModuleController) loader.getController()).setPersonScene(personScene);
//        return node;
//    }
//    
//    private Node loadAndAddElement(String fxmlFile) throws IOException {
//    	Node node = loadElement(fxmlFile);
//        applicationContent.getChildren().add(node);
//        return node;
//    }
}
