package com.gamergeo.project.videomanager.gui.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.gui.mapper.VideoMapper;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoTableModel;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;
import com.gamergeo.project.videomanager.service.VideoService;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class VideoTableController {
	
	@Autowired
	private VideoService videoService;
	
	@Autowired
	private VideoViewController videoViewController;
	
	@Autowired
	private VideoSceneController videoSceneController;
	
	@Autowired
	private VideoMapper videoMapper;
	
	private VideoTableModel videoTableModel = new VideoTableModel();

	@FXML
    private TableView<VideoViewModel> videoTable;
	
    @FXML
    private TableColumn<VideoViewModel, String> titleColumn;
    
    @FXML
    private TableColumn<VideoViewModel, String> tagsColumn;
    
//    @FXML
//    private TableColumn<VideoModel, Boolean> disabledColum;
    
    @FXML
    private void initialize() {
    	List<VideoViewModel> videoList = videoMapper.getViewModels(videoService.getVideoList());
    	videoTableModel.getVideoList().setAll(videoList);
    	videoTable.setItems(videoTableModel.getVideoList());
    	titleColumn.setCellValueFactory(new PropertyValueFactory<VideoViewModel, String>("title"));
    	tagsColumn.setCellValueFactory(new PropertyValueFactory<VideoViewModel, String>("videoTags"));
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
    /**
     * On click, change the video panel to selected video
     */
    public void selectVideo(MouseEvent event) throws IOException {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
        	VideoViewModel video = videoTable.getSelectionModel().getSelectedItem();
        	log.info("Video selected: "+ video.getId());
        	videoViewController.setVideo(videoTable.getSelectionModel().getSelectedItem());
        	videoSceneController.openVideoView();
        }
    }
}
