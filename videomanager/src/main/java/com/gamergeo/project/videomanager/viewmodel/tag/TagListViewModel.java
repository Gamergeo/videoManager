package com.gamergeo.project.videomanager.viewmodel.tag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.service.TagService;

import de.saxsys.mvvmfx.ViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
public class TagListViewModel implements ViewModel {

	private final TagService tagService;
	
	private final List<TagViewModel> tagViewModels = new ArrayList<TagViewModel>();
	
	private final List<Tag> allTags = new ArrayList<Tag>();
	private final ObservableList<Tag> displayedTags = FXCollections.observableArrayList();
	private final ObservableList<Tag> selectedTags = FXCollections.observableArrayList();
	
//	private final List<TagViewModel> tagViewModel = new ArrayList<TagViewModel>();
	
	public TagListViewModel(TagService tagService) {
		this.tagService = tagService;
		allTags.addAll(tagService.findAll());
		displayedTags.setAll(allTags);
	}
	
	public ObservableList<Tag> getDisplayedTags() {
		return displayedTags;
	}
	
	public void addTag(TagViewModel tagViewModel, Tag tag) {
		tagViewModels.add(tagViewModel);
		tagViewModel.setTag(tag);
		tagViewModel.setSelectable(true);
	}
	
//	public void onDragDetected() {
//		parent.onDragDetected();
//	}
	
//	/**
//	 * Select / Unselect a tag
//	 * @return true if tag is selected, false if unselected
//	 */
//	@Override
//	public boolean onTagClick(Tag tag) {
//		if (selectedTags.contains(tag)) {
//			selectedTags.remove(tag);
//			return false;
//		} else {
//			selectedTags.add(tag);
//			return true;
//		}
//	}
	
	public ObservableList<Tag> getSelectedTags() {
		return selectedTags;
	}
}