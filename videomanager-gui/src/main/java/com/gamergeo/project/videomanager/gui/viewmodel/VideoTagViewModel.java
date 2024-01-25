package com.gamergeo.project.videomanager.gui.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VideoTagViewModel {
	
    private final StringProperty text = new SimpleStringProperty();
    
	public final StringProperty textProperty() {
		return this.text;
	}
	
	public final String getText() {
		return this.textProperty().get();
	}

	public final void setText(final String text) {
		this.textProperty().set(text);
	}
	
}
