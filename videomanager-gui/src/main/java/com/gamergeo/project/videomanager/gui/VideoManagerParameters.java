package com.gamergeo.project.videomanager.gui;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.javafx.parameter.ApplicationParameters;
import com.gamergeo.lib.gamlib.javafx.parameter.Parameter;
import com.gamergeo.project.videomanager.model.Tag;

/**
 * Implementation of ApplicationViewModelParameters
 * Useful to init viewmodel with parameters
 */
@Component
public class VideoManagerParameters extends ApplicationParameters {
	
	private final String TAG_PAREMETER = "TAG";

	@Override
	public void initParameters() {
		addApplicationParameter(TAG_PAREMETER, Tag.class);
	}
	
	@SuppressWarnings("unchecked")
	private Parameter<Tag> getTagParameter() {
		return (Parameter<Tag>) getApplicationParameter(TAG_PAREMETER);
	}

	public Parameter<Tag> tag(Tag tag) {
		Parameter<Tag> parameter = getTagParameter();
		parameter.set(tag);
		return parameter;
	}
	
	public Tag tag() {
		return getTagParameter().get();
	}
}
