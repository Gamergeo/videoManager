package com.gamergeo.project.videomanager.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLComponentController;
import com.gamergeo.project.videomanager.gui.service.VideoManagerApplicationService;
import com.gamergeo.project.videomanager.gui.view.VideoTagListView;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;

@FXMLComponentController
public class VideoTagController {
	
	@Autowired
	private VideoTagListView videoTagListView;
	
	@Autowired
	private VideoManagerApplicationService applicationService;
	
	@FXML
	private BorderPane tagPane;
	
	@FXML
	private Label tagLabel;
	
	private VideoTagViewModel tag;
	
    public void setTag(VideoTagViewModel tag) {
    	this.tag = tag;
		tagLabel.textProperty().bind(tag.textProperty());
    }
    
    @FXML
    private void dragTag(MouseEvent event) {
        /* drag was detected, start a drag-and-drop gesture*/
        /* allow any transfer mode */
        Dragboard db = tagPane.startDragAndDrop(TransferMode.ANY);
        
        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
        
        String contentString = videoTagListView.getController().getTagsDragAndDropContent();
        
        // If no tag selected, simple drag & drop
        contentString = !contentString.isEmpty() ? contentString : applicationService.getIdData(tag.getId());
        
        content.putString(contentString);
        db.setContent(content);
        
        event.consume();
    }
    
    public void onClick(MouseEvent event) {
    	
    	if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 1) {
	        if (tagPane.getStyleClass().contains("tag-border-pane-selected")) { // Tag unselect
	        	unselect();
	        } else { // Tag select
	        	select();
	        }
    	}
    }
    
    public void select() {
    	tagPane.getStyleClass().add("tag-border-pane-selected");
    	videoTagListView.getController().addDragAndDropTag(tag);
    }
    
    public void unselect() {
    	if (tagPane.getStyleClass().contains("tag-border-pane-selected")) {
        	tagPane.getStyleClass().remove("tag-border-pane-selected");
        	videoTagListView.getController().removeDragAndDropTag(tag);
    	}
    }
    
}