package com.gamergeo.project.videomanager.gui.view;

import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;
import com.gamergeo.project.videomanager.gui.component.SemiRating;
import com.gamergeo.project.videomanager.gui.viewmodel.SearchViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

@FXMLView
public class SearchView extends AbstractFXMLView<SearchViewModel> {

	@FXML
	public TextField title;
	
	@FXML
	public SemiRating rating;
	
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
		title.setText("");
		rating.setRating(0);
		withTags.getChildren().clear();
		withoutTags.getChildren().clear();
	}
	
	@FXML
	private void random() {
		viewModel.random();
//		log.info("Random request");
	}
}
