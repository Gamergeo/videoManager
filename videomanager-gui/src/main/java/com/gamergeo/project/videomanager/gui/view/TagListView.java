	package com.gamergeo.project.videomanager.gui.view;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

@Component
public class TagListView {
	
	@FXML
	private TextField search;
	
	@FXML
	private TilePane list;
	
}
