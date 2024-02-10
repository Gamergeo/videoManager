package com.gamergeo.project.videomanager.view.search;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Rating;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.viewmodel.search.SearchTagViewModel;
import com.gamergeo.project.videomanager.viewmodel.search.SearchViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

@Component
public class SearchView implements FxmlView<SearchViewModel>, Initializable {

	@FXML
	private TextField title;
	
	@FXML
	private Rating rating;
	
	@FXML
	private Button reset;
	
	@FXML
	private Button random;
	
	@FXML
	private SplitPane searchTags;
	
	@InjectViewModel
	SearchViewModel viewModel;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		title.textProperty().bindBidirectional(viewModel.titleProperty());
		rating.ratingProperty().bindBidirectional(viewModel.ratingProperty());
		
		reset.setOnAction((e) -> viewModel.setResetClick(true));
		random.setOnAction((e) -> viewModel.setRandomClick(true));
		
		loadSearchTag(true);
		loadSearchTag(false);
	}
	
	private void loadSearchTag(boolean with) {
    	ViewTuple<SearchTagView, SearchTagViewModel> searchTag = FXUtils.load(SearchTagView.class);
    	searchTag.getViewModel().setWith(with);
    	searchTags.getItems().add(searchTag.getView());
    	viewModel.initSearchTag(searchTag.getViewModel(), with);
	}
}