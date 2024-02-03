	package com.gamergeo.project.videomanager.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.persistence.VideoDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService { //extends HibernateDatabaseServiceImpl<Video> 
	
	@Autowired
	VideoDao dao;
	
	@Autowired
	TagService tagService;

	@Override
	@Transactional
	public List<Video> findAll() {
		log.info("Require videos list");
		List<Video> videos = dao.findAll();
		log.info("Videos list loaded");
		return videos;
	}
	
	@Override
	@Transactional
	public List<Video> findBy(String title, Double minimalRating, List<Long> searchWithTagIds, List<Long> searchWithoutTagIds) {
		log.info("Require videos list");
		
		if (searchWithTagIds.isEmpty()) {
			searchWithTagIds = tagService.findAllIds();
		}
		
		if (searchWithoutTagIds.isEmpty()) {
			searchWithoutTagIds.add((long) 0);
		}
		
		List<Video> videos = dao.findByTitleContainingAndTagsIncludedAndExcluded(title, minimalRating, searchWithTagIds, searchWithoutTagIds);
		log.info("Videos list loaded");
		return videos;
	}
	

	@Override
	@Transactional
	public void save(Video video) {
		dao.save(video);
	}
	
	/**
	 * Find a random video
	 */
	@Override
	@Transactional
	public Video randomVideo(String title) {
		int count =  (int) dao.count();
        int randomNumber = new Random().nextInt(count) + 1;
		
		return dao.findById((long) randomNumber).orElseThrow(() -> {
			String errorMessage = "Error: Video not found (id=" + randomNumber+")";
			log.error(errorMessage);
			throw new NoSuchElementException(errorMessage);
		});
	}
}