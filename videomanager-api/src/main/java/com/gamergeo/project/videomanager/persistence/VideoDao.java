package com.gamergeo.project.videomanager.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gamergeo.project.videomanager.model.Video;

//@Repository("videoDao")
public interface VideoDao extends CrudRepository<Video, Long> {
	
	List<Video> findAll();
	
	List<Video> findByTitleContaining(String title);
	
    @Query(value = "SELECT v.* FROM video v " +
            "JOIN video_tags vt ON v.id = vt.video_id " +
            "JOIN tag t ON vt.tags_id = t.id " +
            "WHERE v.title LIKE %:title% " +
            "AND t.id IN :includeTags " +
            "AND t.id NOT IN :excludeTags", nativeQuery = true)
	List<Video> findByTitleContainingAndTagsIncludedAndExcluded(@Param("title") String title, 
	                                                          @Param("includeTags") List<Long> includeTags, 
	                                                          @Param("excludeTags") List<Long> excludeTags);
	
}