//package com.gamergeo.project.videomanager.gui.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
//import com.gamergeo.project.videomanager.gui.service.VideoManagerApplicationService;
//import com.gamergeo.project.videomanager.gui.view.TagView;
//import com.gamergeo.project.videomanager.gui.viewmodel.old.TagViewModelOld;
//import com.gamergeo.project.videomanager.service.TagService;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.TilePane;
//import lombok.extern.slf4j.Slf4j;
//
//@FXMLController
//@Slf4j
//public class TagListController {
//	
//	@Autowired
//	private TagService tagService;
//	
//	@Autowired
//	private VideoManagerApplicationService applicationService;
//	
//	@FXML
//	private TextField tagSearchField;
//	
//	@FXML
//	private TilePane tagListPane;
//	
//	private List<TagView> tagViews;
//	
//	private String tagsDragAndDropContent = "";
//	
//	@FXML
//	private void initialize() {
//		tagViews = applicationService.addTagsToNode(tagListPane, tagService.findAll());
//	}
//	
//	public void addDragAndDropTag(TagViewModelOld tag) {
//		
//		if (!tagsDragAndDropContent.contains(tag.getId().toString())) {
//			tagsDragAndDropContent += applicationService.getIdData(tag.getId());
//			log.debug("Tag selected in drag and drop: " + tag.getId());
//		} else {
//			log.warn("Tag already in drag and drop: " + tag.getId());
//		}
//	}
//	
//	public void removeDragAndDropTag(TagViewModelOld tag) {
//		
//		if (tagsDragAndDropContent.contains(tag.getId().toString())) {
//			tagsDragAndDropContent = tagsDragAndDropContent.replace(applicationService.getIdData(tag.getId()), "");
//			log.debug("Tag unselected in drag and drop: " + tag.getId());
//		} else {
//			log.warn("Tag unselected but not found in drag and drop: " + tag.getId());
//		}
//	}
//	
//	public void unselectAllTag() {
//		tagViews.forEach((videoTagView) -> videoTagView.getController().unselect());
//	}
//
//	public String getTagsDragAndDropContent() {
//		return tagsDragAndDropContent;
//	}
//}
