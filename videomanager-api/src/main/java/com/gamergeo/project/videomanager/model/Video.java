package com.gamergeo.project.videomanager.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.gamergeo.lib.gamlib.javafx.model.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table
@Data
@Slf4j
public class Video implements Serializable, Model {

	private static final long serialVersionUID = -2649188449485629485L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length = 2000)
	protected String title;

	@Column(length = 2000)
	protected String url;
	
	protected LocalDate addedDate;
	
	protected Double rating;
	
	@ManyToMany(fetch = FetchType.EAGER)
	protected List<Tag> tags;
	
	protected Boolean disabled;
	
	public void setRating(Number rating) {
		log.info("Change rating video " + rating);
		if (rating != null) {
			this.rating = rating.doubleValue();
		}
	}	
}
