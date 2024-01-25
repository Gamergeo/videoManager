package com.gamergeo.project.videomanager.service;

import java.util.List;

import com.gamergeo.project.videomanager.model.Video;

public interface VideoService { //extends HibernateDatabaseService<Video> {
	
	List<Video> findAll();

	List<Video> findBy(String title);

	void save(Video video);

	/**
	 * Find a random video
	 */
	Video randomVideo(String title);
}
