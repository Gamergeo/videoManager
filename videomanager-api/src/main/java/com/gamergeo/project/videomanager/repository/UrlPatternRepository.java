package com.gamergeo.project.videomanager.repository;

import org.springframework.data.repository.CrudRepository;

import com.gamergeo.project.videomanager.model.UrlPattern;

public interface UrlPatternRepository extends CrudRepository<UrlPattern, Long> {
	
}