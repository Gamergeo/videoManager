package com.gamergeo.project.videomanager.view.screen;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Rating;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.view.tag.TagDroppableView;
import com.gamergeo.project.videomanager.viewmodel.screen.ScreenViewModel;
import com.gamergeo.project.videomanager.viewmodel.tag.TagDroppableViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

@Component
public class ScreenView implements FxmlView<ScreenViewModel>, Initializable, TagDroppableView {
	
	@FXML
	private BorderPane root;
	
	@FXML
	private Label hasDuplicate;
	
	@FXML
	private Label title;
	
	@FXML
	private Hyperlink url;
	
	@FXML
	private TextField urlEdit;
	
	@FXML
	private Label date;
	
	@FXML
	private Rating rating;
	
	@FXML
	private TilePane tags;
	
	@FXML
	private Button disable;
	
	@FXML
	private Button duplicate;

    @InjectViewModel
    private ScreenViewModel viewModel;  
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
		title.textProperty().bindBidirectional(viewModel.titleProperty());
		date.textProperty().bindBidirectional(viewModel.dateProperty());
		
		FXUtils.addSimpleChangeListener(urlEdit.textProperty(), viewModel::setUrlEdit);
		
		this.url.setOnMouseClicked(viewModel::openUrl);
		urlEdit.setOnKeyPressed(this::onKeyPressed);
		
		// Here javafx bindDirectional is bugged
		// I don't really know why but this is not equivalent to bindDirectionnal
		FXUtils.bindDirectional(viewModel.ratingProperty(), rating.ratingProperty());
		// rating.ratingProperty().bindBidirectional(viewModel.ratingProperty());
		
		// Visible / managed
		this.url.visibleProperty().bind(Bindings.and(viewModel.visibleProperty(), viewModel.editProperty().not()));
		this.url.managedProperty().bind(viewModel.editProperty().not());
		this.urlEdit.visibleProperty().bind(Bindings.and(viewModel.visibleProperty(), viewModel.editProperty()));
		this.urlEdit.managedProperty().bind(viewModel.editProperty());
		rating.visibleProperty().bind(viewModel.visibleProperty());
		
		disable.setOnAction((event) -> viewModel.setDisabled(true));
		duplicate.setOnAction((event) -> viewModel.setDuplicate(!viewModel.isDuplicate()));
		
		FXUtils.addSimpleChangeListener(viewModel.duplicateProperty(), this::setDuplicateButtonText);
    	hasDuplicate.managedProperty().bind(viewModel.hasDuplicateProperty());
    	hasDuplicate.visibleProperty().bind(viewModel.hasDuplicateProperty());
		
		renderTags(false);
		setOnMouseDrag();
		root.setOnMousePressed(this::onClick);
	}
    
    private void onClick(MouseEvent event) {
    	if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
    		viewModel.switchEdit();
    		
    		// edit
    		if (urlEdit.isManaged()) {
        		urlEdit.setText(viewModel.getUrl());
    			urlEdit.requestFocus();
    		}
    	}
    }
    
    private void onKeyPressed(KeyEvent event) {
    	if (event.getCode() == KeyCode.ENTER) {
    		viewModel.switchEdit();
    	}
    }
    
    private void setDuplicateButtonText(boolean isDuplicate) {
    	if (isDuplicate) {
        	duplicate.setText("End");
    	} else {
        	duplicate.setText("Duplicate");
    	}
    	
    }

	@Override
	public Pane getTagPane() {
		return tags;
	}

	@Override
	public TagDroppableViewModel getViewModel() {
		return viewModel;
	}

	@Override
	public Pane getDropPane() {
		return root;
	}


}