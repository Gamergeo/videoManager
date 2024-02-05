	package com.gamergeo.project.videomanager.gui.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.controlsfx.control.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.gamergeo.lib.gamlib.javafx.viewmodel.AbstractViewModel;
import com.gamergeo.lib.gamlib.javafx.viewmodel.ViewModel;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ViewModel
public class SearchViewModel extends AbstractViewModel {
	
	@Autowired
	@Lazy
	private SceneViewModel scene;
	
	/** 
	 * Java FX Elements 
	 **/
	@FXML
	private TextField title;
	
	@FXML
	private Rating rating;
	
	@FXML
	private TilePane withTags;
	
	@FXML
	private TilePane withoutTags;
	
	@FXML
	private void initialize() {
		rating.setRating(0d);
	}
	
	@FXML
	private void search() {
		log.info("Search for video");
		scene.filter();
	}
	
	@FXML
	private void reset() {
		log.info("Reset search");
		title.setText("");
		rating.setRating(0);
		withTags.getChildren().clear();
		withoutTags.getChildren().clear();
		
		scene.filter();
	}
	
	@FXML
	private void random() {
		log.info("Random request");
		scene.random();
	}
	
	public String getTitle() {
		return title.getText();
	}
	
	public Double getRating() {
		return rating.getRating();
	}
	
	public List<Tag> getWithTags() {
		return new ArrayList<Tag>();
		// todo
	}
	
	public List<Tag> getWithoutTags() {
		return new ArrayList<Tag>();
		// todo
	}
	
}
