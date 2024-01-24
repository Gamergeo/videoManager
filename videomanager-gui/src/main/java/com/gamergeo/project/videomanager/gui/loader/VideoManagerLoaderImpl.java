package com.gamergeo.project.videomanager.gui.loader;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.gamergeo.lib.gamlib.javafx.loader.ApplicationLoaderImpl;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VideoManagerLoaderImpl extends ApplicationLoaderImpl implements VideoManagerLoader {
	
	@Override
	@SuppressWarnings({ "rawtypes" })
	public FXMLView createView(Class viewClass) {
		try {
			return super.createView(viewClass);
		} catch (IOException e) {

			log.error("Can't find file for:" + viewClass);
			throw new RuntimeException(e);
		}
	}

}
