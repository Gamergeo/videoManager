package com.gamergeo.project.videomanager.gui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamergeo.lib.gamlib.javafx.model.Model;
import com.gamergeo.lib.gamlib.javafx.service.EventService;
import com.gamergeo.project.videomanager.gui.viewmodel.view.SceneViewModel;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.service.VideoService;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ModelSyncService {
	
	@Autowired
	private SceneViewModel scene;
	
	
	@Autowired
	private EventService eventService;
	
	// ModelSyncService s'abonne aux événements de mise à jour du modèle
	@PostConstruct
	public void subscribe() {
	    eventService.subscribe(event -> {
	    	
	    	Model model = event.newValue;	
	    	
	    	if (model instanceof Video) {
	    		 Video updatedVideo = (Video) model;
	    		 
	    		 scene.refreshVideo(updatedVideo);
	    		 
	    	}
	    	
	        // Trouve le ViewModel associé et le met à jour
	    });
	}
}
