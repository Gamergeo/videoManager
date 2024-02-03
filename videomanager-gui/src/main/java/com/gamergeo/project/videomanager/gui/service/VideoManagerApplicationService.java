package com.gamergeo.project.videomanager.gui.service;

import java.util.List;

import com.gamergeo.project.videomanager.gui.view.TagView;
import com.gamergeo.project.videomanager.gui.viewmodel.TagViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

public interface VideoManagerApplicationService {

	TagView addTagToNode(Pane node, TagViewModel tag);

	TagView addTagToNode(Pane node, Tag tag);

	List<TagView> addTagsToNode(Pane node, Iterable<Tag> tags);

	List<TagView> addTagsToNode(Pane node, ObservableList<TagViewModel> tags);

	String getIdData(Long id);

	List<Long> getIdFromData(String data);

	List<VideoViewModel> getVideoViewModel(List<Video> videoList);

}
