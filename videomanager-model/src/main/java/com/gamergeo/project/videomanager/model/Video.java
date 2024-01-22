package com.gamergeo.project.videomanager.model;

import java.time.LocalDate;
import java.util.List;

import com.gamergeo.lib.gamlib.mapper.MappedModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity(name = DatabaseName.VIDEO.TABLE)
@Table(name = DatabaseName.VIDEO.TABLE)
public class Video implements MappedModel {//, HibernateModel {

//	private static final long serialVersionUID = -2649188449485629485L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;

	protected String title;

	protected String url;
	
	@Transient
	protected LocalDate addedDate;
	
	@Transient
	protected List<VideoTag> videoTags;
	
//	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<VideoTag> getVideoTags() {
		return videoTags;
	}

	public void setVideoTags(List<VideoTag> videoTags) {
		this.videoTags = videoTags;
	}

	public LocalDate getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(LocalDate addedDate) {
		this.addedDate = addedDate;
	}
}
