package com.gamergeo.project.videomanager.gui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.project.videomanager.gui.ApplicationConstant;
import com.gamergeo.project.videomanager.gui.service.VideoManagerApplicationService;
import com.gamergeo.project.videomanager.gui.view.VideoTagView;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;
import com.gamergeo.project.videomanager.service.VideoTagService;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import lombok.extern.slf4j.Slf4j;

@FXMLController
@Slf4j
public class VideoTagListController {
	
	@Autowired
	private VideoTagService videoTagService;
	
	@Autowired
	private VideoManagerApplicationService applicationService;
	
	@FXML
	private TextField tagSearchField;
	
	@FXML
	private TilePane tagListPane;
	
	private List<VideoTagView> videoTagViews;
	
	private String tagsDragAndDropContent = "";
	
	@FXML
	private void initialize() {
		videoTagViews = applicationService.addTagsToNode(tagListPane, videoTagService.findAll());
	}
	
	public void addDragAndDropTag(VideoTagViewModel videoTag) {
		
		if (!tagsDragAndDropContent.contains(videoTag.getId().toString())) {
			tagsDragAndDropContent += applicationService.getIdData(videoTag.getId());
			log.debug("VideoTag selected in drag and drop: " + videoTag.getId());
		} else {
			log.warn("VideoTag already in drag and drop: " + videoTag.getId());
		}
	}
	
	public void removeDragAndDropTag(VideoTagViewModel videoTag) {
		
		if (tagsDragAndDropContent.contains(videoTag.getId().toString())) {
			tagsDragAndDropContent = tagsDragAndDropContent.replace(applicationService.getIdData(videoTag.getId()), "");
			log.debug("VideoTag unselected in drag and drop: " + videoTag.getId());
		} else {
			log.warn("VideoTag unselected but not found in drag and drop: " + videoTag.getId());
		}
	}
	
	public void unselectAllTag() {
		videoTagViews.forEach((videoTagView) -> videoTagView.getController().unselect());
	}

	public String getTagsDragAndDropContent() {
		return tagsDragAndDropContent;
	}
}
