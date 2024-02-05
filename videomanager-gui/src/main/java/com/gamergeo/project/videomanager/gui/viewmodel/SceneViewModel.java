package com.gamergeo.project.videomanager.gui.viewmodel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.viewmodel.AbstractViewModel;
import com.gamergeo.lib.gamlib.javafx.viewmodel.ViewModel;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.service.UrlPatternService;
import com.gamergeo.project.videomanager.service.VideoService;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

@ViewModel
public class SceneViewModel extends AbstractViewModel {
	
//	private VideoManagerApplication application;

	/** 
	 * Api services 
	 **/
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private UrlPatternService urlPatternService;
	
	/**
	 * View Models
	 */
	@Autowired
	private SearchViewModel search;
	
	@Autowired
	private VideoViewModel selectedVideo;
	
	@Autowired
	private TableViewModel table;
	
	@Autowired
	private TagsViewModel tags;
	
	/** 
	 * Java FX Elements 
	 **/
	@FXML 
	private HBox root;
	
	@FXML
	private BorderPane main;
	
	private List<Video> allVideos;
	
    @FXML
    private void initialize() {
    	allVideos = videoService.findAll();
    	
    	main.setTop(search.load().getRoot());
    	main.setCenter(selectedVideo.load().getRoot());
    	main.setBottom(table.load().getRoot());
    	root.getChildren().add(tags.load().getRoot());
    	
    	filter();
    }
    
    public void filter() {
    	String title = search.getTitle();
    	Double rating = search.getRating();
    	List<Tag> withTags = search.getWithTags();
    	List<Tag> withoutTags = search.getWithoutTags();
    	
    	List<Video> filteredVideos = videoService.filter(allVideos, title, rating, withTags, withoutTags);
    	table.setData(filteredVideos);
    }
    
    public void select(Video video) {
    	selectedVideo.setData(video);
    }
    
    public void random() {
    	String title = search.getTitle();
    	Double rating = search.getRating();
    	List<Tag> withTags = search.getWithTags();
    	List<Tag> withoutTags = search.getWithoutTags();
    	
    	List<Video> filteredVideos = videoService.filter(allVideos, title, rating, withTags, withoutTags);
    	
    	select(videoService.randomVideo(filteredVideos));
    }
    
    
    public void save() {
    	table.refresh();
    	videoService.save(selectedVideo.getData());
    }
    
    public void stop() {
    	
    }
    
}
