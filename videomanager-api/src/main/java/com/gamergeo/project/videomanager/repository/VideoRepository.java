package com.gamergeo.project.videomanager.repository;

import java.util.List;

import com.gamergeo.lib.gamlib.javafx.repository.ModelRepository;
import com.gamergeo.project.videomanager.model.Video;

//@Repository("videoDao")
public interface VideoRepository extends ModelRepository<Video> {
	
	List<Video> findAll();
	
	List<Video> findByDisabled(boolean disabled);
	
	List<Video> findByDisabledAndTitleContaining(boolean disabled, String title);
	
//    @Query(value = "SELECT DISTINCT v.* FROM video v " +
//            "JOIN video_tags vt ON v.id = vt.video_id " +
//            "JOIN tag t ON vt.tags_id = t.id " +
//            "WHERE v.title LIKE %:title% " +
//            "AND v.rating >= :rating " +
//            "AND t.id IN :includeTags " +
//            "AND t.id NOT IN :excludeTags", nativeQuery = true)
//	List<Video> findByTitleContainingAndTagsIncludedAndExcluded(@Param("title") String title, 
//																@Param("rating") Double minimalRating, 
//	                                                            @Param("includeTags") List<Long> includeTags, 
//	                                                            @Param("excludeTags") List<Long> excludeTags);
	
}