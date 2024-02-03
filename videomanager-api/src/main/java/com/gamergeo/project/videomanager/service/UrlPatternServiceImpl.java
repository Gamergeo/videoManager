	package com.gamergeo.project.videomanager.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamergeo.project.videomanager.model.UrlPattern;
import com.gamergeo.project.videomanager.persistence.UrlPatternDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UrlPatternServiceImpl implements UrlPatternService { 
	
	@Autowired
	UrlPatternDao dao;

	/**
	 * @return a properly formatted link to search on google
	 */
	@Override
	public String getGoogleUrl(String title) {
	    List<UrlPattern> patterns = dao.findAll();
	    String query = patterns.stream()
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