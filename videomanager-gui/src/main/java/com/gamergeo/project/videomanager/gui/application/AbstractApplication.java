package com.gamergeo.project.videomanager.gui.application;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 * Abstract Util class for application
 */
public abstract class AbstractApplication extends Application {

    private static ConfigurableApplicationContext applicationContext;
    
    @Override
    public void init() {
    	applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
    }
	
	public static Node load(String name) throws IOException {
        FXMLLoader loader = new FXMLLoader(AbstractApplication.class.getResource("/fxml/" + name + ".fxml"));
        loader.setControllerFactory(applicationContext::getBean);
		return loader.load();
	}
	
    @Override
    public void stop() {
    	applicationContext.close();
    }
}
