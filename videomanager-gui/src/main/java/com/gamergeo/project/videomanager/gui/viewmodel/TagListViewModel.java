package com.gamergeo.project.videomanager.gui.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.javafx.viewmodel.AbstractChildViewModel;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.service.TagService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
public class TagListViewModel extends AbstractChildViewModel<SceneViewModel> {

	private final TagService tagService;
	
	private final List<Tag> allTags = new ArrayList<Tag>();
	private final ObservableList<Tag> tags = FXCollections.observableArrayList();
	
	public TagListViewModel(TagService tagService) {
		this.tagService = tagService;
	}
	
	@Override
	public void init() {
		allTags.addAll(tagService.findAll());
		tags.setAll(allTags);
	}

	public ObservableList<Tag> tags() {
		return tags;
	}
}
