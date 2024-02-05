	package com.gamergeo.project.videomanager.gui.viewmodel;

import org.controlsfx.control.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.gamergeo.lib.gamlib.javafx.viewmodel.AbstractViewModel;
import com.gamergeo.lib.gamlib.javafx.viewmodel.ViewModel;
import com.gamergeo.project.videomanager.model.Video;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ViewModel
public class VideoViewModel extends AbstractViewModel {
	
	@Autowired
	@Lazy
	protected SceneViewModel scene;
	
	@FXML
	private BorderPane root;
	
	@FXML
	private Label title;
	
	@FXML
	private TextField titleedit;
	
	@FXML
	private Hyperlink url;
	
	@FXML
	private Label date;
	
	@FXML
	private Rating rating;
	
	@FXML
	private TilePane tags;
	
	private ObjectProperty<Video> selectedVideo = new SimpleObjectProperty<Video>();
	
	private ChangeListener<? super Video> updateListener = this::update;
	private ChangeListener<? super String> updateTitleListener = this::updateTitle;
	private ChangeListener<? super Number> updateRatingListener = this::updateRating;
	
	public void initialize() {
		selectedVideo.addListener(updateListener);
	}
	
	public void setData(Video video) {
		selectedVideo.set(video);
	}
	
	public Video getData() {
		return selectedVideo.get();
	}
	
	private void clear() {
		log.info("Clear video view infos");
		titleedit.textProperty().removeListener(updateTitleListener);
		rating.ratingProperty().removeListener(updateRatingListener);
		title.setText("");
		date.setText("");
		rating.setRating(0);
	}
	
	private void update(ObservableValue<? extends Video> observable, Video oldValue, Video newValue) {
		clear();
		if (newValue != null) {
			log.info("Change video view infos: " + newValue.getId());
			title.setText(newValue.getTitle());
			date.setText(newValue.getAddedDate().toString());
			rating.setRating(newValue.getRating());
			titleedit.textProperty().addListener(updateTitleListener);
			rating.ratingProperty().addListener(updateRatingListener);
		}
	}
	
	private void updateRating(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	    Video video = selectedVideo.get();
	    if (video != null) {
	        video.setRating(newValue.doubleValue());
	        scene.save();
	    }
	}
	
	private void updateTitle(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	    Video video = selectedVideo.get();
	    if (video != null) {
	        video.setTitle(newValue);
	        scene.save();
	    }
	}
}
