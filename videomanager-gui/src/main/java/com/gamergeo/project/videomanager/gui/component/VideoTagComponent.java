package com.gamergeo.project.videomanager.gui.component;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.project.videomanager.gui.loader.ApplicationLoader;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class VideoTagComponent extends HBox {
	
	@Autowired
	ApplicationLoader loader;
	
	private VideoTagViewModel videoTag;
	
	@FXML
	private Label videoTagLabel;
	
    public VideoTagComponent() {
    	super();
        FXMLLoader fxmlLoader = loader.getLoader("videoTagComponent");
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(VideoTagComponent.this);
        loader.load(fxmlLoader);
        getStyleClass().add("videoTagComponent");
    }
    
    public VideoTagComponent(VideoTagViewModel videoTag) {
    	this();
		this.videoTag = videoTag;
		videoTagLabel.textProperty().bind(this.videoTag.textProperty());
	}
    
}
