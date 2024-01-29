package com.gamergeo.project.videomanager.gui.service;

import java.util.List;

import com.gamergeo.project.videomanager.gui.view.VideoTagView;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;
import com.gamergeo.project.videomanager.model.VideoTag;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

public interface VideoManagerApplicationService {

	VideoTagView addTagToNode(Pane node, VideoTagViewModel tag);

	VideoTagView addTagToNode(Pane node, VideoTag tag);

	List<VideoTagView> addTagsToNode(Pane node, Iterable<VideoTag> tags);

	List<VideoTagView> addTagsToNode(Pane node, ObservableList<VideoTagViewModel> tags);

	String getIdData(Long id);

	List<Long> getIdFromData(String data);

}
