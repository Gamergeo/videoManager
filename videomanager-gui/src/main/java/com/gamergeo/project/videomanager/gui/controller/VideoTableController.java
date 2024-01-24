package com.gamergeo.project.videomanager.gui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.controller.SceneChildController;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;

@SceneChildController
@Slf4j
public class VideoTableController {
	
	@Autowired
	private VideoSceneController videoSceneController;

	@FXML
    private TableView<VideoViewModel> videoTable;
	
    @FXML
    private TableColumn<VideoViewModel, String> titleColumn;
    
    @FXML
    private TableColumn<VideoViewModel, String> tagsColumn;
    
    @FXML
    private void initialize() {
    	titleColumn.setCellValueFactory(new PropertyValueFactory<VideoViewModel, String>("title"));
    	tagsColumn.setCellValueFactory(new PropertyValueFactory<VideoViewModel, String>("videoTags"));
    }
    
    public void setVideoList(List<VideoViewModel> videoList) {
    	videoTable.getItems().setAll(videoList);
    }
    
    /**
     * On click, change the video panel to selected video
     */
    public void selectVideo(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
        	VideoViewModel video = videoTable.getSelectionModel().getSelectedItem();
        	log.info("Video selected: "+ video.getId());
        	videoSceneController.refreshVideoView(video);
        }
    }
    
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

}
