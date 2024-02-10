package com.gamergeo.project.videomanager.viewmodel.taglist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.service.TagService;
import com.gamergeo.project.videomanager.viewmodel.tag.TagParentViewModel;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
public class TagListViewModel implements ViewModel, TagParentViewModel {

	private final TagService tagService;

	private final List<Tag> allTags = new ArrayList<Tag>();
	private final ListProperty<Tag> renderedTags = new SimpleListProperty<Tag>(FXCollections.observableArrayList());
	private final ObservableList<Tag> selectedTags = FXCollections.observableArrayList();
	private final StringProperty label = new SimpleStringProperty();
	private final BooleanProperty dragDetected = new SimpleBooleanProperty();

	private final ObjectProperty<Tag> tagToDelete = new SimpleObjectProperty<Tag>(); // Tag to be delete if any
	
	public TagListViewModel(TagService tagService) {
		this.tagService = tagService;
		allTags.addAll(tagService.findAll());
		
		renderedTags.setAll(allTags);
		
		FXUtils.addSimpleChangeListener(label, this::filter);
	}
	
	@Override
	public void selectTag(Tag tag, Boolean isSelected) {
		if (isSelected) {
			selectedTags.add(tag);
		} else {
			selectedTags.remove(tag);
		}
	}

	@Override
	public void deleteTag(Tag tag) {
		tagToDelete.set(tag);
		selectedTags.remove(tag);
		renderedTags.remove(tag);
	}
	
	public void filter(String searchText) {
		renderedTags.setAll(allTags.stream()
				.filter(tag -> searchText ==  null || searchText.isBlank() || tag.getLabel().toLowerCase().contains(searchText.toLowerCase()))
	            .collect(Collectors.toCollection(FXCollections::observableArrayList)));
		selectedTags.retainAll(renderedTags);
	}

	public void createTag() {
		Tag tag = new Tag();
		tag.setLabel(getLabel());
		tagService.save(tag);
		
		renderedTags.add(tag);
		allTags.add(tag);
	}
	
	public void unselectAllTag() {
		selectedTags.clear();
		
		// Best way to reset css TODO CLEAN IT
		List<Tag> renderedTags = new ArrayList<Tag>(getRenderedTags());
		this.renderedTags.clear();
		this.renderedTags.setAll(renderedTags);
	}

	@Override
	public ObservableList<Tag> getSelectedTags() {
		return selectedTags;
	}

	public final BooleanProperty dragDetectedProperty() {
		return this.dragDetected;
	}

	public final boolean isDragDetected() {
		return this.dragDetectedProperty().get();
	}

	public final void setDragDetected(final boolean dragDetected) {
		this.dragDetectedProperty().set(dragDetected);
	}

	public final ListProperty<Tag> renderedTagsProperty() {
		return this.renderedTags;
	}

	public final ObservableList<Tag> getRenderedTags() {
		return this.renderedTagsProperty().get();
	}
	
	public final void setRenderedTags(final ObservableList<Tag> renderedTags) {
		this.renderedTagsProperty().set(renderedTags);
	}

	public final ObjectProperty<Tag> tagToDeleteProperty() {
		return this.tagToDelete;
	}

	public final Tag getTagToDelete() {
		return this.tagToDeleteProperty().get();
	}
	
	public final void setTagToDelete(final Tag tagToDelete) {
		this.tagToDeleteProperty().set(tagToDelete);
	}

	public final StringProperty labelProperty() {
		return this.label;
	}

	public final String getLabel() {
		return this.labelProperty().get();
	}
	
	public final void setLabel(final String label) {
		this.labelProperty().set(label);
	}
}