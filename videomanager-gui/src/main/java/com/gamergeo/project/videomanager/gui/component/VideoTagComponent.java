package com.gamergeo.project.videomanager.gui.component;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gamergeo.project.videomanager.gui.application.VideoManagerApplication;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

@Controller
@Scope("prototype")
public class VideoTagComponent extends GridPane {
	
	private VideoTagViewModel videoTag;
	
	@FXML
	private Label videoTagLabel;
	
    public VideoTagComponent() throws IOException {
        FXMLLoader fxmlLoader = VideoManagerApplication.getLoader("videoTagComponent");
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(VideoTagComponent.this);
        fxmlLoader.load();
    }

	public void setVideoTag(VideoTagViewModel videoTag) {
		this.videoTag = videoTag;
		videoTagLabel.textProperty().bind(this.videoTag.textProperty());
	}
}
