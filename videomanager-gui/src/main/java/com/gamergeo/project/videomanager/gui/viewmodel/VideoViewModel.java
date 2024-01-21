package com.gamergeo.project.videomanager.gui.viewmodel;

import java.util.List;

import com.gamergeo.lib.gamlib.gui.viewmodel.ViewModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VideoViewModel implements ViewModel {
	
    private final SimpleStringProperty title = new SimpleStringProperty();
    private final SimpleStringProperty url = new SimpleStringProperty();
    
	private ObservableList<VideoTagViewModel> videoTagList = FXCollections.observableArrayList();
    
    public SimpleStringProperty titleProperty() {
        return title;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }
    
	public ObservableList<VideoTagViewModel> getVideoTagList() {
		return videoTagList;
	}

	public void setVideoTagList(List<VideoTagViewModel> videoTagList) {
		this.videoTagList.addAll(videoTagList);
	}
	
	public String getVideoTags() {
		String videoTags = "";
		
		for (VideoTagViewModel videoTag : getVideoTagList()) {
			if (videoTags.isEmpty()) {
				videoTags += videoTag.getText();
			} else {
				videoTags += " / " + videoTag.getText();
			}
		}
		return videoTags;
	}

	public final SimpleStringProperty urlProperty() {
		return this.url;
	}
	
	public final String getUrl() {
		return this.urlProperty().get();
	}
	
	public final void setUrl(final String url) {
		this.urlProperty().set(url);
	}
}
