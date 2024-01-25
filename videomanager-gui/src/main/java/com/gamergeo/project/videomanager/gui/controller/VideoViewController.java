package com.gamergeo.project.videomanager.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.lib.gamlib.javafx.controller.FXMLSceneChildController;
import com.gamergeo.project.videomanager.gui.view.VideoTagView;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.util.converter.LocalDateStringConverter;
import lombok.extern.slf4j.Slf4j;

@FXMLSceneChildController
@Slf4j
public class VideoViewController implements FXMLController {
	
	@FXML
	private Label videoTitleLabel;
	
	@FXML
	private TextField videoUrlField;
	
	@FXML
	private Label addedDateLabel;
	
	@FXML
	private FlowPane videoTagsPane;
	
	@Autowired
	ApplicationContext applicationContext;
	
	public void setVideo(VideoViewModel videoView) {
		log.info("Change video view infos: " + videoView.getId());
		videoTitleLabel.textProperty().bind(videoView.titleProperty());
		videoUrlField.textProperty().bind(videoView.urlProperty());
		addedDateLabel.textProperty().bindBidirectional(videoView.addedDateProperty(), new LocalDateStringConverter());
		
		videoTagsPane.getChildren().clear();
		
		for (VideoTagViewModel videoTag : videoView.getVideoTagList()) {
			VideoTagView videoTagView = applicationContext.getBean(VideoTagView.class);
			videoTagView.load();
			((VideoTagController) videoTagView.getController()).setTag(videoTag);
			videoTagView.getRoot().getStyleClass().add("videoTagComponent");
			videoTagsPane.getChildren().add(videoTagView.getRoot());
		}
	}
		
//		((GridPane) VideoManagerApplication.load("videoTagView")).set
		
//		videoTagsPane.getChildren().add((GridPane) VideoManagerApplication.load("videoTagView"));
		
//		this.personScene = personScene;
		
        // Bind selected
//        personScene.getSelectedPersonProperty().addListener((observable, oldValue, person)->{
//        	idPerson.textProperty().bind(Bindings.selectInteger(person, "id").asString());
//        	namePerson.textProperty().bind(Bindings.selectString(person, "name"));
//        	employedPerson.textProperty().bind(Bindings.selectBoolean(person, "employed").asString());
//        });
    
//    	personList.add(new PersonView(new Person(1, "jean_mi", true)));
//    	personList.add(new PersonView(new Person(2, "jeannot", false)));
//    	
//    	tableView.setItems(personList);
//    	
//        idColumn.setCellValueFactory(new PropertyValueFactory<PersonView, Integer>("id"));
//        nameColumn.setCellValueFactory(new PropertyValueFactory<PersonView, String>("name"));
//        employedColumn.setCellValueFactory(new PropertyValueFactory<PersonView, Boolean>("isEmployed"));
//    }
//    
//	/**
//	 * Refresh les données de la table en fonction du paramètre
//	 * @param searchText
//	 */
//    public void loadDataTable(String searchText) {
//        Task<ObservableList<PersonView>> task = new Task<ObservableList<PersonView>>() {
//            @Override
//            protected ObservableList<PersonView> call() throws Exception {
//                updateMessage("Loading data");
//                return FXCollections.observableArrayList(personList
//                        .stream()
//                        .filter(value -> value.getName().toString().toLowerCase().contains(searchText))
//                        .collect(Collectors.toList()));
//            }
//        };
//        
//        task.setOnSucceeded(event -> {
//        	tableView.setItems(FXCollections.observableList(task.getValue()));
//        });
//        
//        Thread th = new Thread(task);
//        th.setDaemon(true);
//        th.start();	
//    }
//    
//	public void setPersonScene(PersonSceneView personScene) {
//		this.personScene = personScene;
//        
//        // Bind search
//		personScene.buttonPressedProperty().addListener((observable, oldValue, newValue)->{
//			if (newValue) {
//				loadDataTable(personScene.getSearchText());	
//			}
//        });
//	}
}

