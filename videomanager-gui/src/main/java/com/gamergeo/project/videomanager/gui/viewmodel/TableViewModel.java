package com.gamergeo.project.videomanager.gui.viewmodel;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.javafx.viewmodel.AbstractViewModel;
import com.gamergeo.project.videomanager.gui.view.TableView;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

@Component
public class TableViewModel extends AbstractViewModel<TableView> {
	
    private final ObservableList<VideoViewModel> videos = FXCollections.observableArrayList();
    
	@Override
    public void init() {
    	super.init();
    	
    	// When sceneviewmodel videos change, table is updated
    	videos.addListener((ListChangeListener.Change<? extends VideoViewModel> change) -> {
    		view.table.getItems().setAll(change.getList());
    	});
    }
    
    public void setVideos(List<VideoViewModel> videos) {
    	this.videos.setAll(videos);
    }
    
    public ObservableList<VideoViewModel> getVideos() {
    	return videos;
    }
    
    public ReadOnlyObjectProperty<VideoViewModel> selectedVideo() {
    	return view.table.getSelectionModel().selectedItemProperty();
    }
}
