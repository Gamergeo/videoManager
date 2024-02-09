//package com.gamergeo.project.videomanager.gui.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.gamergeo.lib.gamlib.javafx.controller.FXMLController;
//import com.gamergeo.project.videomanager.gui.cell.RatingCellFactory;
//import com.gamergeo.project.videomanager.gui.cell.TagListCellFactory;
//import com.gamergeo.project.videomanager.gui.view.VideoSceneView;
//import com.gamergeo.project.videomanager.gui.viewmodel.old.TagViewModelOld;
//import com.gamergeo.project.videomanager.gui.viewmodel.old.VideoViewModelOld;
//
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TitledPane;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.input.MouseEvent;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@FXMLController
//public class VideoTableController {
//
//	@Autowired
//	private VideoSceneView videoSceneView;
//	
//	@FXML
//	TitledPane tableTitledPane;
//
//	@FXML
//    private TableView<VideoViewModelOld> videoTable;
//	
//    @FXML
//    private TableColumn<VideoViewModelOld, String> titleColumn;
//    
//    @FXML
//    private TableColumn<VideoViewModelOld, ObservableList<TagViewModelOld>> tagsColumn;
//    
//    @FXML
//    private TableColumn<VideoViewModelOld, Number> ratingColumn;
//    
//    @FXML
//    private void initialize() {
//    	titleColumn.setCellValueFactory(new PropertyValueFactory<VideoViewModelOld, String>("title"));
//    	tagsColumn.setCellValueFactory(cellData -> cellData.getValue().tagsProperty());
//    	tagsColumn.setCellFactory(new TagListCellFactory());
//    	ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
//    	ratingColumn.setCellFactory(new RatingCellFactory());
//    	videoTable.getSortOrder().add(titleColumn);
//    	titleColumn.setSortType(TableColumn.SortType.ASCENDING);
//    	videoTable.sort();
//    }
//    
//    public void setVideoList(List<VideoViewModelOld> videoList) {
//    	videoTable.getItems().setAll(videoList);
//    	videoTable.sort();
//    }
//    
//    /**
//     * On click, change the video panel to selected video
//     */
//    public void selectVideo(MouseEvent event) {
//        if (event.isPrimaryButtonDown() && event.getClickCount() == 1) {
//        	VideoViewModelOld video = videoTable.getSelectionModel().getSelectedItem();
//        	log.info("Video selected: "+ video.getId());
//        	videoSceneView.getController().refreshVideoView(video);
//        }
//    }
//	
//	public void openView() {
//		tableTitledPane.setExpanded(true);
//	}
//	
//	public void disable(VideoViewModelOld video) {
//		videoTable.getItems().remove(video);
//	}
//}