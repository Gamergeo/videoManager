package com.gamergeo.project.videomanager.gui.viewmodel.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.gui.viewmodel.model.VideoViewModel;
import com.gamergeo.project.videomanager.model.Video;

import jakarta.annotation.PostConstruct;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

@Component
public class SceneViewModel {
	
	@Autowired
	private SearchViewModel search;
	
	@Autowired
	private ScreenViewModel screen;

	@Autowired
	private TableViewModel table;
	
	private final ObjectProperty<VideoViewModel> selectedVideo = new SimpleObjectProperty<VideoViewModel>();
	
	@PostConstruct
	public void init() {
		// On search change, filter table
      	search.titleProperty().addListener((observable, oldValue, newValue) -> filter());
      	search.ratingProperty().addListener((observable, oldValue, newValue) -> filter());
      	
      	search.setScene(this);
      	screen.setScene(this);
      	table.setScene(this);
      	
      	// Bind screen and table
//      	table.selectedVideoProperty().bindBidirectional(screen.selectedVideoProperty());
	}
	
	private void filter() {
		table.filter(search.getTitle(), search.getRating());
	}
	
	public void random() {
		setSelectedVideo(table.random());
	}
	
	public void refreshVideo(Video model) {
//		selectedVideo.get().setRating(model.getRating());
		table.refreshVideo(model);
	}
	
	public final ObjectProperty<VideoViewModel> selectedVideoProperty() {
		return this.selectedVideo;
	}

	public final VideoViewModel getSelectedVideo() {
		return this.selectedVideoProperty().get();
	}

	public final void setSelectedVideo(final VideoViewModel selectedVideo) {
		this.selectedVideoProperty().set(selectedVideo);
	}
	
	
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
