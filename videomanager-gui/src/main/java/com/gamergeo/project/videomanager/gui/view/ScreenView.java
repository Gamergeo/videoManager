package com.gamergeo.project.videomanager.gui.view;

import org.controlsfx.control.Rating;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.ViewUtils;
import com.gamergeo.project.videomanager.gui.view.tag.TagDroppableView;
import com.gamergeo.project.videomanager.gui.viewmodel.ScreenViewModel;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

@Component
public class ScreenView extends TagDroppableView<ScreenViewModel> {
	
	@FXML
	private BorderPane root;
	
	@FXML
	private Label title;
	
	@FXML
	private Hyperlink url;
	
	@FXML
	private Label date;
	
	@FXML
	private Rating rating;
	
	@FXML
	private TilePane tags;
	
	@FXML
	protected void initialize() {
		super.initialize();
		title.textProperty().bindBidirectional(viewModel.titleProperty());
		
		url.setOnMouseClicked(viewModel::openUrl);
		
		// Here javafx bindDirectional is bugged
		// I don't really know why but this is not equivalent to bindDirectionnal
		ViewUtils.bindDirectional(viewModel.ratingProperty(), rating.ratingProperty());
		// rating.ratingProperty().bindBidirectional(viewModel.ratingProperty());
		
		url.visibleProperty().bind(viewModel.visibleProperty());
		rating.visibleProperty().bind(viewModel.visibleProperty());
	}
	
	@Override
	protected Pane getTagPane() {
		return tags;
	}

	@Override
	protected ObservableList<Tag> getTags() {
		return viewModel.getVideoTags();
	}

	@Override
	protected Pane getDropTagPane() {
		return root;
	}
}
