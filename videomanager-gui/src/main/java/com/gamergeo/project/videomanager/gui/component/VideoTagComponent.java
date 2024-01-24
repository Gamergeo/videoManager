//package com.gamergeo.project.videomanager.gui.component;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import com.gamergeo.project.videomanager.gui.loader.ApplicationLoader;
//import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;
//
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.control.Label;
//import javafx.scene.layout.HBox;
//
//@Component
//@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//public class VideoTagComponent extends HBox {
//	
//	@Autowired
//	ApplicationLoader loader;
//	
//	private VideoTagViewModel videoTag;
//	
//	@FXML
//	private Label videoTagLabel;
//	
//    public VideoTagComponent() {
//    	super();
//        FXMLLoader fxmlLoader = loader.getLoader("videoTagComponent");
//        fxmlLoader.setRoot(this);
//        fxmlLoader.setController(VideoTagComponent.this);
//        loader.load(fxmlLoader);
//        getStyleClass().add("videoTagComponent");
//    }
//    
//    public VideoTagComponent(VideoTagViewModel videoTag) {
//    	this();
//		this.videoTag = videoTag;
//		videoTagLabel.textProperty().bind(this.videoTag.textProperty());
//	}
//    
//}
