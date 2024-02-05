package com.gamergeo.project.videomanager.gui.viewmodel;

import org.springframework.stereotype.Component;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

@Component
public class ScreenViewModel {
	
    private final ObjectProperty<VideoViewModel> selectedVideo = new SimpleObjectProperty<VideoViewModel>();

	public final ObjectProperty<VideoViewModel> selectedVideoProperty() {
		return this.selectedVideo;
	}

	public final VideoViewModel getSelectedVideo() {
		return this.selectedVideoProperty().get();
	}

	public final void setSelectedVideo(final VideoViewModel selectedVideo) {
		this.selectedVideoProperty().set(selectedVideo);
	}
    
}
