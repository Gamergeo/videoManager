package com.gamergeo.project.videomanager.gui.view;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;

@Component
public class VideoSearchView extends AbstractFXMLView implements FXMLView {

	@Override
	public String getClasspath() {
		return "fxml/videoSearch.fxml";
	}
}
