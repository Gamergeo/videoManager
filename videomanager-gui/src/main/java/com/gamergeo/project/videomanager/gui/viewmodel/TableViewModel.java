package com.gamergeo.project.videomanager.gui.viewmodel;

import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.service.VideoService;

import jakarta.annotation.PostConstruct;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

@Component
public class TableViewModel {
	
	@Autowired
	private VideoService videoService;
	
	private final ObservableList<VideoViewModel> filteredVideos = FXCollections.observableArrayList();
	
	private final ObjectProperty<VideoViewModel> selectedVideo = new SimpleObjectProperty<VideoViewModel>();
	
	private final ObservableList<VideoViewModel> allVideos = FXCollections.observableArrayList();
	
	private final StringProperty headerMessage = new SimpleStringProperty();
	
	@PostConstruct
	private void init() {
		allVideos.addAll(videoService.findAll().stream().map(VideoViewModel::new).collect(Collectors.toList()));
		
		filteredVideos.addListener((ListChangeListener.Change<? extends VideoViewModel> change) -> headerMessage.set(filteredVideos.size() + " videos found"));
		
		filteredVideos.setAll(allVideos);
	}

	public void filter(String title,Double rating) {
		filteredVideos.setAll(allVideos.stream()
				.filter(video -> title ==  null || title.isBlank() || video.getTitle().contains(title))
	            .filter(video -> rating == null || rating == 0 || video.getRating() >= rating) // Filtrage par note minimale
//	            .filter(video -> withTags.isEmpty() || video.getTags().stream().anyMatch(tag -> withTags.contains(tag))) // Vérifie la présence d'au moins un tag recherché
//	            .filter(video -> withoutTags.isEmpty() || video.getTags().stream().noneMatch(tag -> withoutTags.contains(tag))) // Exclut les vidéos avec des tags non désirés
//	            .sorted()
	            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
	}
	
	public VideoViewModel random() {
		Integer size = filteredVideos.size();
		
		if (size > 0) {
		    int randomNumber = new Random().nextInt(size);
		    return filteredVideos.get(randomNumber);
		}
		
		return null;
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

	public final StringProperty headerMessageProperty() {
		return this.headerMessage;
	}
	
	public final String getHeaderMessage() {
		return this.headerMessageProperty().get();
	}

	public final void setHeaderMessage(final String headerMessage) {
		this.headerMessageProperty().set(headerMessage);
	}

	public ObservableList<VideoViewModel> getFilteredVideos() {
		return filteredVideos;
	}
}
