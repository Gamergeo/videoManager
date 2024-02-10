package com.gamergeo.project.videomanager.viewmodel.taglist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
public class TagListViewModel implements ViewModel, TagParentViewModel {
	
	private final TagService tagService;

	private final List<Tag> allTags = new ArrayList<Tag>();
	private final ListProperty<Tag> renderedTags = new SimpleListProperty<Tag>(FXCollections.observableArrayList());
	private final ObservableList<Tag> selectedTags = FXCollections.observableArrayList();
	private final BooleanProperty dragDetected = new SimpleBooleanProperty();

	private final ObjectProperty<Tag> tagToDelete = new SimpleObjectProperty<Tag>(); // Tag to be delete if any
	
	public TagListViewModel(TagService tagService) {
		this.tagService = tagService;
		allTags.addAll(tagService.findAll());
		renderedTags.setAll(allTags);
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
	
}