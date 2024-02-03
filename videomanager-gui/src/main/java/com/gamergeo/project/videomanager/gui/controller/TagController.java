package com.gamergeo.project.videomanager.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLComponentController;
import com.gamergeo.project.videomanager.gui.service.VideoManagerApplicationService;
import com.gamergeo.project.videomanager.gui.view.TagListView;
import com.gamergeo.project.videomanager.gui.viewmodel.TagViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;

@FXMLComponentController
public class TagController {
	
	@Autowired
	private TagListView tagListView;
	
	@Autowired
	private VideoManagerApplicationService applicationService;
	
	@FXML
	private BorderPane tagPane;
	
	@FXML
	private Label tagLabel;
	
	private TagViewModel tag;
	
    public void setTag(TagViewModel tag) {
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
        
        String contentString = tagListView.getController().getTagsDragAndDropContent();
        
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
    	tagListView.getController().addDragAndDropTag(tag);
    }
    
    public void unselect() {
    	if (tagPane.getStyleClass().contains("tag-border-pane-selected")) {
        	tagPane.getStyleClass().remove("tag-border-pane-selected");
        	tagListView.getController().removeDragAndDropTag(tag);
    	}
    }
    
}