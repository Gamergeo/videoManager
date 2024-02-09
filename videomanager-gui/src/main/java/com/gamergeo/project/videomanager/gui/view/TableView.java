package com.gamergeo.project.videomanager.gui.view;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXMLView;
import com.gamergeo.project.videomanager.gui.viewmodel.TableRowViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.TableViewModel;

import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;

@Component
public class TableView extends FXMLView<TableViewModel> {
	
	@FXML
	private TitledPane root;
	
	@FXML
	public javafx.scene.control.TableView<TableRowViewModel> table;
	
    @FXML
    private TableColumn<TableRowViewModel, String> titleColumn;
    
    @FXML
    private TableColumn<TableRowViewModel, String> tagsColumn;
    
    @FXML
    private TableColumn<TableRowViewModel, String> ratingColumn;
    
    @FXML
    public void initialize() {
		titleColumn.setCellValueFactory(new PropertyValueFactory<TableRowViewModel, String>("title"));
	  	ratingColumn.setCellValueFactory(new PropertyValueFactory<TableRowViewModel, String>("rating"));
	  	tagsColumn.setCellValueFactory(new PropertyValueFactory<TableRowViewModel, String>("tags"));
		
      	table.getSortOrder().add(titleColumn);
      	titleColumn.setSortType(TableColumn.SortType.ASCENDING);
      	
      	// Bind items and sort
      	SortedList<TableRowViewModel> sortedItems = new SortedList<TableRowViewModel>(viewModel.getRows());
      	sortedItems.comparatorProperty().bind(table.comparatorProperty());
      	table.setItems(sortedItems);
      	
      	// Bind video count
      	root.textProperty().bind(viewModel.headerMessageProperty());
      	
      	// On table selection, change viewModel
      	addSimpleChangeListener(table.getSelectionModel().selectedItemProperty(), viewModel::setSelectedRow);
      	// On view model change, change table selected and scroll
      	addSimpleChangeListener(viewModel.selectedRowProperty(), this::select);
    }
    
    private void select(TableRowViewModel row) {
  		if (table.getSelectionModel().getSelectedItem() != row) {
      		table.getSelectionModel().select(row);
      		table.scrollTo(row);
  		}
    }
}
