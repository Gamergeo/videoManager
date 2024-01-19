package com.gamergeo.project.videomanager.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.gui.viewmodel.VideoModel;
import com.gamergeo.project.videomanager.service.VideoService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

@Component
public class VideoTableController {
	
	@Autowired
	private VideoService videoService;

	private ObservableList<VideoModel> videoList = FXCollections.observableArrayList();

	@FXML
    private TableView<VideoModel> videoTable;
	
    @FXML
    private TableColumn<VideoModel, String> titleColumn;
    
    @FXML
    private TableColumn<VideoModel, Boolean> bestofColumn;
    
    @FXML
    private TableColumn<VideoModel, Boolean> disabledColum;
    
    @FXML
    private void initialize() {
    	videoList.setAll(VideoModel.fromModels(videoService.getVideoList()));
    	videoTable.setItems(videoList);
    	titleColumn.setCellValueFactory(new PropertyValueFactory<VideoModel, String>("title"));
    	bestofColumn.setCellValueFactory(new PropertyValueFactory<VideoModel, Boolean>("bestof"));
    	disabledColum.setCellValueFactory(new PropertyValueFactory<VideoModel, Boolean>("disabled"));
    }
    
    public void getVideo() {
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
//    public void selectPerson(MouseEvent event) throws IOException {
//        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
//        	personScene.setSelectedPerson(tableView.getSelectionModel().getSelectedItem());
//        }
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
