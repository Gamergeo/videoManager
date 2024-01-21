package com.gamergeo.project.videomanager.gui.component;

import java.io.IOException;

import com.gamergeo.project.videomanager.gui.application.VideoManagerApplication;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class VideoTagComponent extends HBox {
	
	private VideoTagViewModel videoTag;
	
	@FXML
	private Label videoTagLabel;
	
    public VideoTagComponent() throws IOException {
    	super();
        FXMLLoader fxmlLoader = VideoManagerApplication.getLoader("videoTagComponent");
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(VideoTagComponent.this);
        fxmlLoader.load();
        getStyleClass().add("videoTagComponent");
    }
    
    public VideoTagComponent(VideoTagViewModel videoTag) throws IOException {
    	this();
		this.videoTag = videoTag;
		videoTagLabel.textProperty().bind(this.videoTag.textProperty());
	}
    
}
