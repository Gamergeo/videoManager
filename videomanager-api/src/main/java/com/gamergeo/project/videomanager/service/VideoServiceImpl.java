	package com.gamergeo.project.videomanager.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.persistence.VideoDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService {
	
	@Autowired
	VideoDao dao;
	
	@Autowired
	TagService tagService;
	
	@Override
	@Transactional
	public Video findById(Long id) {
		log.info("Load video: " + id);
		return dao.findById(id).orElseThrow(() -> {
			String errorMessage = "Error: Video not found (id=" + id+")";
			log.error(errorMessage);
			throw new NoSuchElementException(errorMessage);
		});
	}
	
	@Override
	@Transactional
	public List<Video> findAll() {
		log.info("Load all videos list");
		List<Video> videos = dao.findByDisabled(false);
		log.info("Videos list loaded");
		return videos;
	}
	
	/**
	 * Filter a videos list from search criteria
	 */
	@Override
	public List<Video> filter(List<Video> videos, 
								String title, Double minimalRating, List<Tag> searchWithTagIds, List<Tag> searchWithoutTagIds) {
		return videos.stream()
				.filter(video -> title ==  null || title.isBlank() || video.getTitle().contains(title))
	            .filter(video -> minimalRating == null || minimalRating == 0 || (video.getRating() != null && video.getRating() >= minimalRating)) // Filtrage par note minimale
	            .filter(video -> searchWithTagIds.isEmpty() || video.getTags().stream().anyMatch(tag -> searchWithTagIds.contains(tag))) // Vérifie la présence d'au moins un tag recherché
	            .filter(video -> searchWithoutTagIds.isEmpty() || video.getTags().stream().noneMatch(tag -> searchWithoutTagIds.contains(tag))) // Exclut les vidéos avec des tags non désirés
	            .collect(Collectors.toList()); // Collecte les résultats correspondants
	}

	@Override
	@Transactional
	public void save(Video video) {
		dao.save(video);
	}
	
	/**
	 * Find a random video in list
	 */
	@Override
	public Video randomVideo(List<Video> videos) {
	    if (videos.isEmpty()) {
	        return null;
	    }
	    
	    int randomNumber = new Random().nextInt(videos.size());
	    return videos.get(randomNumber);
	}
}