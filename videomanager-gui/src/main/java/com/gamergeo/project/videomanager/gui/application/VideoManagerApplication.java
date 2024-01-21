package com.gamergeo.project.videomanager.gui.application;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VideoManagerApplication extends AbstractApplication {
	
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox page = (VBox) load("videoScene");
//        page.getChildren().add(loader.load());
        Scene scene = new Scene(page);

        primaryStage.setTitle("Video manager");
//        scene.getStylesheets().add("/css/button.css");
        primaryStage.setScene(scene);		
//        primaryStage.sizeToScene();
        primaryStage.show();
    }

//    public static void main(String[] args) {
//        launch(args);
//    }
}
