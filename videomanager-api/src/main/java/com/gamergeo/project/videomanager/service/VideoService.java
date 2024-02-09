package com.gamergeo.project.videomanager.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.repository.VideoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VideoService extends ApplicationCrudService<Video> {

	private final VideoRepository repository;
	
//	private final TagService tagService;

	@Override
	public VideoRepository getRepository() {
		return repository;
	}
	
//	/**
//	 * Filter a videos list from search criteria
//	 */
//	@Override
//	public List<Video> filter(List<Video> videos, 
//								String title, Double minimalRating, List<Tag> searchWithTagIds, List<Tag> searchWithoutTagIds) {
//		log.info("Filter with params (title=" + title + ", rating=" + minimalRating + ", with: " + searchWithTagIds.toString() + ", without" + searchWithoutTagIds.toString());
//		
//		return videos.stream()
//				.filter(video -> title ==  null || title.isBlank() || video.getTitle().contains(title))
//	            .filter(video -> minimalRating == null || minimalRating == 0 || video.getRating() >= minimalRating) // Filtrage par note minimale
//	            .filter(video -> searchWithTagIds.isEmpty() || video.getTags().stream().anyMatch(tag -> searchWithTagIds.contains(tag))) // Vérifie la présence d'au moins un tag recherché
//	            .filter(video -> searchWithoutTagIds.isEmpty() || video.getTags().stream().noneMatch(tag -> searchWithoutTagIds.contains(tag))) // Exclut les vidéos avec des tags non désirés
//	            .collect(Collectors.toList()); // Collecte les résultats correspondants
//	}
	
	/**
	 * Find a random video in list
	 */
	public Video randomVideo(List<Video> videos) {
	    if (videos.isEmpty()) {
	        return null;
	    }
	    
	    int randomNumber = new Random().nextInt(videos.size());
	    return videos.get(randomNumber);
	}
}