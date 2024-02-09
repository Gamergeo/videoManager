package com.gamergeo.project.videomanager.viewmodel.tag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.service.TagService;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
public class TagListViewModel implements ViewModel, TagParentViewModel {

	protected final List<TagViewModel> tagViewModels = new ArrayList<TagViewModel>();
	private final List<Tag> allTags = new ArrayList<Tag>();
	protected final ObservableList<Tag> renderedTags = FXCollections.observableArrayList();
	private final ObservableList<Tag> selectedTags = FXCollections.observableArrayList();
	private final BooleanProperty dragDetected = new SimpleBooleanProperty();
	
	public TagListViewModel(TagService tagService) {
		allTags.addAll(tagService.findAll());
		renderedTags.setAll(allTags);
	}
	
	/**
	 * Init child view model list / bind 
	 */
	@Override
	public void initTagViewModel(TagViewModel tagViewModel, Tag tag) {
		tagViewModels.add(tagViewModel);
		tagViewModel.setTag(tag);
		tagViewModel.setSelectable(true);
		FXUtils.addSimpleChangeListener(tagViewModel.clickedProperty(), (isClicked) -> selectTag(tag, isClicked));
	}
	
	private void selectTag(Tag tag, Boolean isClicked) {
		if (isClicked) {
			selectedTags.add(tag);
		} else {
			selectedTags.remove(tag);
		}
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
	
	public ObservableList<Tag> getRenderedTags() {
		return renderedTags;
	}
}