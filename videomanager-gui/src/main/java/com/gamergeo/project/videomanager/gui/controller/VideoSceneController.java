package com.gamergeo.project.videomanager.gui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.project.videomanager.gui.service.VideoManagerApplicationService;
import com.gamergeo.project.videomanager.gui.view.TagListView;
import com.gamergeo.project.videomanager.gui.view.VideoSearchView;
import com.gamergeo.project.videomanager.gui.view.VideoTableView;
import com.gamergeo.project.videomanager.gui.view.VideoView;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.service.VideoService;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

@FXMLController
public class VideoSceneController {

	@Autowired
	private VideoService videoService;
	
	@Autowired
	private VideoManagerApplicationService applicationService;
	
	@Autowired
	VideoSearchView videoSearchView;
	
	@Autowired
	VideoTableView videoTableView;
	
	@Autowired
	VideoView videoView;
	
	@Autowired
	TagListView tagListView;
	
	@FXML
	private HBox videoSceneRoot;
	
	@FXML
	private BorderPane videoBorderPane;
	
    @FXML
    private void initialize() {
    	videoBorderPane.setTop(videoSearchView.load().getRoot());
    	videoBorderPane.setCenter(videoView.load().getRoot());
    	videoBorderPane.setBottom(videoTableView.load().getRoot());
    	
    	videoSceneRoot.getChildren().add(tagListView.load().getRoot());
    	
    	resetVideoList();
    }
    
    public void resetVideoList() {
    	List<VideoViewModel> videoList = applicationService.getVideoViewModel(videoService.findAll());
		videoTableView.getController().setVideoList(videoList);
    }
    
    public void refreshVideoList(String title, Double minimalRating, List<Long> searchWithTagIds, List<Long> searchWithoutTagIds) {
    	List<VideoViewModel> videoList = applicationService.getVideoViewModel(videoService.findBy(title, minimalRating, searchWithTagIds, searchWithoutTagIds));
    	videoTableView.getController().setVideoList(videoList);
    	videoTableView.getController().openView();
    }
    
    public void refreshVideoView(VideoViewModel video) {
    	videoView.getController().setVideo(video);
    }
    
    public void save(VideoViewModel video) {
    	videoService.save(video.getModel());
    }
    
    public void reset(VideoViewModel video) {
    	Video model = videoService.findById(video.getId());
    	video.setVideo(model);
    }
    
    public void disable(VideoViewModel video) {
    	video.getModel().setDisabled(true);
    	videoService.save(video.getModel());
    	videoView.getController().clear();
    	videoTableView.getController().disable(video);
    }
    
    public void randomVideo(String title, Double minimalRating, List<Long> searchWithTagIds, List<Long> searchWithoutTagIds) {
    	Video randomVideo = videoService.randomVideo(title, minimalRating, searchWithTagIds, searchWithoutTagIds);
    	
    	if (randomVideo != null) {
        	refreshVideoView(new VideoViewModel(randomVideo));
    	} else {
    		videoView.getController().clear();
    	}
    }
    
    public void unselectAllTag() {
        tagListView.getController().unselectAllTag();
    }
}
