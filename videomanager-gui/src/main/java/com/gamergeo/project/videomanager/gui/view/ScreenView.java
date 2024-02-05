package com.gamergeo.project.videomanager.gui.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.view.AbstractView;
import com.gamergeo.lib.gamlib.javafx.view.View;
import com.gamergeo.project.videomanager.gui.component.SemiRating;
import com.gamergeo.project.videomanager.gui.viewmodel.SceneViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@View
public class ScreenView extends AbstractView {
	
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
		
//		// Update info on video selection 
//		scene.selectedVideo().addListener((observable, oldValue, newValue) -> {
//
//			// Unbind old selected video
//			if (oldValue != null) {
//				rating.ratingProperty().unbindBidirectional(oldValue.ratingProperty());
//			}
//			
//			if (newValue == null) {
//				clear();
//			} else {
//				log.info("Change video view infos: " + newValue.getId());
//				title.setText(newValue.getTitle());
//				date.setText(newValue.getDate());
//				rating.ratingProperty().bindBidirectional(scene.selectedVideo().get().ratingProperty());
//				rating.setRating(newValue.getRating());
//				setVisible(true);
//			}
//		});
	}
	
	private void clear() {
		log.info("Clear video view infos");
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
