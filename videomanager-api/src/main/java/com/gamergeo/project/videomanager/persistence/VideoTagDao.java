package com.gamergeo.project.videomanager.persistence;

import org.springframework.data.repository.CrudRepository;

import com.gamergeo.project.videomanager.model.VideoTag;

public interface VideoTagDao extends CrudRepository<VideoTag, Integer> {


}