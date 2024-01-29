package com.gamergeo.project.videomanager.gui.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.project.videomanager.gui.view.VideoTagView;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;
import com.gamergeo.project.videomanager.service.VideoTagService;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;

@FXMLController
public class VideoTagListController {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private VideoTagService videoTagService;
	
	@FXML
	private TextField tagSearchField;
	
	@FXML
	private TilePane tagListPane;
	
	@FXML
	private void initialize() {
    	List<VideoTagViewModel> tagList = videoTagService.findAll()
				 .stream()
				 .map((video) -> new VideoTagViewModel(video))
				 .collect(Collectors.toList());
    	
    	tagList.forEach((tag) -> {
			VideoTagView tagView = applicationContext.getBean(VideoTagView.class);
			tagView.load();
			tagView.getController().setTag(tag);
			tagListPane.getChildren().add(tagView.getRoot());
		});
	}
    	
}
