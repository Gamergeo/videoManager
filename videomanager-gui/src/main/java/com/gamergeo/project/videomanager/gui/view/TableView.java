package com.gamergeo.project.videomanager.gui.view;

import java.util.List;

import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;
import com.gamergeo.project.videomanager.gui.cell.RatingCellFactory;
import com.gamergeo.project.videomanager.gui.viewmodel.model.VideoViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.view.TableViewModel;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.collections.transformation.SortedList;
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
      	
      	// Bind items and sort
      	SortedList<VideoViewModel> sortedItems = new SortedList<VideoViewModel>(viewModel.getFilteredVideos());
      	sortedItems.comparatorProperty().bind(table.comparatorProperty());
      	table.setItems(sortedItems);
      	
      	// Bind video count
      	root.textProperty().bind(viewModel.headerMessageProperty());
      	
      	// Bind selected item
      	table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> viewModel.setSelectedVideo(newValue));
      	viewModel.selectedVideoProperty().addListener((observable, oldValue, newValue) -> {
      		if (table.getSelectionModel().getSelectedItem() != newValue) {
          		table.getSelectionModel().select(newValue);
          		table.scrollTo(newValue);
      		}
      	});
    }
}
