package com.gamergeo.project.videomanager.gui.mapper;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.mapper.AbstractMVMMapper;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;
import com.gamergeo.project.videomanager.model.VideoTag;

@Component
public class VideoTagMapperImpl extends AbstractMVMMapper<VideoTag, VideoTagViewModel> implements VideoTagMapper {
	
	@Override
	public VideoTagViewModel getViewModel(VideoTag videoTag) {
		VideoTagViewModel videoTagViewModel = new VideoTagViewModel();
		videoTagViewModel.setText(videoTag.getText());
		return videoTagViewModel;
	}

	@Override
	public VideoTag getModel(VideoTagViewModel model) {
		// TODO Auto-generated method stub
		return null;
	}

}
