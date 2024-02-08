package com.gamergeo.project.videomanager.gui.view;

import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;
import com.gamergeo.lib.gamlib.javafx.view.ViewUtils;
import com.gamergeo.project.videomanager.gui.component.SemiRating;
import com.gamergeo.project.videomanager.gui.viewmodel.ScreenViewModel;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import lombok.extern.slf4j.Slf4j;

@FXMLView
@Slf4j
public class ScreenView extends AbstractFXMLView<ScreenViewModel> {
	
	@FXML
	private BorderPane root;
	
	@FXML
	private Label title;
	
	@FXML
	private Hyperlink url;
	
	@FXML
	private Label date;
	
	@FXML
	private SemiRating rating;
	
	@FXML
	private TilePane tags;
	
	public ScreenView(ApplicationContext applicationContext, ScreenViewModel viewModel) {
		super(applicationContext, viewModel);
	}
	
	@FXML
	private void initialize() {
		title.textProperty().bindBidirectional(viewModel.titleProperty());

//		// Here bindDirectional is bugged
//		// I don't really know why but this is not equivalent to bindDirectionnal
		ViewUtils.bindDirectional(viewModel.ratingProperty(), rating.ratingProperty());
//		rating.ratingProperty().bindBidirectional(viewModel.ratingProperty());
		
		url.visibleProperty().bind(viewModel.visibleProperty());
		rating.visibleProperty().bind(viewModel.visibleProperty());
		
		viewModel.videoTags().addListener((ListChangeListener.Change<? extends Tag> change) -> {
			addTags();
		});
	}
	
	private void addTags() {
		tags.getChildren().clear();
		
		viewModel.videoTags().forEach((tag) -> {
			TagView tagView = load(TagView.class);
			tagView.getViewModel().setTag(tag);
			tags.getChildren().add(tagView.getRoot());
		});
	}
}
