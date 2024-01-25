package com.gamergeo.project.videomanager.gui.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
import com.gamergeo.project.videomanager.gui.viewmodel.ApplicationViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@FXMLController
public class VideoTableController {
	
	@Autowired
	ApplicationViewModel applicationViewModel;
	
	@Autowired
	VideoViewController videoViewController;

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
    	videoTable.setItems(applicationViewModel.getVideoList());
    }
    
    /**
     * On click, change the video panel to selected video
     */
    public void selectVideo(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
        	VideoViewModel video = videoTable.getSelectionModel().getSelectedItem();
        	log.info("Video selected: "+ video.getId());
        	applicationViewModel.setSelectedVideo(video);
        }
    }
}
