package com.gamergeo.project.videomanager.gui.viewmodel.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.gui.viewmodel.model.VideoViewModel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

@Component
public class ScreenViewModel {
	
	private SceneViewModel scene;
	
	public final ObjectProperty<VideoViewModel> selectedVideoProperty() {
		return scene.selectedVideoProperty();
	}
	
	public final DoubleProperty ratingProperty() {
		return selectedVideoProperty().get().ratingProperty();
	}
	
	public final void setRating(final double rating) {
		this.selectedVideoProperty().get().setRating(rating);
	}

	public void setScene(SceneViewModel scene) {
		this.scene = scene;
	}
}
