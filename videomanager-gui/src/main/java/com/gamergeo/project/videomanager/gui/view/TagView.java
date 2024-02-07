	package com.gamergeo.project.videomanager.gui.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;
import com.gamergeo.project.videomanager.gui.viewmodel.TagViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

@FXMLView
@Scope("prototype")
public class TagView extends AbstractFXMLView<TagViewModel>{
	
	@FXML
	private BorderPane root;
	
	@FXML
	private Label label;
	
	public TagView(ApplicationContext applicationContext, TagViewModel viewModel) {
		super(applicationContext, viewModel);
	}
	
	@FXML
	private void initialize() { 
		label.textProperty().bindBidirectional(viewModel.labelProperty());
	}
}
