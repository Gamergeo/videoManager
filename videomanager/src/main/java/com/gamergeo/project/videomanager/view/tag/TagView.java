	package com.gamergeo.project.videomanager.view.tag;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.viewmodel.tag.TagViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

@Component
@Scope("prototype")
public class TagView implements FxmlView<TagViewModel>, Initializable {
	
	@FXML
	private BorderPane root;
	
	@FXML
	private Label label;
	
	@FXML
	private TextField labelEdit;
	
	@FXML
	private Button delete;

    @InjectViewModel
    private TagViewModel viewModel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		label.textProperty().bindBidirectional(viewModel.labelProperty());
		delete.setOnAction((event) -> viewModel.setDeleted(true));
		
		// Select tag (click or drag)
		root.setOnMousePressed(this::onMousePressed);
		root.addEventFilter(MouseEvent.MOUSE_CLICKED, this::onMousePressed);
		
		select(viewModel.isSelected());
		FXUtils.addSimpleChangeListener(viewModel.selectedProperty(), this::select);
		root.setOnDragDetected((event) -> viewModel.setSelected(true));
		
		labelEdit.setManaged(false);
		label.visibleProperty().bind(label.managedProperty());
		label.managedProperty().bind(viewModel.editProperty().not());
//		label.addEventFilter(MouseEvent.MOUSE_CLICKED, this::onMousePressed);
		labelEdit.visibleProperty().bind(labelEdit.managedProperty());
		labelEdit.managedProperty().bind(viewModel.editProperty());
		labelEdit.setOnKeyPressed(this::onKeyPressed);
//		labelEdit.addEventFilter(MouseEvent.MOUSE_CLICKED, this::onMousePressed);
		labelEdit.setOnContextMenuRequested(event -> event.consume());
		FXUtils.addSimpleChangeListener(labelEdit.textProperty(), viewModel::setLabelEdit);
	}
	
	private void select(boolean isSelected) {
		if (isSelected) {
			root.getStyleClass().add("tag-border-pane-selected");
		} else {
			root.getStyleClass().remove("tag-border-pane-selected");
		}
	}
	
    private void onMousePressed(MouseEvent event) {
    	// Tag selection
    	if (event.isPrimaryButtonDown()) {
        	event.consume();
    		 viewModel.select();
    	} else if (event.isSecondaryButtonDown()) { // Rename
        	event.consume();
    		viewModel.switchEdit();
    		
    		// edit
    		if (labelEdit.isManaged()) {
    			labelEdit.setText(viewModel.getLabel());
    			labelEdit.requestFocus();
    		}
    	}
    	event.consume();
    }
    
    private void onKeyPressed(KeyEvent event) {
    	if (event.getCode() == KeyCode.ENTER) {
    		viewModel.switchEdit();
    	}
    }
}