package com.gamergeo.project.videomanager.service;

import java.util.List;
import java.util.Optional;

import com.gamergeo.project.videomanager.VideoManagerException;
import com.gamergeo.project.videomanager.model.Model;
import com.gamergeo.project.videomanager.util.ApplicationUtils;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class ApplicationCrudService<T extends Model> implements CrudService<T> {
	
	// Used for log / error message
	protected String getModelName() {
		return this.getClass().getSimpleName().toLowerCase().replace("service", "");
	}
		
	@Transactional
	@Override
	public List<T> findAll() {
		log.info("Load all " + getModelName() + "s");
		List<T> models = ApplicationUtils.toList(getRepository().findAll());
		log.info(getModelName() + "s loaded");
		return models;
	}
		
	@Transactional
	@Override
	public T findById(Long id) {
		log.info("Load " + getModelName() +" (id=" + id + ")");
		
		Optional<T> optional = getRepository().findById(id);
		if (optional.isEmpty()) {
			throw new VideoManagerException( "Error: " + getModelName() + " not found (id=" + id+")");
		}
		
		return optional.get();
	}
		
	@Transactional
	@Override
	public List<T> findAllById(List<Long> ids) {
		return ApplicationUtils.toList(getRepository().findAllById(ids));
	}

}
