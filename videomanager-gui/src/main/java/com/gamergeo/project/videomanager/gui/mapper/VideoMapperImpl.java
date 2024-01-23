package com.gamergeo.project.videomanager.gui.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.mapper.AbstractMVMMapper;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;
import com.gamergeo.project.videomanager.model.Video;

@Component
public class VideoMapperImpl extends AbstractMVMMapper<Video, VideoViewModel> implements VideoMapper {
	
	@Autowired
	private VideoTagMapper videoTagMapper;
	
	@Override
	public VideoViewModel getViewModel(Video video) {
		VideoViewModel videoViewModel = new VideoViewModel();
		videoViewModel.setTitle(video.getTitle());
		videoViewModel.setUrl(video.getUrl());
		// TODO
//		videoViewModel.setVideoTagList(videoTagMapper.getViewModels(video.getVideoTags()));
		videoViewModel.setAddedDate(video.getAddedDate());
		
		return videoViewModel;
	}

	@Override
	public Video getModel(VideoViewModel model) {
		// TODO Auto-generated method stub
		return null;
	}
}
