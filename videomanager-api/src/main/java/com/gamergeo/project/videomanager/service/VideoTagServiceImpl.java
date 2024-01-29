	package com.gamergeo.project.videomanager.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamergeo.project.videomanager.model.VideoTag;
import com.gamergeo.project.videomanager.persistence.VideoTagDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VideoTagServiceImpl implements VideoTagService { //extends HibernateDatabaseServiceImpl<Video> 
	
	@Autowired
	VideoTagDao dao;

	@Override
	@Transactional
	public List<VideoTag> findAll() {
		log.info("Require videos tag list");
		List<VideoTag> tags = dao.findAll();
		log.info("Videos tag list loaded");
		return tags;
	}

	@Override
	@Transactional
	public VideoTag findById(Long id) {
		return dao.findById(id).orElseThrow(() -> {
			String errorMessage = "Error: Video tag not found (id=" + id+")";
			log.error(errorMessage);
			throw new NoSuchElementException(errorMessage);
		});
		
	}
	
}