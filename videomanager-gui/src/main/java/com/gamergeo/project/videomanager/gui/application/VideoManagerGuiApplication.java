package com.gamergeo.project.videomanager.gui.application;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class VideoManagerGuiApplication extends AbstractApplication {
	
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

//    public static void main(String[] args) {
//        launch(args);
//    }
}
