package com.gamergeo.project.videomanager.gui.view;

import org.controlsfx.control.Rating;
import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;
import com.gamergeo.project.videomanager.gui.viewmodel.SearchViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

@FXMLView
public class SearchView extends AbstractFXMLView<SearchViewModel> {

	@FXML
	public TextField title;
	
	@FXML
	public Rating rating;
	
	@FXML
	public TilePane withTags;
	
	@FXML
	public TilePane withoutTags;
	
	public SearchView(ApplicationContext applicationContext, SearchViewModel viewModel) {
		super(applicationContext, viewModel);
	}
	
	@FXML
	private void initialize() {
		title.textProperty().bindBidirectional(viewModel.titleProperty());
		rating.ratingProperty().bindBidirectional(viewModel.ratingProperty());
	}
	
	@FXML
	private void reset() {
		viewModel.reset();
	}
	
	@FXML
	private void random() {
		viewModel.random();
	}
}
