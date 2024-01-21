package com.gamergeo.project.videomanager.gui.viewmodel;

import com.gamergeo.lib.gamlib.gui.viewmodel.ViewModel;

import javafx.beans.property.SimpleStringProperty;

public class VideoTagViewModel implements ViewModel {
	
    private final SimpleStringProperty text = new SimpleStringProperty();
    
	public final SimpleStringProperty textProperty() {
		return this.text;
	}
	
	public final String getText() {
		return this.textProperty().get();
	}

	public final void setText(final String text) {
		this.textProperty().set(text);
	}
	
}
