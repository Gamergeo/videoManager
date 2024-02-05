	package com.gamergeo.project.videomanager.gui.viewmodel;

import com.gamergeo.lib.gamlib.javafx.viewmodel.AbstractViewModel;
import com.gamergeo.lib.gamlib.javafx.viewmodel.ViewModel;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ViewModel
public class TagViewModel extends AbstractViewModel {
	
	@FXML
	private BorderPane root;
	
	@FXML
	private Label label;
	
	private Tag model;
}
