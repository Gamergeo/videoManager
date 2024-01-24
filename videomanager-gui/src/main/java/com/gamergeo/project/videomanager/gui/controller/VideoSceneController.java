package com.gamergeo.project.videomanager.gui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.lib.gamlib.javafx.controller.SceneController;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;
import com.gamergeo.project.videomanager.gui.loader.VideoManagerLoader;
import com.gamergeo.project.videomanager.gui.mapper.VideoMapper;
import com.gamergeo.project.videomanager.gui.view.VideoSceneView;
import com.gamergeo.project.videomanager.gui.view.VideoSearchView;
import com.gamergeo.project.videomanager.gui.view.VideoTableView;
import com.gamergeo.project.videomanager.gui.view.VideoView;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;
import com.gamergeo.project.videomanager.service.VideoService;

import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

@SceneController
public class VideoSceneController implements FXMLController {
	
	@Autowired
	VideoManagerLoader loader;
	
	@Autowired
	private VideoMapper videoMapper;
	
	@Autowired
	private VideoService videoService;
	
	@FXML
	private GridPane applicationContent;
	
	@FXML
	private TitledPane videoSearch;
	
	@FXML
	private TitledPane videoTable;
	
	@FXML
	private TitledPane videoView;
	
	@Autowired
	@Lazy
	private VideoSearchController videoSearchController;
	
	@Autowired
	@Lazy
	private VideoTableController videoTableController;
	
	@Autowired
	@Lazy
	private VideoViewController videoViewController;
	
    @FXML
    private void initialize() {
    	
    	FXMLView videoSearchView = loader.createView(VideoSearchView.class);
    	videoSearch.setContent(videoSearchView.getRoot());
    	bindHeight(videoSearch);
    	
    	FXMLView videoTableView = loader.createView(VideoTableView.class);
    	videoTable.setContent(videoTableView.getRoot());
    	bindHeight(videoTable);
    	
    	FXMLView videoViewView = loader.createView(VideoView.class);
    	videoView.setContent(videoViewView.getRoot());
    	bindHeight(videoView);
    	refreshVideoList();
    }
    
    private void bindHeight(TitledPane pane) {
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
    
    public void refreshVideoList() {
    	List<VideoViewModel> videoList = videoMapper.getViewModels(videoService.getVideoList());
    	videoTableController.setVideoList(videoList);
    }
    
    public void refreshVideoList(String title) {
    	List<VideoViewModel> videoList = videoMapper.getViewModels(videoService.getVideoList(title));
    	videoTableController.setVideoList(videoList);
    }
    
    public void refreshVideoView(VideoViewModel video) {
    	openVideoView();
		videoViewController.setVideo(video);
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
