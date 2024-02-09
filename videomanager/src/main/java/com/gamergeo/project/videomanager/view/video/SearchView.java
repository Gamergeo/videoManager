package com.gamergeo.project.videomanager.view.video;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Rating;
import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.viewmodel.video.SearchViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

@Component
public class SearchView implements FxmlView<SearchViewModel>, Initializable {

	@FXML
	public TextField title;
	
	@FXML
	public Rating rating;
	
	@FXML
	public TilePane withTags;
	
	@FXML
	public TilePane withoutTags;
	
	@FXML
	public Button reset;
	
	@FXML
	public Button random;
	
	@InjectViewModel
	SearchViewModel viewModel;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		title.textProperty().bindBidirectional(viewModel.titleProperty());
		rating.ratingProperty().bindBidirectional(viewModel.ratingProperty());
		
		reset.setOnAction((e) -> viewModel.setResetClick(true));
		random.setOnAction((e) -> viewModel.setRandomClick(true));
	}
}