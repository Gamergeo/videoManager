package com.gamergeo.project.videomanager.view.video;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Rating;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.helper.TagParentHelper;
import com.gamergeo.project.videomanager.viewmodel.video.ScreenViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

@Component
public class ScreenView implements FxmlView<ScreenViewModel>, Initializable {
	
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

    @InjectViewModel
    private ScreenViewModel viewModel;    
    
    private final TagParentHelper tagParentHelper;
    
	public ScreenView(TagParentHelper tagParentHelper) {
		this.tagParentHelper = tagParentHelper;
	}
	
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
		title.textProperty().bindBidirectional(viewModel.titleProperty());
		
		this.url.setOnMouseClicked(viewModel::openUrl);
		
		// Here javafx bindDirectional is bugged
		// I don't really know why but this is not equivalent to bindDirectionnal
		FXUtils.bindDirectional(viewModel.ratingProperty(), rating.ratingProperty());
		// rating.ratingProperty().bindBidirectional(viewModel.ratingProperty());
		
		this.url.visibleProperty().bind(viewModel.visibleProperty());
		rating.visibleProperty().bind(viewModel.visibleProperty());
		
		root.setOnMouseDragOver(this::onMouseDragOver);
		root.setOnMouseDragExited(this::onMouseDragExited);
		root.setOnMouseDragReleased(this::onMouseDragReleased);	
		
		tagParentHelper.initTags(tags, viewModel);
	}

	public void onMouseDragOver(MouseEvent event) {
		viewModel.onMouseDragOver();
		event.consume();
	}

	public void onMouseDragExited(MouseEvent event) {
		viewModel.onMouseDragExited();
		event.consume();
	}

	public void onMouseDragReleased(MouseEvent event) {
		viewModel.setDragReleased(true);
		event.consume();
	}
}