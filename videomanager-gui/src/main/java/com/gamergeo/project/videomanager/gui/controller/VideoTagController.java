package com.gamergeo.project.videomanager.gui.controller;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLComponentController;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;

@FXMLComponentController
public class VideoTagController {
	
	@FXML
	private BorderPane videoTagPane;
	
	@FXML
	private Label videoTagLabel;
	
	private VideoTagViewModel videoTag;
	
    public void setTag(VideoTagViewModel videoTag) {
    	this.videoTag = videoTag;
		videoTagLabel.textProperty().bind(videoTag.textProperty());
    }
    
    @FXML
    private void dragTag(MouseEvent event) {
        /* drag was detected, start a drag-and-drop gesture*/
        /* allow any transfer mode */
        Dragboard db = videoTagPane.startDragAndDrop(TransferMode.ANY);
        
        /* Put a string on a dragboard */
        ClipboardContent content = new ClipboardContent();
//        content.put(new DataFormat("videoTag"), videoTag.getModel());
        content.putString(videoTag.getId().toString());
        db.setContent(content);
        
        event.consume();
    }
    
}