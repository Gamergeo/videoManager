package com.gamergeo.project.videomanager.model;

import java.io.Serializable;

import com.gamergeo.lib.gamlib.mapper.MappedModel;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name = DatabaseName.TAG.TABLE)
@Table(name = DatabaseName.TAG.TABLE)
@Data
public class VideoTag implements Serializable, MappedModel {//, HibernateModel {

	private static final long serialVersionUID = 6257375419629830569L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	
	protected String text;
}
