package com.gamergeo.project.videomanager.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gamergeo.project.videomanager.model.Video;

//@Repository("videoDao")
public interface VideoDao extends CrudRepository<Video, Long> {
	
	List<Video> findAll();
	
	List<Video> findByTitleContaining(String title);
	
}