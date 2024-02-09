	package com.gamergeo.project.videomanager.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.gamergeo.project.videomanager.model.UrlPattern;
import com.gamergeo.project.videomanager.repository.UrlPatternRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UrlPatternService extends ApplicationCrudService<UrlPattern>{ 
	
	private final UrlPatternRepository repository;
	
	@Override
	public UrlPatternRepository getRepository() {
		return repository;
	}

	/**
	 * @return a properly formatted link to search on google
	 */
	@Transactional
	public String getGoogleUrl(String title) {
	    String query = StreamSupport.stream(super.findAll().spliterator(), false)
	    					   .map(UrlPattern::getText)
	                           .reduce(title, (currentUrl, pattern) -> currentUrl.replace(pattern, ""));
	    
	    String encodedQuery;
	    
		try {
			encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			log.error("Can't encode url: " + query);
			 return "https://www.google.com/search?q=\"" + query + "\"";
		}

	    return "https://www.google.com/search?q=\"" + encodedQuery + "\"";
	}
}