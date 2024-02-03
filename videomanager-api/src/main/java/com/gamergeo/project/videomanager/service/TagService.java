package com.gamergeo.project.videomanager.service;

import java.util.List;

import com.gamergeo.project.videomanager.model.Tag;

public interface TagService { //extends HibernateDatabaseService<Video> {
	
	List<Tag> findAll();

	Tag findById(Long id);

	List<Tag> findAllById(List<Long> ids);

	List<Long> findAllIds();

}
