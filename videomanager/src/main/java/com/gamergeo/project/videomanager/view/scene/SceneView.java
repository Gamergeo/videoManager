package com.gamergeo.project.videomanager.view.scene;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.view.screen.ScreenView;
import com.gamergeo.project.videomanager.view.search.SearchView;
import com.gamergeo.project.videomanager.view.table.TableView;
import com.gamergeo.project.videomanager.view.taglist.TagListView;
import com.gamergeo.project.videomanager.viewmodel.scene.SceneViewModel;
import com.gamergeo.project.videomanager.viewmodel.screen.ScreenViewModel;
import com.gamergeo.project.videomanager.viewmodel.search.SearchViewModel;
import com.gamergeo.project.videomanager.viewmodel.table.TableViewModel;
import com.gamergeo.project.videomanager.viewmodel.taglist.TagListViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

@Component
public class SceneView implements FxmlView<SceneViewModel>, Initializable {
	
	@FXML 
	private HBox root;
	
	@FXML
	private BorderPane main;

    @InjectViewModel
    private SceneViewModel viewModel;
	
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	ViewTuple<SearchView, SearchViewModel> search = FXUtils.load(SearchView.class);
    	viewModel.initSearch(search.getViewModel());
    	main.setTop(search.getView());
    	
    	ViewTuple<TableView, TableViewModel> table = FXUtils.load(TableView.class);
    	viewModel.initTable(table.getViewModel());
    	main.setBottom(table.getView());
    	
    	ViewTuple<ScreenView, ScreenViewModel> screen = FXUtils.load(ScreenView.class);
    	viewModel.initScreen(screen.getViewModel());
    	main.setCenter(screen.getView());
    	
    	ViewTuple<TagListView, TagListViewModel> tagList = FXUtils.load(TagListView.class);
    	viewModel.initTagList(tagList.getViewModel());
    	root.getChildren().add(tagList.getView());
    	
    	root.cursorProperty().bind(viewModel.cursorProperty());
    	root.setOnMouseDragReleased((event) -> viewModel.setDragReleased(true));
	}
}