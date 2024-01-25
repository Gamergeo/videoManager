package com.gamergeo.project.videomanager.gui.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.project.videomanager.gui.view.VideoSearchView;
import com.gamergeo.project.videomanager.gui.view.VideoTableView;
import com.gamergeo.project.videomanager.gui.view.VideoView;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;
import com.gamergeo.project.videomanager.service.VideoService;

import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

@FXMLController
public class VideoSceneController {

	@Autowired
	private VideoService videoService;
	
	@FXML
	private GridPane mainPane;
	
	@FXML
	private TitledPane videoSearchPane;
	
	@FXML
	private TitledPane videoTablePane;
	
	@FXML
	private TitledPane videoViewPane;
	
	@Autowired
	VideoSearchView videoSearchView;
	
	@Autowired
	VideoTableView videoTableView;
	
	@Autowired
	VideoView videoView;
	
    @FXML
    private void initialize() {
    	videoSearchPane.setContent(videoSearchView.load().getRoot());
    	bindHeight(videoSearchPane);

    	videoTablePane.setContent(videoTableView.load().getRoot());
    	bindHeight(videoTablePane);

    	videoViewPane.setContent(videoView.load().getRoot());
    	bindHeight(videoViewPane);
    	
    	refreshVideoList();
    }
    
    private void bindHeight(TitledPane pane) {
    	pane.heightProperty().addListener((obs, oldHeight, newHeight) -> {
    		mainPane.getScene().getWindow().sizeToScene();
//    		double totalHeight = videoTable.getHeight() + videoView.getHeight() + videoSearch.getHeight();
//    		if (applicationContent.getScene().getHeight() > applicationContent.getScene().getWindow().getHeight()) {
//    			applicationContent.getScene().getWindow().setHeight(applicationContent.getScene().getHeight());
//    		}
    	});
//    				
    }
    
    public void openVideoView() {
    	videoViewPane.setExpanded(true);
    }
    
    public void refreshVideoList() {
    	List<VideoViewModel> videoList = videoService.findAll()
													 .stream()
													 .map((video) -> new VideoViewModel(video))
													 .collect(Collectors.toList());
    	videoTableView.getController().setVideoList(videoList);
    }
    
    public void refreshVideoList(String title) {
    	List<VideoViewModel> videoList = videoService.findBy(title)
				 .stream()
				 .map((video) -> new VideoViewModel(video))
				 .collect(Collectors.toList());
    	videoTableView.getController().setVideoList(videoList);
    }
    
    public void refreshVideoView(VideoViewModel video) {
    	openVideoView();
    	videoView.getController().setVideo(video);
    }
    
    public void save(VideoViewModel video) {
    	videoService.save(video.getVideo());
    }
    
    public void randomVideo(String title) {
    	VideoViewModel randomVideo = new VideoViewModel(videoService.randomVideo(title));
    	refreshVideoView(randomVideo);
    }
    	
}
