package com.gamergeo.project.videomanager.gui.view;

import org.controlsfx.control.Rating;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXMLView;
import com.gamergeo.project.videomanager.gui.viewmodel.SearchViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

@Component
public class SearchView extends FXMLView<SearchViewModel> {

	@FXML
	public TextField title;
	
	@FXML
	public Rating rating;
	
	@FXML
	public TilePane withTags;
	
	@FXML
	public TilePane withoutTags;
	
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
