package com.gamergeo.project.videomanager.service;

import java.util.List;

import com.gamergeo.project.videomanager.model.Video;

public interface VideoService {
	
	List<Video> findAll();

	Video findById(Long id);

	/**
	 * Filter a videos list from search criteria
	 */
//	List<Video> filter(List<Video> videos, String title, Double minimalRating, List<Tag> searchWithTagIds,
//			List<Tag> searchWithoutTagIds);

	/**
	 * Find a random video in list
	 */
	Video randomVideo(List<Video> videos);

	Video save(Video video);

}
