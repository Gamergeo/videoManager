	package com.gamergeo.project.videomanager.gui.viewmodel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.gamergeo.lib.gamlib.javafx.viewmodel.AbstractViewModel;
import com.gamergeo.lib.gamlib.javafx.viewmodel.ViewModel;
import com.gamergeo.project.videomanager.gui.cell.RatingCellFactory;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.model.Video;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;

@ViewModel
public class TableViewModel extends AbstractViewModel {
	
	@Autowired
	@Lazy
	private SceneViewModel scene;
	
	@FXML
	TitledPane root;
	
	@FXML
    private TableView<Video> table;
	
    @FXML
    private TableColumn<Video, String> titleColumn;
    
    @FXML
    private TableColumn<Video, List<Tag>> tagsColumn;
    
    @FXML
    private TableColumn<Video, Double> ratingColumn;
    
    @FXML
    public void initialize() {
		titleColumn.setCellValueFactory(new PropertyValueFactory<Video, String>("title"));
	  	ratingColumn.setCellValueFactory(new PropertyValueFactory<Video, Double>("rating"));
	  	ratingColumn.setCellFactory(new RatingCellFactory());
		
//      	tagsColumn.setCellValueFactory(cellData -> cellData.getValue().tagsProperty());
//      	tagsColumn.setCellFactory(new TagListCellFactory());

//      	titleColumn.setCellValueFactory(new PropertyValueFactory<VideoViewModelOld, String>("title"));
//      	tagsColumn.setCellValueFactory(cellData -> cellData.getValue().tagsProperty());
//      	tagsColumn.setCellFactory(new TagListCellFactory());
//      	ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
//      	ratingColumn.setCellFactory(new RatingCellFactory());
      	
      	table.getSortOrder().add(titleColumn);
      	titleColumn.setSortType(TableColumn.SortType.ASCENDING);
      	table.sort();
    }
    
    public void setData(List<Video> videos) {
    	table.getItems().setAll(videos);
    }
    
    public void refresh() {
    	table.refresh();
    }
    
    @FXML
    private void select() {
    	scene.select(table.getSelectionModel().getSelectedItem());
    }
}
