package com.gamergeo.project.videomanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gamergeo.project.videomanager.model.Video;

public interface VideoRepository extends CrudRepository<Video, Long> {
	
	List<Video> findAll();
	
	List<Video> findByDisabled(boolean disabled);
	
	List<Video> findByDisabledAndTitleContaining(boolean disabled, String title);
	
    @Query(value = "SELECT * FROM video WHERE REPLACE(url, '&amp;', '&') LIKE %?1% OR REPLACE(url, '&', '&amp;') LIKE %?1%", nativeQuery = true)
    List<Video> findByUrlWithAmpersandNative(String url);

}