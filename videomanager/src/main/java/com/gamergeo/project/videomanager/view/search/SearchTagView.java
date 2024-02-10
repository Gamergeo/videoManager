package com.gamergeo.project.videomanager.view.search;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.view.tag.TagDroppableView;
import com.gamergeo.project.videomanager.viewmodel.search.SearchTagViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

@Component
@Scope("prototype")
public class SearchTagView  implements FxmlView<SearchTagViewModel>, Initializable, TagDroppableView {

	@FXML
	private Label label;
	
	@FXML
	private TilePane tags;
	
	@InjectViewModel
	SearchTagViewModel viewModel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		label.textProperty().bind(viewModel.labelProperty());
		
		renderTags(false);
		setOnMouseDrag();
	}
	
	@Override
	public Pane getTagPane() {
		return tags;
	}

	@Override
	public SearchTagViewModel getViewModel() {
		return viewModel;
	}
}
