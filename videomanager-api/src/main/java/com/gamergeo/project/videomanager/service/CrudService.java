package com.gamergeo.project.videomanager.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import jakarta.transaction.Transactional;

public interface CrudService<T> {
	
	public CrudRepository<T, Long> getRepository();
	
	@Transactional
	default public T save(T model) {
		return getRepository().save(model);
	}
	
	@Transactional
	default public void delete(T model) {
		getRepository().delete(model);
	}
	
	@Transactional
	default public Iterable<T> findAll() {
		return getRepository().findAll();
	}
	
	@Transactional
	default public T findById(Long id) {
		return getRepository().findById(id).orElseThrow();
	}
	
	@Transactional
	default public long count() {
		return getRepository().count();
	}

	@Transactional
	default public Iterable<T> findAllById(List<Long> ids) {
		return getRepository().findAllById(ids);
	}
}
