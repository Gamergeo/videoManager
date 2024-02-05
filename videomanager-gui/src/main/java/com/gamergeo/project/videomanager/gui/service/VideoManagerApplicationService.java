//package com.gamergeo.project.videomanager.gui.service;
//
//import java.util.List;
//
//import org.controlsfx.control.Rating;
//
//import com.gamergeo.project.videomanager.gui.view.TagView;
//import com.gamergeo.project.videomanager.gui.viewmodel.old.TagViewModelOld;
//import com.gamergeo.project.videomanager.gui.viewmodel.old.VideoViewModelOld;
//import com.gamergeo.project.videomanager.model.Video;
//import com.gamergeo.project.videomanager.model.Tag;
//
//import javafx.collections.ObservableList;
//import javafx.scene.layout.Pane;
//
//public interface VideoManagerApplicationService {
//
//	TagView addTagToNode(Pane node, TagViewModelOld tag);
//
//	TagView addTagToNode(Pane node, Tag tag);
//
//	List<TagView> addTagsToNode(Pane node, Iterable<Tag> tags);
//
//	List<TagView> addTagsToNode(Pane node, ObservableList<TagViewModelOld> tags);
//
//	String getIdData(Long id);
//
//	List<Long> getIdFromData(String data);
//
//	List<VideoViewModelOld> getVideoViewModel(List<Video> videoList);
//
//	/**
//	 * Allow only demi value for rating
//	 */
//	void semiValueRating(Rating rating);
//
//}
