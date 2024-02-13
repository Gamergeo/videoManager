package com.gamergeo.project.videomanager.viewmodel.parse;

import java.io.File;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.model.parse.Folder;
import com.gamergeo.project.videomanager.service.parse.ParseService;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Component
@Scope("prototype")
public class ParseTabViewModel implements ViewModel {
	
	private final ParseService parseService;
	
	private final ObjectProperty<File> rawFile = new SimpleObjectProperty<File>(); // Raw file, need to stay raw
	private final ObjectProperty<File> file = new SimpleObjectProperty<File>();
	private final StringProperty message = new SimpleStringProperty();
	private final BooleanProperty error = new SimpleBooleanProperty();
	private final ObjectProperty<Folder> rootFolder = new SimpleObjectProperty<Folder>();

	public ParseTabViewModel(ParseService parseService) {
      	this.parseService = parseService;

      	FXUtils.addSimpleChangeListener(rawFile, this::importFile);
//      	FXUtils.addSimpleChangeListener(file, this::validateFile);
	}
	
	/** Copy file in correct folder */
	private void importFile(File rawFile) {

		if (!parseService.validateFile(rawFile)) {
			error.set(true);
			message.set("File is not a proper bookmark file");
		} else {
			setFile(parseService.importFile(rawFile));

			// Parse folders for treeview
			setRootFolder(parseService.parseFolders(getFile()));
		}
	}
	
	public final StringProperty messageProperty() {
		return this.message;
	}
	
	public final String getMessage() {
		return this.messageProperty().get();
	}

	public final void setMessage(final String message) {
		this.messageProperty().set(message);
	}

	public final BooleanProperty errorProperty() {
		return this.error;
	}

	public final boolean isError() {
		return this.errorProperty().get();
	}

	public final void setError(final boolean error) {
		this.errorProperty().set(error);
	}

	public final ObjectProperty<File> rawFileProperty() {
		return this.rawFile;
	}

	public final File getRawFile() {
		return this.rawFileProperty().get();
	}
	
	public final void setRawFile(final File rawFile) {
		this.rawFileProperty().set(rawFile);
	}
	
	public final ObjectProperty<File> fileProperty() {
		return this.file;
	}

	public final File getFile() {
		return this.fileProperty().get();
	}
	
	public final void setFile(final File file) {
		this.fileProperty().set(file);
	}

	public final ObjectProperty<Folder> rootFolderProperty() {
		return this.rootFolder;
	}
	
	public final Folder getRootFolder() {
		return this.rootFolderProperty().get();
	}
	
	public final void setRootFolder(final Folder rootFolder) {
		this.rootFolderProperty().set(rootFolder);
	}
	
}
