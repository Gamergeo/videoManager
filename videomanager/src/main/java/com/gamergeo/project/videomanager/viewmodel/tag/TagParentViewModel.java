package com.gamergeo.project.videomanager.viewmodel.tag;

import com.gamergeo.project.videomanager.model.Tag;

import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public interface TagParentViewModel {
	
	public ObservableList<Tag> getRenderedTags();
	
	public ListProperty<Tag> renderedTagsProperty();
	
	public void deleteTag(Tag tag);
	
	default public void selectTag(Tag tag, Boolean isSelected) {}

	default public ObservableList<Tag> getSelectedTags() {
		return FXCollections.emptyObservableList();
	}
}