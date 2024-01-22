package com.gamergeo.project.videomanager.model;

import java.io.Serializable;

import com.gamergeo.lib.gamlib.mapper.MappedModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = DatabaseName.TAG.TABLE)
@Table(name = DatabaseName.TAG.TABLE)
public class VideoTag implements Serializable, MappedModel {//, HibernateModel {

	private static final long serialVersionUID = 6257375419629830569L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	protected String text;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
