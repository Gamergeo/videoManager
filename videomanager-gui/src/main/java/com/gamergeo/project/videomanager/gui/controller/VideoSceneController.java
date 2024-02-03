package com.gamergeo.project.videomanager.gui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.project.videomanager.gui.service.VideoManagerApplicationService;
import com.gamergeo.project.videomanager.gui.view.VideoSearchView;
import com.gamergeo.project.videomanager.gui.view.VideoTableView;
import com.gamergeo.project.videomanager.gui.view.TagListView;
import com.gamergeo.project.videomanager.gui.view.VideoView;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;
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
    
    public void refreshVideoList(String title, List<Long> searchWithTagIds, List<Long> searchWithoutTagIds) {
    	List<VideoViewModel> videoList = applicationService.getVideoViewModel(videoService.findBy(title, searchWithTagIds, searchWithoutTagIds));
    	videoTableView.getController().setVideoList(videoList);
    	videoTableView.getController().openView();
    }
    
    public void refreshVideoView(VideoViewModel video) {
    	videoView.getController().setVideo(video);
    }
    
    public void save(VideoViewModel video) {
    	videoService.save(video.getVideo());
    }
    
    public void randomVideo(String title) {
    	VideoViewModel randomVideo = new VideoViewModel(videoService.randomVideo(title));
    	refreshVideoView(randomVideo);
    }
    
    public void unselectAllTag() {
        tagListView.getController().unselectAllTag();
    }
}
