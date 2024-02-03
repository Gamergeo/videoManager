package com.gamergeo.project.videomanager.gui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.project.videomanager.gui.cell.RatingCellFactory;
import com.gamergeo.project.videomanager.gui.view.VideoSceneView;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@FXMLController
public class VideoTableController {

	@Autowired
	private VideoSceneView videoSceneView;
	
	@FXML
	TitledPane tableTitledPane;

	@FXML
    private TableView<VideoViewModel> videoTable;
	
    @FXML
    private TableColumn<VideoViewModel, String> titleColumn;
    
    @FXML
    private TableColumn<VideoViewModel, String> tagsColumn;
    
    @FXML
    private TableColumn<VideoViewModel, Number> ratingColumn;
    
    @FXML
    private void initialize() {
    	titleColumn.setCellValueFactory(new PropertyValueFactory<VideoViewModel, String>("title"));
    	tagsColumn.setCellValueFactory(new PropertyValueFactory<VideoViewModel, String>("tags"));
    	ratingColumn.setCellValueFactory(new PropertyValueFactory<VideoViewModel, Number>("rating"));
    	ratingColumn.setCellFactory(new RatingCellFactory());
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
        	videoSceneView.getController().refreshVideoView(video);
        }
    }
	
	public void openView() {
		tableTitledPane.setExpanded(true);
	}
}
