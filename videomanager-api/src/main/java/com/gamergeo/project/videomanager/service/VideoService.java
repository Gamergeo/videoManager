package com.gamergeo.project.videomanager.service;

import java.util.List;

import com.gamergeo.project.videomanager.model.Video;

public interface VideoService {
	
	List<Video> findAll();

	void save(Video video);

	List<Video> findBy(String title, Double minimalRating, List<Long> searchWithTagIds, List<Long> searchWithoutTagIds);

	Video findById(Long id);

	/**
	 * Find a random video
	 */
	Video randomVideo(String title, Double minimalRating, List<Long> searchWithTagIds, List<Long> searchWithoutTagIds);

}
