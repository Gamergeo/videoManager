package com.gamergeo.project.videomanager.gui;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.gamergeo.project.videomanager.gui.loader.VideoManagerLoader;
import com.gamergeo.project.videomanager.gui.view.VideoSceneView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.gamergeo.project.videomanager")
@EntityScan("com.gamergeo.project.videomanager.model")
@EnableJpaRepositories("com.gamergeo.project.videomanager.persistence")
@SpringBootApplication
@Slf4j
public class VideoManagerApplication extends Application {

    private static ConfigurableApplicationContext applicationContext;
	
	public static void main(String[] args) {
		launch(args);
	}
    
    @Override
    public void init() {
    	log.info("Application VideoManager JavaFX is starting...");
		SpringApplicationBuilder builder = new SpringApplicationBuilder(VideoManagerApplication.class);
		applicationContext = builder.run(getParameters().getRaw().toArray(new String[0]));
        log.info("Application VideoManager JavaFX started");
    }
    @Override
    public void stop() {
    	log.info("Application VideoManager is closing...");
    	applicationContext.close();    
        log.info("Application VideoManager closed");
    }
	
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane page = (GridPane) applicationContext.getBean(VideoManagerLoader.class).createView(VideoSceneView.class).getRoot();
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
