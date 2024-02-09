package com.gamergeo.project.videomanager.view;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Rating;
import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.viewmodel.SceneViewModel;
import com.gamergeo.project.videomanager.viewmodel.SearchViewModel;
import com.gamergeo.project.videomanager.viewmodel.TableViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
	
	@InjectViewModel
	SearchViewModel viewModel;
	
	private final SceneViewModel sceneViewModel;
	private final TableViewModel tableViewModel;
	
	public SearchView(SceneViewModel sceneViewModel, TableViewModel tableViewModel) {
		super();
		this.sceneViewModel = sceneViewModel;
		this.tableViewModel = tableViewModel;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		title.textProperty().bindBidirectional(viewModel.titleProperty());
		rating.ratingProperty().bindBidirectional(viewModel.ratingProperty());
	}
	
	@FXML
	private void reset() {
		viewModel.reset();
	}
	
	@FXML
	private void random() {
		sceneViewModel.random();
	}
}