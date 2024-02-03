package com.gamergeo.project.videomanager.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gamergeo.project.videomanager.model.Tag;

public interface TagDao extends CrudRepository<Tag, Long> {
	
	List<Tag> findAll();
	
}