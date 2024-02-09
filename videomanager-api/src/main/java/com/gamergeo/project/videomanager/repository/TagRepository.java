package com.gamergeo.project.videomanager.repository;

import org.springframework.data.repository.CrudRepository;

import com.gamergeo.project.videomanager.model.Tag;

public interface TagRepository extends CrudRepository<Tag, Long> {
	
}