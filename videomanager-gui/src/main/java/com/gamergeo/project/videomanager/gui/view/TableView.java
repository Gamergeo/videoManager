package com.gamergeo.project.videomanager.gui.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;
import com.gamergeo.project.videomanager.gui.cell.RatingCellFactory;
import com.gamergeo.project.videomanager.gui.viewmodel.ScreenViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.SearchViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.TableViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;

@FXMLView
public class TableView extends AbstractFXMLView<TableViewModel> {
	
	@FXML
	private TitledPane root;
	
	@FXML
	public javafx.scene.control.TableView<VideoViewModel> table;
	
    @FXML
    private TableColumn<VideoViewModel, String> titleColumn;
    
    @FXML
    private TableColumn<VideoViewModel, List<Tag>> tagsColumn;
    
    @FXML
    private TableColumn<VideoViewModel, Double> ratingColumn;
    
    @Autowired
    private SearchViewModel search;
	
	@Autowired
	private ScreenViewModel screen;
    
    @FXML
    public void initialize() {
		titleColumn.setCellValueFactory(new PropertyValueFactory<VideoViewModel, String>("title"));
	  	ratingColumn.setCellValueFactory(new PropertyValueFactory<VideoViewModel, Double>("rating"));
	  	ratingColumn.setCellFactory(new RatingCellFactory());
		
//      	tagsColumn.setCellValueFactory(cellData -> cellData.getValue().tagsProperty());
//      	tagsColumn.setCellFactory(new TagListCellFactory());

//      	titleColumn.setCellValueFactory(new PropertyValueFactory<VideoViewModelOld, String>("title"));
//      	tagsColumn.setCellValueFactory(cellData -> cellData.getValue().tagsProperty());
//      	tagsColumn.setCellFactory(new TagListCellFactory());
	  	
      	table.getSortOrder().add(titleColumn);
      	titleColumn.setSortType(TableColumn.SortType.ASCENDING);
      	table.sort();
      	
      	viewModel.getVideos().addListener((ListChangeListener.Change<? extends VideoViewModel> change) -> table.getItems().setAll(change.getList()));
		
		// On search change, filter table
      	search.titleProperty().addListener((observable, oldValue, newValue) -> filterTable());
      	search.ratingProperty().addListener((observable, oldValue, newValue) -> filterTable());
      	filterTable();
      	
      	// Bind selected table item and selected item
      	table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> screen.setSelectedVideo(newValue));
    }
	
	private void filterTable() {
		viewModel.filter(search.getTitle(), search.getRating());
	}
}
