package com.gamergeo.project.videomanager.gui.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.gui.mapper.AbstractMapper;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;
import com.gamergeo.project.videomanager.model.Video;

@Component
public class VideoMapperImpl extends AbstractMapper<Video, VideoViewModel> implements VideoMapper {
	
	@Autowired
	private VideoTagMapper videoTagMapper;
	
	@Override
	public VideoViewModel toViewModel(Video video) {
		VideoViewModel videoViewModel = new VideoViewModel();
		videoViewModel.setTitle(video.getTitle());
		videoViewModel.setUrl(video.getUrl());
		videoViewModel.setVideoTagList(videoTagMapper.getViewModels(video.getVideoTags()));
		videoViewModel.setAddedDate(video.getAddedDate());
		
		return videoViewModel;
	}
}
