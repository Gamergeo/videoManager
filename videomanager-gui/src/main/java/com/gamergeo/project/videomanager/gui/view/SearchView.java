package com.gamergeo.project.videomanager.gui.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;
import com.gamergeo.project.videomanager.gui.component.SemiRating;
import com.gamergeo.project.videomanager.gui.viewmodel.ScreenViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.SearchViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.TableViewModel;

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
	
	@Autowired
	public ScreenViewModel screen;
	
	@Autowired
	public TableViewModel table;
	
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
//		log.info("Random request");
		screen.setSelectedVideo(table.random());
	}
}
