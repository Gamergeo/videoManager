package com.gamergeo.project.videomanager.gui.view;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;

@Component
public class VideoTableView extends AbstractFXMLView implements FXMLView {

	@Override
	public String getClasspath() {
		return "fxml/videoTable.fxml";
	}
}
