package com.gamergeo.project.videomanager.gui.controller.component;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class VideoTagComponentController implements FXMLController {
	
	@FXML
	private Label videoTagLabel;
	
    @FXML
    public void setTag(VideoTagViewModel videoTag) {
		videoTagLabel.textProperty().bind(videoTag.textProperty());
    }
}
