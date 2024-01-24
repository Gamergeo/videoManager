package com.gamergeo.project.videomanager.gui;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javafx.application.Application;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.gamergeo.project.videomanager")
@EntityScan("com.gamergeo.project.videomanager.model")
@EnableJpaRepositories("com.gamergeo.project.videomanager.persistence")
@SpringBootApplication
@Slf4j
public class VideoManagerApplication {

  public static void main(String[] args) {
  	log.info("Application VideoManager is starting...");
    Application.launch(VideoManagerGuiApplication.class, args);
    log.info("Application VideoManager started");
  }

}