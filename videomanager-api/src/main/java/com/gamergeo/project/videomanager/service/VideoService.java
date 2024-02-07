package com.gamergeo.project.videomanager.service;

import java.util.List;

import com.gamergeo.lib.gamlib.javafx.service.ModelService;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.model.Video;

public interface VideoService extends ModelService<Video> {
	
	List<Video> findAll();

	Video findById(Long id);

	/**
	 * Filter a videos list from search criteria
	 */
	List<Video> filter(List<Video> videos, String title, Double minimalRating, List<Tag> searchWithTagIds,
			List<Tag> searchWithoutTagIds);

	/**
	 * Find a random video in list
	 */
	Video randomVideo(List<Video> videos);

}
