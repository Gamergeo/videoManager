	package com.gamergeo.project.videomanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.persistence.VideoDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VideoServiceImpl implements VideoService { //extends HibernateDatabaseServiceImpl<Video> 
	
	@Autowired
	VideoDao videoDao;

	@Override
	@Transactional
	public List<Video> getVideoList() {
		log.info("Require videos list");
		List<Video> videos = videoDao.findAll();
		log.info("Videos list loaded");
		return videos;
	}
	
	@Override
	@Transactional
	public List<Video> getVideoList(String title) {
		log.info("Require videos list");
		List<Video> videos = videoDao.findByTitleContaining(title);
		log.info("Videos list loaded");
		return videos;
	}
	

	@Override
	@Transactional
	public void save(Video video) {
		videoDao.save(video);
	}

}