package com.gamergeo.project.videomanager.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gamergeo.project.videomanager.model.VideoTag;

public interface VideoTagDao extends CrudRepository<VideoTag, Integer> {
	
	List<VideoTag> findAll();
}