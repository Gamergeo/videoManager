package com.gamergeo.project.videomanager.gui.view;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;

@Component
public class VideoSceneView extends AbstractFXMLView implements FXMLView {

	@Override
	public String getClasspath() {
		return "fxml/videoScene.fxml";
	}
}
