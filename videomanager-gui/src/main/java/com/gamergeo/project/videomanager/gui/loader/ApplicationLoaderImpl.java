package com.gamergeo.project.videomanager.gui.loader;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplicationLoaderImpl implements ApplicationLoader {
	
	@Autowired
	ApplicationContext applicationContext;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Override
	public Node load(String name) {
		log.info("Load FXML for: " + name);
        FXMLLoader loader = getLoader(name);
		return load(loader);
	}
	
	@Override
	public Node load(FXMLLoader loader) {
        Node node;
		try {
			node = loader.load();
		} catch(IOException e) {
			log.error("Can't parse FXML File:" + loader.getLocation().toString());
			throw new RuntimeException(e);
		}
		
		return node;
	}
	
	@Override
	public FXMLLoader getLoader(String name) {
		log.info("Require FXML Loader for: " + name);
		FXMLLoader loader;
		
		try {
			loader =  new FXMLLoader(resourceLoader.getResource("classpath:fxml/" + name + ".fxml").getURL());
		} catch(IOException e) {
			log.error("FXML file not found:" + name);
			throw new RuntimeException(e);
		}
		
        loader.setControllerFactory(applicationContext::getBean);
        return loader;
	}

}
