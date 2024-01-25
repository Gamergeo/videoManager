package com.gamergeo.project.videomanager.gui.viewmodel;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.model.Video;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
public class ApplicationViewModel {
	
	private SimpleBooleanProperty saveVideo = new SimpleBooleanProperty();

	private SimpleBooleanProperty selectVideo = new SimpleBooleanProperty();

	private ObjectProperty<VideoViewModel> selectedVideo = new SimpleObjectProperty<VideoViewModel>();

	private ObservableList<VideoViewModel> videoList = FXCollections.observableArrayList();
	
	private ObservableList<VideoTagViewModel> applicationTagList = FXCollections.observableArrayList();

	public final ObjectProperty<VideoViewModel> selectedVideoProperty() {
		return this.selectedVideo;
	}
	
	public final VideoViewModel getSelectedVideo() {
		return this.selectedVideoProperty().get();
	}
	
	public final void setSelectedVideo(final VideoViewModel selectedVideo) {
		this.selectedVideoProperty().set(selectedVideo);
	}

	public final SimpleBooleanProperty saveVideoProperty() {
		return this.saveVideo;
	}
	
	public final boolean isSaveVideo() {
		return this.saveVideoProperty().get();
	}

	public final void setSaveVideo(final boolean saveVideo) {
		this.saveVideoProperty().set(saveVideo);
	}

	public ObservableList<VideoViewModel> getVideoList() {
		return videoList;
	}
	
	public void setVideoList(List<Video> videoList) {
		this.videoList.clear();
		videoList.forEach((video) -> this.videoList.add(new VideoViewModel(video)));
	}

	public final SimpleBooleanProperty selectVideoProperty() {
		return this.selectVideo;
	}

	public final boolean isSelectVideo() {
		return this.selectVideoProperty().get();
	}

	public final void setSelectVideo(final boolean selectVideo) {
		this.selectVideoProperty().set(selectVideo);
	}

}
