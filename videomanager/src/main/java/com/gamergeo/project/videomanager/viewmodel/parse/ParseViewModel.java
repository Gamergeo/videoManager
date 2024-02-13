package com.gamergeo.project.videomanager.viewmodel.parse;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Component;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
public class ParseViewModel implements ViewModel {

	private final ListProperty<File> files = new SimpleListProperty<File>(FXCollections.observableArrayList());
	
	public void setFiles(List<File> files) {
		setFiles(FXCollections.observableArrayList(files));
	}

	public final ListProperty<File> filesProperty() {
		return this.files;
	}
	
	public final ObservableList<File> getFiles() {
		return this.filesProperty().get();
	}

	public final void setFiles(final ObservableList<File> files) {
		this.filesProperty().set(files);
	}
}
