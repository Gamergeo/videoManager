package com.gamergeo.project.videomanager.gui.viewmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.viewmodel.AbstractChildViewModel;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.service.VideoService;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
public class TableViewModel extends AbstractChildViewModel<SceneViewModel> {
	
	private VideoService videoService;
	
	private final ObservableList<TableRowViewModel> rows = FXCollections.observableArrayList();
	private final List<Video> allVideos = new ArrayList<Video>();
	private final StringProperty headerMessage = new SimpleStringProperty();
	private final ObjectProperty<TableRowViewModel> selectedRow = new SimpleObjectProperty<TableRowViewModel>();
	
	public TableViewModel(VideoService videoService) {
		this.videoService = videoService;
	}
	
	@Override
	public void init() {
		allVideos.addAll(videoService.findAll());
		
		addSimpleListChangeListener(rows, (rows) -> headerMessage.set(rows.size() + " videos found"));
		rows.setAll(allVideos.stream().map(TableRowViewModel::new).collect(Collectors.toList()));
	}

	public void filter(String title,Double rating) {
		rows.setAll(allVideos.stream()
				.filter(video -> title ==  null || title.isBlank() || video.getTitle().contains(title))
	            .filter(video -> rating == null || rating == 0 || video.getRating() >= rating) // Filtrage par note minimale
//	            .filter(video -> withTags.isEmpty() || video.getTags().stream().anyMatch(tag -> withTags.contains(tag))) // Vérifie la présence d'au moins un tag recherché
//	            .filter(video -> withoutTags.isEmpty() || video.getTags().stream().noneMatch(tag -> withoutTags.contains(tag))) // Exclut les vidéos avec des tags non désirés
//	            .sorted()
	            .map(TableRowViewModel::new)
	            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
	}
	
	public Video random() {
		Integer size = rows.size();
		
		if (size > 0) {
		    int randomNumber = new Random().nextInt(size);
		    return rows.get(randomNumber).getVideo();
		}
		
		return null;
	}

	public final StringProperty headerMessageProperty() {
		return this.headerMessage;
	}
	
	public final String getHeaderMessage() {
		return this.headerMessageProperty().get();
	}

	public final void setHeaderMessage(final String headerMessage) {
		this.headerMessageProperty().set(headerMessage);
	}

	public ObservableList<TableRowViewModel> getRows() {
		return rows;
	}

	public final ObjectProperty<TableRowViewModel> selectedRowProperty() {
		return this.selectedRow;
	}
	
	public final TableRowViewModel getSelectedRow() {
		return this.selectedRowProperty().get();
	}
	
	public final void setSelectedRow(final TableRowViewModel selectedRow) {
		this.selectedRowProperty().set(selectedRow);
	}
	
	public final void setSelectedRow(final Video video) {
		TableRowViewModel selectedRow = rows.stream().filter((row) -> row.getVideo().getId() == video.getId()).findAny().orElseThrow();
		setSelectedRow(selectedRow);
	}
		
}
