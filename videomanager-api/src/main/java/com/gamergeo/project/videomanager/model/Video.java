package com.gamergeo.project.videomanager.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity(name = DatabaseName.VIDEO.TABLE)
@Table(name = DatabaseName.VIDEO.TABLE)
@Data
public class Video {//, HibernateModel {

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
	
}
