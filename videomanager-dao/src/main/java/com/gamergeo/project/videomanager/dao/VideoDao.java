package com.gamergeo.project.videomanager.dao;

import org.springframework.data.repository.CrudRepository;

import com.gamergeo.project.videomanager.model.Video;

//@Repository("videoDao")
public interface VideoDao extends CrudRepository<Video, Integer> {
}