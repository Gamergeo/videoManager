package com.gamergeo.project.videomanager.service;

import java.util.List;

import com.gamergeo.project.videomanager.model.VideoTag;

public interface VideoTagService { //extends HibernateDatabaseService<Video> {
	
	List<VideoTag> findAll();

	VideoTag findById(Long id);

	List<VideoTag> findAllById(List<Long> ids);

}
