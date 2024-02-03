package com.gamergeo.project.videomanager.gui.viewmodel;

import java.time.LocalDate;
import java.util.StringJoiner;

import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.model.Video;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
public class VideoViewModel {
	
	private Video initialVideo;
	
	private Video video;
	
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty url = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> addedDate = new SimpleObjectProperty<LocalDate>();
	
	private ObservableList<TagViewModel> tagList = FXCollections.observableArrayList();
    
    public VideoViewModel() {}
    
    public VideoViewModel(Video video) {
    	this.initialVideo = video;
    	this.video = video;
    	
    	this.title.set(video.getTitle());
    	this.title.addListener((x, oldValue, newValue) -> video.setTitle(newValue));
    	
    	this.url.set(video.getUrl());
    	this.url.addListener((x, oldValue, newValue) -> video.setUrl(newValue));

    	this.addedDate.set(video.getAddedDate());
    	this.addedDate.addListener((x, oldValue, newValue) -> video.setAddedDate(newValue));
    	
    	tagList.clear();
    	video.getTags().forEach((tag) -> tagList.add(new TagViewModel(tag)));
    }
	
	public String getTags() {
		StringJoiner joiner = new StringJoiner(" / ");
		tagList.forEach((videoTag) -> joiner.add(videoTag.getText()));
		return joiner.toString();
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


	public Video getInitialVideo() {
		return initialVideo;
	}


	public void setInitialVideo(Video initialVideo) {
		this.initialVideo = initialVideo;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public final StringProperty titleProperty() {
		return this.title;
	}

	public final String getTitle() {
		return this.titleProperty().get();
	}
	
	public final void setTitle(final String title) {
		this.titleProperty().set(title);
	}
	
	public final Long getId() {
		return video.getId();
	}

	public ObservableList<TagViewModel> getTagList() {
		return tagList;
	}

	public void setTagList(ObservableList<TagViewModel> tagList) {
		this.tagList = tagList;
	}
}
