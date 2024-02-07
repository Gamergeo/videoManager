package com.gamergeo.project.videomanager.gui.view;

import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;
import com.gamergeo.project.videomanager.gui.component.SemiRating;
import com.gamergeo.project.videomanager.gui.viewmodel.view.ScreenViewModel;

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
	
	@FXML
	private void initialize() {
		
		setVisible(false);
		
		// Update info on video selection 
		viewModel.selectedVideoProperty().addListener((observable, oldValue, newValue) -> {

	    	log.info("Change info");
	    	
			// Unbind old selected video
			if (oldValue != null) {
				rating.ratingProperty().unbindBidirectional(oldValue.ratingProperty());
			}
			
			if (newValue == null) {
				clear();
			} else {
				log.info("Change video view infos: " + newValue.getId());
				title.setText(newValue.getTitle());
				date.setText(newValue.getDate());
				viewModel.ratingProperty().addListener((e,a,b) -> {
					log.error("from model " + a + " " + b);
					rating.setRating(b.doubleValue());
				});
				rating.ratingProperty().addListener((e,a,b) -> {
					log.error("to model " + a + " " + b);
					viewModel.setRating(b.doubleValue());
				});
//				rating.ratingProperty().bindBidirectional(viewModel.ratingProperty());
				setVisible(true);
			}
		});
	}
	
	private void clear() {
		title.setText("");
		date.setText("");
		rating.setRating(0);
		setVisible(false);
	}
	
	private void setVisible(boolean visible) {
		url.setVisible(visible);
		rating.setVisible(visible);
	}
}
