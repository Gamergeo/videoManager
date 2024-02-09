package com.gamergeo.project.videomanager.helper;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.view.tag.TagView;
import com.gamergeo.project.videomanager.viewmodel.tag.TagParentViewModel;
import com.gamergeo.project.videomanager.viewmodel.tag.TagViewModel;

import de.saxsys.mvvmfx.ViewTuple;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

@Component
public class TagParentHelper {
	
	public void initTags(Pane tagPane, TagParentViewModel viewModel) {
		renderTags(viewModel.getRenderedTags(), tagPane, viewModel);
		FXUtils.addSimpleListChangeListener(viewModel.getRenderedTags(), (tags) -> renderTags(tags, tagPane, viewModel));
	}
	
	private void renderTags(ObservableList<Tag> tags, Pane tagPane, TagParentViewModel viewModel) {
		tagPane.getChildren().clear();
		tags.forEach((tag) -> renderTag(tag, tagPane, viewModel));
	}
	
	private void renderTag(Tag tag, Pane tagPane, TagParentViewModel viewModel) {
		ViewTuple<TagView, TagViewModel> tuple = FXUtils.load(TagView.class);
		viewModel.initTagViewModel(tuple.getViewModel(), tag);
		tagPane.getChildren().add(tuple.getView());
	}

}