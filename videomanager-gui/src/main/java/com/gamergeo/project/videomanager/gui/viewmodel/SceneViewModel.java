package com.gamergeo.project.videomanager.gui.viewmodel;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.javafx.viewmodel.ViewModel;
import com.gamergeo.project.videomanager.model.Video;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

@Component
public class SceneViewModel implements ViewModel {
	
	private final SearchViewModel search;
	private final ScreenViewModel screen;
	private final TableViewModel table;
	private final TagListViewModel tagList;
	
	private final ObjectProperty<Video> selectedVideo = new SimpleObjectProperty<Video>();
	
	public SceneViewModel(SearchViewModel search, ScreenViewModel screen, TableViewModel table, TagListViewModel tagList) {
		this.search = search;
		this.screen = screen;
		this.table = table;
		this.tagList = tagList;
		
      	filter();
      	search.titleProperty().addListener((observable, oldValue, newValue) -> filter());
      	search.ratingProperty().addListener((observable, oldValue, newValue) -> filter());
      	
      	// Bind selected video and row
      	selectedVideo.addListener((observable, oldValue, newValue) -> table.setSelectedRow(selectedVideo.get()));
      	table.selectedRowProperty().addListener((observable, oldValue, newValue) ->  selectedVideo.set(table.getSelectedRow().getVideo()));
	}
	
	private void filter() {
		table.filter(search.getTitle(), search.getRating());
	}
	
	public void random() {
		setSelectedVideo(table.random());
	}
	
	public final ObjectProperty<Video> selectedVideoProperty() {
		return this.selectedVideo;
	}

	public final Video getSelectedVideo() {
		return this.selectedVideoProperty().get();
	}

	public final void setSelectedVideo(final Video selectedVideo) {
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
