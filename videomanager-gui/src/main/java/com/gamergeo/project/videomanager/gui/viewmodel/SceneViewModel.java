package com.gamergeo.project.videomanager.gui.viewmodel;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.javafx.viewmodel.AbstractViewModel;
import com.gamergeo.project.videomanager.gui.view.SceneView;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.service.VideoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor_ ={@Lazy})
public class SceneViewModel extends AbstractViewModel<SceneView> {
	
	private final VideoService videoService;
	
	private final SearchViewModel search; 
	private final TableViewModel table;
	private final ScreenViewModel screen;
	
	private List<Video> allVideos;
    
	@Override
    public void init() {
    	super.init();
    	
    	view.main.setTop(search.getRoot());
    	view.main.setBottom(table.getRoot());
    	view.main.setCenter(screen.getRoot());
    	
    	allVideos = videoService.findAll();
    	table.setVideos(toViewModels(allVideos));
    	
    	// Bind screen video with table selected one
    	screen.video().bind(table.selectedVideo());
    }
    
//    public void random(String title, Double minimalRating, List<Tag> searchWithTagIds, List<Tag> searchWithoutTagIds) {
//    	filter(title, minimalRating, searchWithTagIds, searchWithoutTagIds);
//    	
//	    if (!filteredVideos.isEmpty()) {
//		    int randomNumber = new Random().nextInt(filteredVideos.size());
//		    selectedVideo.set(filteredVideos.get(randomNumber));
//	    }
//    }
    
    private List<VideoViewModel> toViewModels(List<Video> models) {
    	return models.stream().map(VideoViewModel::new).collect(Collectors.toList());
    }
}
