	package com.gamergeo.project.videomanager.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

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
		log.info("Load all videos list");
		List<Video> videos = dao.findAll();
		log.info("Videos list loaded");
		return videos;
	}
	
	@Override
	@Transactional
	public List<Video> findBy(String title, Double minimalRating, List<Long> searchWithTagIds, List<Long> searchWithoutTagIds) {
		log.info("Load videos list (title=" + title + ", rating=" + minimalRating + ", with:" + searchWithTagIds.toString() +  ", without:" + searchWithoutTagIds.toString());
		
	    return dao.findByTitleContaining(title).stream()
	            .filter(video -> minimalRating == 0 || (video.getRating() != null && video.getRating() >= minimalRating)) // Filtrage par note minimale
	            .filter(video -> searchWithTagIds.isEmpty() || video.getTags().stream().anyMatch(tag -> searchWithTagIds.contains(tag.getId()))) // Vérifie la présence d'au moins un tag recherché
	            .filter(video -> searchWithoutTagIds.isEmpty() || video.getTags().stream().noneMatch(tag -> searchWithoutTagIds.contains(tag.getId()))) // Exclut les vidéos avec des tags non désirés
	            .collect(Collectors.toList()); // Collecte les résultats correspondants
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