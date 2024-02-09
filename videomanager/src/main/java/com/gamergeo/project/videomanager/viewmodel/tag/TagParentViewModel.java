package com.gamergeo.project.videomanager.viewmodel.tag;

import com.gamergeo.project.videomanager.model.Tag;

import javafx.collections.ObservableList;

public interface TagParentViewModel {
	
	/**
	 * Init child view model list / bind 
	 */
	public void initTagViewModel(TagViewModel tagViewModel, Tag tag);
	
	public ObservableList<Tag> getRenderedTags();
}