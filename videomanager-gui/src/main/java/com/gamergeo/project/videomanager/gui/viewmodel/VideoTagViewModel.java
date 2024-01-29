package com.gamergeo.project.videomanager.gui.viewmodel;

import java.io.Serializable;

import com.gamergeo.project.videomanager.model.VideoTag;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VideoTagViewModel implements Serializable {
	
	private static final long serialVersionUID = 3009196317499043074L;

	private VideoTag initialModel;
	
	private VideoTag model;
	
    private final StringProperty text = new SimpleStringProperty();
    
    public VideoTagViewModel(VideoTag videoTag) {
    	model = videoTag;
    	initialModel = videoTag;

    	this.text.set(videoTag.getText());
    	this.text.addListener((x, oldValue, newValue) -> videoTag.setText(newValue));
    }
    
	public final StringProperty textProperty() {
		return this.text;
	}
	
	public final String getText() {
		return this.textProperty().get();
	}

	public final void setText(final String text) {
		this.textProperty().set(text);
	}

	public VideoTag getInitialModel() {
		return initialModel;
	}

	public void setInitialModel(VideoTag initialModel) {
		this.initialModel = initialModel;
	}

	public VideoTag getModel() {
		return model;
	}

	public void setModel(VideoTag model) {
		this.model = model;
	}
	
	public Long getId() {
		return model.getId();
	}
}
