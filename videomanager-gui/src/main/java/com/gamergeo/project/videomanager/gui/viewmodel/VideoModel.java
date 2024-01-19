package com.gamergeo.project.videomanager.gui.viewmodel;

import java.util.ArrayList;
import java.util.List;

import com.gamergeo.project.videomanager.model.Video;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class VideoModel {
	
    private final SimpleStringProperty title = new SimpleStringProperty();
    private final SimpleStringProperty link = new SimpleStringProperty();
    private final SimpleBooleanProperty bestof = new SimpleBooleanProperty();
    private final SimpleBooleanProperty disabled = new SimpleBooleanProperty();
    
    public VideoModel(Video video) {
    	this.setTitle(video.getTitle());
    	this.setLink(video.getLink());
    	this.setBestof(video.isBestof());
    	this.setDisabled(video.isDisabled());
    }
    
    public static List<VideoModel> fromModels(List<Video> models) {
    	List<VideoModel> viewModels =  new ArrayList<VideoModel>();
    	for(Video model : models) {
    		viewModels.add(new VideoModel(model));
    	}
    	
    	return viewModels;
    }
    
    public SimpleStringProperty titleProperty() {
        return title;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }
    
    public SimpleStringProperty linkProperty() {
        return link;
    }

	public String getLink() {
		return link.get();
	}
	
    public void setLink(String link) {
        this.link.set(link);
    }
    
    public SimpleBooleanProperty bestofProperty() {
        return bestof;
    }
    
	public Boolean isBestof() {
		return bestof.get();
	}
	
    public void setBestof(Boolean bestof) {
        this.bestof.set(bestof);
    }
    
    public SimpleBooleanProperty disabledProperty() {
        return disabled;
    }
    
	public Boolean isDisabled() {
		return disabled.get();
	}
	
    public void setDisabled(Boolean disabled) {
        this.disabled.set(disabled);
    }
}
