	package com.gamergeo.project.videomanager.gui.view;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.view.AbstractView;
import com.gamergeo.lib.gamlib.javafx.view.View;
import com.gamergeo.project.videomanager.gui.component.SemiRating;
import com.gamergeo.project.videomanager.gui.viewmodel.SceneViewModel;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@View
public class SearchView extends AbstractView {

	@FXML
	public TextField title;
	
	@FXML
	public SemiRating rating;
	
	@FXML
	public TilePane withTags;
	
	@FXML
	public TilePane withoutTags;
	
	@FXML
	public Button search;
	
	@FXML
	private void initialize() {
		rating.setRating(0d);
	}
	
	@FXML
	private void clear() {
		title.setText("");
		rating.setRating(0);
		withTags.getChildren().clear();
		withoutTags.getChildren().clear();
	}
	
	@FXML
	private void random() {
		log.info("Random request");
//		scene.random(title.getText(), rating.getRating(), new ArrayList<Tag>(), new ArrayList<Tag>());
	}
}
