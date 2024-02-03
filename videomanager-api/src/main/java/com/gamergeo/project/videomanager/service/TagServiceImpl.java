	package com.gamergeo.project.videomanager.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.persistence.TagDao;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TagServiceImpl implements TagService { 
	
	@Autowired
	TagDao dao;

	@Override
	@Transactional
	public List<Tag> findAll() {
		log.info("Load all tag list");
		List<Tag> tags = dao.findAll();
		log.info("Videos tag list loaded");
		return tags;
	}

	@Override
	@Transactional
	public Tag findById(Long id) {
		return dao.findById(id).orElseThrow(() -> {
			String errorMessage = "Error: Video tag not found (id=" + id+")";
			log.error(errorMessage);
			throw new NoSuchElementException(errorMessage);
		});
	}
	
	@Override
	@Transactional
	public List<Long> findAllIds() {
		return findAll().stream()
                .map(Tag::getId)
                .collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<Tag> findAllById(List<Long> ids) {
		return (List<Tag>) dao.findAllById(ids);
	}

}