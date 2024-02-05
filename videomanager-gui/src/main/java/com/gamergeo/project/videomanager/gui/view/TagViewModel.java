	package com.gamergeo.project.videomanager.gui.view;

import com.gamergeo.lib.gamlib.javafx.view.AbstractView;
import com.gamergeo.lib.gamlib.javafx.view.View;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@View
public class TagViewModel extends AbstractView {
	
	@FXML
	private BorderPane root;
	
	@FXML
	private Label label;
	
	private Tag model;
}
