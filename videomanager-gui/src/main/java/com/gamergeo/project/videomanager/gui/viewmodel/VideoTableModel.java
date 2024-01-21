package com.gamergeo.project.videomanager.gui.viewmodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class VideoTableModel {
	
	private VideoViewModel selectedVideo;

	private ObservableList<VideoViewModel> videoList = FXCollections.observableArrayList();
    
	public VideoViewModel getSelectedVideo() {
		return selectedVideo;
	}

	public void setSelectedVideo(VideoViewModel selectedVideo) {
		this.selectedVideo = selectedVideo;
	}

	public ObservableList<VideoViewModel> getVideoList() {
		return videoList;
	}

	public void setVideoList(ObservableList<VideoViewModel> videoList) {
		this.videoList = videoList;
	}
}
