	package com.gamergeo.project.videomanager.gui.view;

import java.util.List;

import com.gamergeo.lib.gamlib.javafx.view.AbstractView;
import com.gamergeo.lib.gamlib.javafx.view.View;
import com.gamergeo.project.videomanager.gui.cell.RatingCellFactory;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;

@View
public class TableView extends AbstractView {
	
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
      	table.sort();
    }
    
    public javafx.scene.control.TableView<VideoViewModel> table() {
    	return table;
    }
    
    /**
     * Refresh table
     * We do not utilize properties in our models, therefore table item values cannot be updated without refreshing the table
     */
//    public void refresh() {
//    	table.refresh();
//    }
}
