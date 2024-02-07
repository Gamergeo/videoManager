package com.gamergeo.project.videomanager.gui.view;

import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;
import com.gamergeo.project.videomanager.gui.component.SemiRating;
import com.gamergeo.project.videomanager.gui.viewmodel.ScreenViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

@FXMLView
public class ScreenView extends AbstractFXMLView<ScreenViewModel> {
	
	@FXML
	private BorderPane root;
	
	@FXML
	private Label title;
	
	@FXML
	private Hyperlink url;
	
	@FXML
	private Label date;
	
	@FXML
	private SemiRating rating;
	
	@FXML
	private TilePane tags;
	
	public ScreenView(ApplicationContext applicationContext, ScreenViewModel viewModel) {
		super(applicationContext, viewModel);
	}
	
	@FXML
	private void initialize() {
		title.textProperty().bindBidirectional(viewModel.titleProperty());
		rating.ratingProperty().bindBidirectional(viewModel.ratingProperty());
		
		url.visibleProperty().bind(viewModel.visibleProperty());
		rating.visibleProperty().bind(viewModel.visibleProperty());
	}
}
