	package com.gamergeo.project.videomanager.gui.view;

import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.model.Tag;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TagView {
	
	@FXML
	private BorderPane root;
	
	@FXML
	private Label label;
	
	private Tag model;
}
