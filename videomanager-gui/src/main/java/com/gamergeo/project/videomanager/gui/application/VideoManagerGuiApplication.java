package com.gamergeo.project.videomanager.gui.application;

import java.io.IOException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VideoManagerGuiApplication extends Application {

    private static ConfigurableApplicationContext applicationContext;
    
    @Override
    public void init() {
    	log.info("Application VideoManager JavaFX is starting...");
        applicationContext = new SpringApplicationBuilder(VideoManagerApplication.class).run();
        log.info("Application VideoManager JavaFX started");
    }
    
	public static Node load(String name) throws IOException {
		log.info("Load FXML for: " + name);
        FXMLLoader loader = getLoader(name);
        loader.setControllerFactory(applicationContext::getBean);
		return loader.load();
	}
	
	public static FXMLLoader getLoader(String name) {
		log.info("Require FXML Loader for: " + name);
		FXMLLoader loader =  new FXMLLoader(VideoManagerGuiApplication.class.getResource("/fxml/" + name + ".fxml"));
        loader.setControllerFactory(applicationContext::getBean);
        return loader;
	}
	
    @Override
    public void stop() {
    	log.info("Application VideoManager is closing...");
    	applicationContext.close();    
        log.info("Application VideoManager closed");
    }
	
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane page = (GridPane) load("videoScene");
//        page.getChildren().add(loader.load());
        Scene scene = new Scene(page);
        scene.getStylesheets().add("/css/videomanager.css");

        primaryStage.setY(50);
        primaryStage.setX(Screen.getPrimary().getBounds().getWidth()/2-300);
        primaryStage.setTitle("Video manager");
        primaryStage.setScene(scene);		
//        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
