package com.gamergeo.project.videomanager.gui.application;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javafx.application.Application;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.gamergeo.project.videomanager")
@EntityScan("com.gamergeo.project.videomanager.model")
@EnableJpaRepositories("com.gamergeo.project.videomanager.persistence") 
public class VideoManagerApplication {

  public static void main(String[] args) {
//    SpringApplication.run(VideoManagerApplication.class, args);>
    Application.launch(VideoManagerGuiApplication.class, args);
  }

}