package com.gamergeo.project.videomanager.service;

import java.util.List;

import com.gamergeo.project.videomanager.model.Video;

public interface VideoService { //extends HibernateDatabaseService<Video> {
	
	List<Video> findAll();

	void save(Video video);

	/**
	 * Find a random video
	 */
	Video randomVideo(String title);

	List<Video> findBy(String title, List<Long> searchWithTagIds, List<Long> searchWithoutTagIds);
}
