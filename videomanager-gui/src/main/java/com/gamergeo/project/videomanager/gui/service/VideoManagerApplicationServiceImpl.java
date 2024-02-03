package com.gamergeo.project.videomanager.gui.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gamergeo.project.videomanager.gui.ApplicationConstant;
import com.gamergeo.project.videomanager.gui.view.TagView;
import com.gamergeo.project.videomanager.gui.viewmodel.TagViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

@Service
public class VideoManagerApplicationServiceImpl implements VideoManagerApplicationService {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public TagView addTagToNode(Pane node, TagViewModel tag) {
   		TagView tagView = applicationContext.getBean(TagView.class);
		tagView.load();
		tagView.getController().setTag(tag);
		
		// Todo : ne pas Add ici pour laisser la verification au controlleur
		node.getChildren().add(tagView.getRoot());
		return tagView;
	}

	@Override
	public TagView addTagToNode(Pane node, Tag tag) {
		TagViewModel tagModel = new TagViewModel(tag);
		return addTagToNode(node, tagModel);
	}

	@Override
	public List<TagView> addTagsToNode(Pane node, Iterable<Tag> tags) {
		List<TagView> views = new ArrayList<TagView>();
		
		tags.forEach((tag) -> {
			views.add(addTagToNode(node, tag));
		});
		
		return views;
	}
	
	@Override
	public List<TagView> addTagsToNode(Pane node, ObservableList<TagViewModel> tags) {
		List<TagView> views = new ArrayList<TagView>();
		
		tags.forEach((tag) -> {
			views.add(addTagToNode(node, tag));
		});
		
		return views;
	}
	
	@Override
	public String getIdData(Long id) {
		return ApplicationConstant.ID_DATA + id.toString() + ApplicationConstant.SEPARATOR;
	}

	@Override
	public List<Long> getIdFromData(String data) {
        Stream<String> stringStream = Stream.of(data.split(ApplicationConstant.SEPARATOR));
        return stringStream.map((id) -> Long.valueOf(id.replace(ApplicationConstant.ID_DATA, ""))).collect(Collectors.toList());
	}
	
	@Override
	public List<VideoViewModel> getVideoViewModel(List<Video> videoList) {
		return videoList.stream()
				 .map((video) -> new VideoViewModel(video))
				 .collect(Collectors.toList());
	}
}

