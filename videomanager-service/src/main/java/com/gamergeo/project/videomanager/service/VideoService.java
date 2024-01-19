package com.gamergeo.project.videomanager.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.gamergeo.project.videomanager.model.Video;


@Service
public interface VideoService {
	
	List<Video> getVideoList();

//	Video addNewVideo(Video video);
//
//	Video update(Video video);
//
//	Video getRandomVideo(List<String> wantedCategories, boolean bestof, boolean disabled);
}
