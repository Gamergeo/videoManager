package com.gamergeo.project.videomanager.view.tag;

import java.util.Comparator;
import java.util.List;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.viewmodel.tag.TagParentViewModel;
import com.gamergeo.project.videomanager.viewmodel.tag.TagViewModel;

import de.saxsys.mvvmfx.ViewTuple;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.layout.Pane;

public interface TagParentView {
	
	/**
	 * Render tags
	 * @param tagPane pane where tag view will be added
	 * @param viewModel current view model
	 * @param selectable true if tags are selectable
	 */
	default public void renderTags(boolean selectable) {
		renderTags(getViewModel().getRenderedTags(), selectable);
		FXUtils.addSimpleListChangeListener(getViewModel().getRenderedTags(), (tags) -> renderTags(tags, selectable));
	}
	
	private void renderTags(ObservableList<Tag> tags, boolean selectable) {
		List<Tag> selectedTags = getViewModel().getSelectedTags();
		getTagPane().getChildren().clear();
		SortedList<Tag> sortedList = new SortedList<Tag>(tags, (tag1, tag2)  -> tag1.getLabel().compareTo(tag2.getLabel()));
				
		sortedList.forEach((tag) -> renderTag(tag, selectable, selectedTags.contains(tag)));
	}
	
	private void renderTag(Tag tag, boolean selectable, boolean selected) {
		ViewTuple<TagView, TagViewModel> tuple = FXUtils.load(TagView.class);
		TagViewModel tagViewModel = tuple.getViewModel();
		tagViewModel.setTag(tag);
		tagViewModel.setSelectable(selectable);
		tagViewModel.setSelected(selected);
		FXUtils.addSimpleChangeListener(tagViewModel.selectedProperty(), (isSelected) -> getViewModel().selectTag(tag, isSelected));
		FXUtils.addEmptyChangeListener(tagViewModel.deletedProperty(), () -> getViewModel().deleteTag(tag));
		getTagPane().getChildren().add(tuple.getView());
	}
	
	public TagParentViewModel getViewModel();
	public Pane getTagPane();
	
}