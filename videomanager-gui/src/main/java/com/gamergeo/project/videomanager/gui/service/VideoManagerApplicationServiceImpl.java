//package com.gamergeo.project.videomanager.gui.service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.controlsfx.control.Rating;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Service;
//
//import com.gamergeo.project.videomanager.gui.ApplicationConstant;
//import com.gamergeo.project.videomanager.gui.view.TagView;
//import com.gamergeo.project.videomanager.gui.viewmodel.old.TagViewModelOld;
//import com.gamergeo.project.videomanager.gui.viewmodel.old.VideoViewModelOld;
//import com.gamergeo.project.videomanager.model.Video;
//import com.gamergeo.project.videomanager.model.Tag;
//
//import javafx.collections.ObservableList;
//import javafx.scene.layout.Pane;
//
//@Service
//public class VideoManagerApplicationServiceImpl implements VideoManagerApplicationService {
//	
//	@Autowired
//	private ApplicationContext applicationContext;
//	
//	@Override
//	public TagView addTagToNode(Pane node, TagViewModelOld tag) {
//   		TagView tagView = applicationContext.getBean(TagView.class);
//		tagView.load();
//		tagView.getController().setTag(tag);
//		
//		// Todo : ne pas Add ici pour laisser la verification au controlleur
//		node.getChildren().add(tagView.getRoot());
//		return tagView;
//	}
//
//	@Override
//	public TagView addTagToNode(Pane node, Tag tag) {
//		TagViewModelOld tagModel = new TagViewModelOld(tag);
//		return addTagToNode(node, tagModel);
//	}
//
//	@Override
//	public List<TagView> addTagsToNode(Pane node, Iterable<Tag> tags) {
//		List<TagView> views = new ArrayList<TagView>();
//		
//		tags.forEach((tag) -> {
//			views.add(addTagToNode(node, tag));
//		});
//		
//		return views;
//	}
//	
//	@Override
//	public List<TagView> addTagsToNode(Pane node, ObservableList<TagViewModelOld> tags) {
//		List<TagView> views = new ArrayList<TagView>();
//		
//		tags.forEach((tag) -> {
//			views.add(addTagToNode(node, tag));
//		});
//		
//		return views;
//	}
//	
//	@Override
//	public String getIdData(Long id) {
//		return ApplicationConstant.ID_DATA + id.toString() + ApplicationConstant.SEPARATOR;
//	}
//
//	@Override
//	public List<Long> getIdFromData(String data) {
//        Stream<String> stringStream = Stream.of(data.split(ApplicationConstant.SEPARATOR));
//        return stringStream.map((id) -> Long.valueOf(id.replace(ApplicationConstant.ID_DATA, ""))).collect(Collectors.toList());
//	}
//	
//	@Override
//	public List<VideoViewModelOld> getVideoViewModel(List<Video> videoList) {
//		return videoList.stream()
//				 .map((video) -> new VideoViewModelOld(video))
//				 .collect(Collectors.toList());
//	}
//
//	/**
//	 * Allow only demi value for rating
//	 */
//	@Override
//	public void semiValueRating(Rating rating) {
//		rating.ratingProperty().addListener((obs, oldVal, newVal) -> {
//		    double roundedValue = Math.round(newVal.doubleValue() * 2) / 2.0;
//		    if (!newVal.equals(roundedValue)) { 
//		    	rating.setRating(roundedValue); 
//		    }
//		});
//	}
//}
//
