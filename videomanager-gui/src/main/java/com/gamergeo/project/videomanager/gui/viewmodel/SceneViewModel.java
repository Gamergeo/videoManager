package com.gamergeo.project.videomanager.gui.viewmodel;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.service.VideoService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SceneViewModel {
	
//	private final VideoService videoService;
//	private final SearchViewModel search; 
//	private final TableViewModel table;
//	private final ScreenViewModel screen;
//	
//	private List<VideoViewModel> allVideos;
    
//	@PostConstruct
//    public void init() {
//    	allVideos = videoService.findAll().stream().map(VideoViewModel::new).collect(Collectors.toList());
//    	table.load(allVideos);
//    	
//    	// Bind screen video with table selected one
//    	screen.video().bind(table.selectedVideo());
//		
//    	// On search critera change, filter table
//    	search.titleProperty().addListener((observable, oldValue, newValue) -> filter());
//    	search.ratingProperty().addListener((observable, oldValue, newValue) -> filter());
//
//		search.setOnRandomAction((e) -> random());
//    }
	
//    public void random() {
//    	filter();
//    	
//    	Integer tableSize = table.getSize();
//    	
//    	if (tableSize > 0) {
//		    int randomNumber = new Random().nextInt(tableSize);
////		    selectedVideo.set(filteredVideos.get(randomNumber));
//    	}
//    	
//    	
////    	filter(title, minimalRating, searchWithTagIds, searchWithoutTagIds);
////    	
////	    if (!filteredVideos.isEmpty()) {
////		    int randomNumber = new Random().nextInt(filteredVideos.size());
////		    selectedVideo.set(filteredVideos.get(randomNumber));
////	    }
//    }
}
