package com.gamergeo.project.videomanager.gui.viewmodel;

import java.time.LocalDate;
import java.util.List;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VideoViewModel {
	
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty url = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> addedDate = new SimpleObjectProperty<LocalDate>();
    
	private ObservableList<VideoTagViewModel> videoTagList = FXCollections.observableArrayList();
    
    public StringProperty titleProperty() {
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

	public final StringProperty urlProperty() {
		return this.url;
	}
	
	public final String getUrl() {
		return this.urlProperty().get();
	}
	
	public final void setUrl(final String url) {
		this.urlProperty().set(url);
	}

	public final ObjectProperty<LocalDate> addedDateProperty() {
		return this.addedDate;
	}

	public final String getAddedDate() {
		return this.addedDateProperty().get().toString();
	}
	
	public final void setAddedDate(final LocalDate addedDate) {
		this.addedDateProperty().set(addedDate);
	}
}
