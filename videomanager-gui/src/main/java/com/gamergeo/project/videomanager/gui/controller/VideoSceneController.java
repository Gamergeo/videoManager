//package com.gamergeo.project.videomanager.gui.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//
//import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
//import com.gamergeo.project.videomanager.gui.VideoManagerApplication;
//import com.gamergeo.project.videomanager.gui.service.VideoManagerApplicationService;
//import com.gamergeo.project.videomanager.gui.view.TagListView;
//import com.gamergeo.project.videomanager.gui.view.VideoSearchView;
//import com.gamergeo.project.videomanager.gui.view.VideoTableView;
//import com.gamergeo.project.videomanager.gui.view.VideoView;
//import com.gamergeo.project.videomanager.gui.viewmodel.old.VideoViewModelOld;
//import com.gamergeo.project.videomanager.model.Video;
//import com.gamergeo.project.videomanager.service.UrlPatternService;
//import com.gamergeo.project.videomanager.service.VideoService;
//
//import javafx.fxml.FXML;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.HBox;
//import javafx.stage.Stage;
//
//@FXMLController
//public class VideoSceneController {
//	
//	@Autowired
//	@Lazy
//	private VideoManagerApplication application;
//
//	@Autowired
//	private VideoService videoService;
//	
//	@Autowired
//	private UrlPatternService urlPatternService;
//	
//	@Autowired
//	private VideoManagerApplicationService applicationService;
//	
//	@Autowired
//	VideoSearchView videoSearchView;
//	
//	@Autowired
//	VideoTableView videoTableView;
//	
//	@Autowired
//	VideoView videoView;
//	
//	@Autowired
//	TagListView tagListView;
//	
//	@FXML
//	private HBox videoSceneRoot;
//	
//	@FXML
//	private BorderPane videoBorderPane;
//	
//    @FXML
//    private void initialize() {
//    	videoBorderPane.setTop(videoSearchView.load().getRoot());
//    	videoBorderPane.setCenter(videoView.load().getRoot());
//    	videoBorderPane.setBottom(videoTableView.load().getRoot());
//    	
//    	videoSceneRoot.getChildren().add(tagListView.load().getRoot());
//    	
//    	resetVideoList();
//    }
//    
//    public void resetVideoList() {
//    	List<VideoViewModelOld> videoList = applicationService.getVideoViewModel(videoService.findAll());
//		videoTableView.getController().setVideoList(videoList);
//    }
//    
//    public void refreshVideoList(String title, Double minimalRating, List<Long> searchWithTagIds, List<Long> searchWithoutTagIds) {
//    	List<VideoViewModelOld> videoList = applicationService.getVideoViewModel(videoService.findBy(title, minimalRating, searchWithTagIds, searchWithoutTagIds));
//    	videoTableView.getController().setVideoList(videoList);
//    	videoTableView.getController().openView();
//    }
//    
//    public void refreshVideoView(VideoViewModelOld video) {
//    	videoView.getController().setVideo(video);
//    }
//    
//    public void save(VideoViewModelOld video) {
//    	videoService.save(video.getModel());
//    }
//    
//    public void reset(VideoViewModelOld video) {
//    	Video model = videoService.findById(video.getId());
//    	video.setVideo(model);
//    }
//    
//    public void disable(VideoViewModelOld video) {
//    	video.getModel().setDisabled(true);
//		videoView.getController().clear();
//    	videoTableView.getController().disable(video);
//    }
//    
//    public void randomVideo(String title, Double minimalRating, List<Long> searchWithTagIds, List<Long> searchWithoutTagIds) {
//    	Video randomVideo = videoService.randomVideo(title, minimalRating, searchWithTagIds, searchWithoutTagIds);
//    	
//    	if (randomVideo != null) {
//        	refreshVideoView(new VideoViewModelOld(randomVideo));
//    	} else {
//    		videoView.getController().clear();
//    	}
//    }
//    
//    public void unselectAllTag() {
//        tagListView.getController().unselectAllTag();
//    }
//    
//    public void clickOnLink(VideoViewModelOld video, boolean googleSearch) {
//    	if (!googleSearch) {
//    		application.getHostServices().showDocument(video.getUrl());
//    	} else {
//	    	String googleUrl = urlPatternService.getGoogleUrl(video.getModel().getTitle());
//    		application.getHostServices().showDocument(googleUrl);
//    	}
//    }
//    
//    public void setIconified(boolean iconify) {
//        Stage stage = (Stage) videoSceneRoot.getScene().getWindow();
//        stage.setIconified(true);
//    }
//    
//    /**
//     * Method called when application end
//     */
//    public void stop() {
//    	videoView.getController().clear(); // Save current video
//    }
//}
