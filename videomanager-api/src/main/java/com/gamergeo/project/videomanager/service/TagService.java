	package com.gamergeo.project.videomanager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.repository.TagRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TagService extends ApplicationCrudService<Tag> {
	
	private final TagRepository repository;
	private final VideoService videoService;
	
	@Override
	public TagRepository getRepository() {
		return repository;
	}

	@Transactional
	public List<Long> findAllIds() {
		return findAll().stream()
                .map(Tag::getId)
                .collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public void delete(Tag tag) {
		List<Video> videos = videoService.findAll();
		
		videos.forEach((video) -> {
			if (video.getTags().remove(tag)) {
				videoService.save(video);
			}
		});
		
		repository.delete(tag);
	}
}