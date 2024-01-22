package com.gamergeo.project.videomanager.service;

import java.util.List;

import com.gamergeo.project.videomanager.model.Video;

public interface VideoService { //extends HibernateDatabaseService<Video> {
	
	List<Video> getVideoList();

	Video getVideo();
}
