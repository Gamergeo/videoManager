package com.gamergeo.project.videomanager.gui.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.gamergeo.project.videomanager.gui.ApplicationConstant;
import com.gamergeo.project.videomanager.gui.view.VideoTagView;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;
import com.gamergeo.project.videomanager.model.VideoTag;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

@Service
public class VideoManagerApplicationServiceImpl implements VideoManagerApplicationService {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public VideoTagView addTagToNode(Pane node, VideoTagViewModel tag) {
   		VideoTagView tagView = applicationContext.getBean(VideoTagView.class);
		tagView.load();
		tagView.getController().setTag(tag);
		
		// Todo : ne pas Add ici pour laisser la verification au controlleur
		node.getChildren().add(tagView.getRoot());
		return tagView;
	}

	@Override
	public VideoTagView addTagToNode(Pane node, VideoTag tag) {
		VideoTagViewModel tagModel = new VideoTagViewModel(tag);
		return addTagToNode(node, tagModel);
	}

	@Override
	public List<VideoTagView> addTagsToNode(Pane node, Iterable<VideoTag> tags) {
		List<VideoTagView> views = new ArrayList<VideoTagView>();
		
		tags.forEach((tag) -> {
			views.add(addTagToNode(node, tag));
		});
		
		return views;
	}
	
	@Override
	public List<VideoTagView> addTagsToNode(Pane node, ObservableList<VideoTagViewModel> tags) {
		List<VideoTagView> views = new ArrayList<VideoTagView>();
		
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
}

