package com.gamergeo.project.videomanager.viewmodel.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.service.VideoService;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
public class TableViewModel implements ViewModel {
	
	private final VideoService videoService;
	
	private final ListProperty<TableRowViewModel> rows = new SimpleListProperty<TableRowViewModel>(FXCollections.observableArrayList());
	private final List<Video> allVideos = new ArrayList<Video>();
	private final StringProperty headerMessage = new SimpleStringProperty();
	private final ObjectProperty<TableRowViewModel> selectedRow = new SimpleObjectProperty<TableRowViewModel>();
	
	public TableViewModel(VideoService videoService) {
		this.videoService = videoService;
		FXUtils.addSimpleListChangeListener(rows, (rows) -> headerMessage.set(rows.size() + " videos found"));
		loadTable();
	}
	
	public void loadTable() {
		allVideos.clear();
		allVideos.addAll(videoService.findAllEnabled());
		rows.setAll(allVideos.stream().map(TableRowViewModel::new).collect(Collectors.toList()));
	}

	public void filter(String title, Double rating, List<Tag> withTags, List<Tag> withoutTags) {
		TableRowViewModel selectedRow = this.selectedRow.get();
		rows.setAll(allVideos.stream()
				.filter(video -> title ==  null || title.isBlank() || video.getTitle().toLowerCase().contains(title.toLowerCase()))
	            .filter(video -> rating == null || rating == 0 || video.getRating() >= rating) // Filtrage par note minimale
	            .filter(video -> withTags.isEmpty() || video.getTags().stream().anyMatch(tag -> withTags.contains(tag))) // Vérifie la présence d'au moins un tag recherché
	            .filter(video -> withoutTags.isEmpty() || video.getTags().stream().noneMatch(tag -> withoutTags.contains(tag))) // Exclut les vidéos avec des tags non désirés
	            .map(TableRowViewModel::new)
	            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
		
		if (rows.contains(selectedRow)) {
			setSelectedRow(selectedRow);
		}
	}
	
	/**
	 * Find all videos for url
	 */
	public void filter(String url) {
		TableRowViewModel selectedRow = this.selectedRow.get();
		String cleanedUrl = url.replace("&amp;", "&").trim().toLowerCase();
		
		rows.setAll(allVideos.stream()
				.filter(video -> url ==  null || url.isBlank() || video.getUrl().replace("&amp;", "&").trim().toLowerCase().contains(cleanedUrl) 
															   || cleanedUrl.contains(video.getUrl().replace("&amp;", "&").trim().toLowerCase()))
	            .map(TableRowViewModel::new)
	            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
		
		if (rows.contains(selectedRow)) {
			setSelectedRow(selectedRow);
		}
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
		if (video == null) {
			selectedRow.set(null);
		} else {
			TableRowViewModel selectedRow = rows.stream().filter((row) -> row.getVideo().getId() == video.getId()).findAny().orElseThrow();
			setSelectedRow(selectedRow);
		}
	}

	public final ListProperty<TableRowViewModel> rowsProperty() {
		return this.rows;
	}

	public final ObservableList<TableRowViewModel> getRows() {
		return this.rowsProperty().get();
	}

	public final void setRows(final ObservableList<TableRowViewModel> rows) {
		this.rowsProperty().set(rows);
	}
	
}