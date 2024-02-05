package com.gamergeo.project.videomanager.gui.viewmodel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.javafx.viewmodel.AbstractViewModel;
import com.gamergeo.project.videomanager.gui.view.SearchView;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.event.ActionEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor_ ={@Lazy})
public class SearchViewModel extends AbstractViewModel<SearchView> {
	
	@Lazy
	TableViewModel table;
	
	@Override
	public void init() {
		super.init();
		view.search.setOnAction(this::filter);
	}	
	
	/**
	 * Filter a video list from criteria
	 * @param videos
	 */
	@Lazy
    public void filter(ActionEvent ActionEvent) {
    	String title = view.title.getText();
    	Double rating = view.rating.getRating();
    	List<Tag> withTags = new ArrayList<Tag>();
    	List<Tag> withoutTags = new ArrayList<Tag>();
    	
		log.info("Filter with params (title=" + title + ", rating=" + rating + ", with: " + withTags.toString() + ", without" + withoutTags.toString());
		
		table.getVideos().filtered(video -> title ==  null || title.isBlank() || video.getTitle().contains(title))
	            .filtered(video -> rating == null || rating == 0 || video.getRating() >= rating); // Filtrage par note minimale
//	            .filter(video -> withTags.isEmpty() || video.getTags().stream().anyMatch(tag -> withTags.contains(tag))) // Vérifie la présence d'au moins un tag recherché
//	            .filter(video -> withoutTags.isEmpty() || video.getTags().stream().noneMatch(tag -> withoutTags.contains(tag))) // Exclut les vidéos avec des tags non désirés
    }

}
