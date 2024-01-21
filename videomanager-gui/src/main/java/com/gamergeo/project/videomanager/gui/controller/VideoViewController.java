package com.gamergeo.project.videomanager.gui.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;

import com.gamergeo.project.videomanager.gui.component.VideoTagComponent;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTagViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

@Controller
public class VideoViewController {
	
	@FXML
	private Label videoTitleLabel;
	
	@FXML
	private TextField videoUrlField;
	
	@FXML
	private FlowPane videoTagsPane;
	
	public void setVideo(VideoViewModel videoView) throws IOException {
		videoTitleLabel.textProperty().bind(videoView.titleProperty());
		videoUrlField.textProperty().bind(videoView.urlProperty());
		
		videoTagsPane.getChildren().clear();
		
		for (VideoTagViewModel videoTag : videoView.getVideoTagList()) {
			VideoTagComponent videoTagComponent = new VideoTagComponent();
			videoTagComponent.setVideoTag(videoTag);
			videoTagsPane.getChildren().add(videoTagComponent);
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
	}
    
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
