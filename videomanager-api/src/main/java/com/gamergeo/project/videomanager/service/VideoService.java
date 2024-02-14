package com.gamergeo.project.videomanager.service;

import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.repository.VideoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoService extends ApplicationCrudService<Video> {

	private final VideoRepository repository;
	
	@Override
	public VideoRepository getRepository() {
		return repository;
	}
	
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

	public Video refresh(Video video) {
		return repository.findById(video.getId()).orElseThrow();
	}
	
	public List<Video> findAllEnabled() {
		log.info("Load all enabled videos");
		return repository.findByDisabled(false);
	}
	
	/**
	 * Add a video, only if not alreadyu found in base (link matters)
	 */
	@Transactional
	public Video addVideo(Video video) {
		
		if (!findVideoByLink(video.getUrl()).isEmpty()) {
			log.info("Can't add video, already in base: " + video.getTitle());
			return null;
		}
		
		return save(video);
	}
	
	/**
	 * @return video by url, but with & and &amp; alike 
	 */
	public List<Video> findVideoByLink(String url) {
		return repository.findByUrlWithAmpersandNative(url);
	}
}