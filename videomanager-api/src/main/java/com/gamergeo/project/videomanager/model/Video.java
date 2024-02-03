package com.gamergeo.project.videomanager.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = DatabaseName.VIDEO.TABLE)
@Table(name = DatabaseName.VIDEO.TABLE)
@Data
public class Video {//, HibernateModel {

//	private static final long serialVersionUID = -2649188449485629485L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;

	@Column(length = 2000)
	protected String title;

	@Column(length = 2000)
	protected String url;
	
	protected LocalDate addedDate;
	
	protected Double rating;
	
	@ManyToMany(fetch = FetchType.EAGER)
	protected List<Tag> tags;
	
	protected Boolean disabled;
}
