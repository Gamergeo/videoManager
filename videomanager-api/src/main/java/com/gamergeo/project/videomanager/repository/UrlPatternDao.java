package com.gamergeo.project.videomanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gamergeo.project.videomanager.model.UrlPattern;

public interface UrlPatternDao extends CrudRepository<UrlPattern, Long> {
	
	List<UrlPattern> findAll();
	
}