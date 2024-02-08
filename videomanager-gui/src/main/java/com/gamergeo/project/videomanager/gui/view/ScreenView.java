package com.gamergeo.project.videomanager.gui.view;

import org.controlsfx.control.Rating;
import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.gamlib.javafx.view.FXMLView;
import com.gamergeo.lib.gamlib.javafx.view.ViewUtils;
import com.gamergeo.project.videomanager.gui.VideoManagerParameters;
import com.gamergeo.project.videomanager.gui.view.tag.AbstractTagDroppableView;
import com.gamergeo.project.videomanager.gui.viewmodel.ScreenViewModel;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

@FXMLView
public class ScreenView extends AbstractTagDroppableView<ScreenViewModel> {
	
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
	
	public ScreenView(ApplicationContext applicationContext, ScreenViewModel viewModel, VideoManagerParameters applicationParameters) {
		super(applicationContext, viewModel, applicationParameters);
	}
	
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