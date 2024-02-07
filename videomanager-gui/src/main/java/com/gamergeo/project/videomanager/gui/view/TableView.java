package com.gamergeo.project.videomanager.gui.view;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;
import com.gamergeo.project.videomanager.gui.cell.RatingCellFactory;
import com.gamergeo.project.videomanager.gui.viewmodel.TableRowViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.TableViewModel;
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
	public javafx.scene.control.TableView<TableRowViewModel> table;
	
    @FXML
    private TableColumn<TableRowViewModel, String> titleColumn;
    
    @FXML
    private TableColumn<TableRowViewModel, List<Tag>> tagsColumn;
    
    @FXML
    private TableColumn<TableRowViewModel, Double> ratingColumn;
    
	public TableView(ApplicationContext applicationContext, TableViewModel viewModel) {
		super(applicationContext, viewModel);
	}
    
    @FXML
    public void initialize() {
		titleColumn.setCellValueFactory(new PropertyValueFactory<TableRowViewModel, String>("title"));
	  	ratingColumn.setCellValueFactory(new PropertyValueFactory<TableRowViewModel, Double>("rating"));
	  	ratingColumn.setCellFactory(new RatingCellFactory());
		
//      	tagsColumn.setCellValueFactory(cellData -> cellData.getValue().tagsProperty());
//      	tagsColumn.setCellFactory(new TagListCellFactory());

//      	titleColumn.setCellValueFactory(new PropertyValueFactory<VideoViewModelOld, String>("title"));
//      	tagsColumn.setCellValueFactory(cellData -> cellData.getValue().tagsProperty());
//      	tagsColumn.setCellFactory(new TagListCellFactory());
      	
      	table.getSortOrder().add(titleColumn);
      	titleColumn.setSortType(TableColumn.SortType.ASCENDING);
      	
      	// Bind items and sort
      	SortedList<TableRowViewModel> sortedItems = new SortedList<TableRowViewModel>(viewModel.getRows());
      	sortedItems.comparatorProperty().bind(table.comparatorProperty());
      	table.setItems(sortedItems);
      	
      	// Bind video count
      	root.textProperty().bind(viewModel.headerMessageProperty());
      	
      	// Bind selected item
      	table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> viewModel.setSelectedRow(newValue));
      	viewModel.selectedRowProperty().addListener((observable, oldValue, newValue) -> {
      		if (table.getSelectionModel().getSelectedItem() != newValue) {
          		table.getSelectionModel().select(newValue);
          		table.scrollTo(newValue);
      		}
      	});
    }
}
