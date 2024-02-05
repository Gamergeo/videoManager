package com.gamergeo.project.videomanager.gui.viewmodel;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.service.VideoService;

import jakarta.annotation.PostConstruct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
public class TableViewModel {
	
	@Autowired
	private VideoService videoService;
	
	private ObservableList<VideoViewModel> filteredVideos = FXCollections.observableArrayList();
	
	private List<VideoViewModel> allVideos;
	
	@PostConstruct
	private void init() {
		allVideos = videoService.findAll().stream().map(VideoViewModel::new).collect(Collectors.toList());
	}

	public void filter(String title, Double rating) {
		filteredVideos.setAll(allVideos.stream()
				.filter(video -> title ==  null || title.isBlank() || video.getTitle().contains(title))
	            .filter(video -> rating == null || rating == 0 || video.getRating() >= rating) // Filtrage par note minimale
//	            .filter(video -> withTags.isEmpty() || video.getTags().stream().anyMatch(tag -> withTags.contains(tag))) // Vérifie la présence d'au moins un tag recherché
//	            .filter(video -> withoutTags.isEmpty() || video.getTags().stream().noneMatch(tag -> withoutTags.contains(tag))) // Exclut les vidéos avec des tags non désirés
				.collect(Collectors.toList()));
	}
	
	public ObservableList<VideoViewModel> getVideos() {
		return filteredVideos;
	}
	
	public VideoViewModel random() {
		Integer size = filteredVideos.size();
		
		if (size > 0) {
		    int randomNumber = new Random().nextInt(size);
		    return filteredVideos.get(randomNumber);
		}
		
		return null;
	}
}
