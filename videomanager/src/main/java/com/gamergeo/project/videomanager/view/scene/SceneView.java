package com.gamergeo.project.videomanager.view.scene;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.VideoManagerApplication;
import com.gamergeo.project.videomanager.view.parse.ParseView;
import com.gamergeo.project.videomanager.view.screen.ScreenView;
import com.gamergeo.project.videomanager.view.search.SearchView;
import com.gamergeo.project.videomanager.view.table.TableView;
import com.gamergeo.project.videomanager.view.taglist.TagListView;
import com.gamergeo.project.videomanager.viewmodel.parse.ParseViewModel;
import com.gamergeo.project.videomanager.viewmodel.scene.SceneViewModel;
import com.gamergeo.project.videomanager.viewmodel.screen.ScreenViewModel;
import com.gamergeo.project.videomanager.viewmodel.search.SearchViewModel;
import com.gamergeo.project.videomanager.viewmodel.table.TableViewModel;
import com.gamergeo.project.videomanager.viewmodel.taglist.TagListViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    	// Tag drag and drop
    	root.setOnMouseDragReleased((event) -> viewModel.setDragReleased(true));
    	
    	// File drag and drop
    	root.setOnDragOver(this::onDragOverFile);
    	root.setOnDragDropped(this::onDragDroppedFile);
	}
    
    private void onDragOverFile(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        event.consume();
    }    
    
    private void onDragDroppedFile(DragEvent event) {
    	// Open a modal window
        Dragboard dragboard = event.getDragboard();

        if (dragboard.hasFiles()) {
	        Stage modalStage = new Stage();
	        modalStage.initModality(Modality.APPLICATION_MODAL);
	        modalStage.initOwner(root.getScene().getWindow());
	    	
	    	ViewTuple<ParseView, ParseViewModel> model = FXUtils.load(ParseView.class);
	    	model.getViewModel().setFiles(dragboard.getFiles());
	        Scene scene = new Scene(model.getView());
	        scene.getStylesheets().add(FXUtils.classPackageToResourcePath(VideoManagerApplication.class));
	    	modalStage.setScene(scene);
	    	
            event.setDropCompleted(true);
            event.consume();
            
            // wait for drag and drop to be completed
            Platform.runLater(() -> {
                modalStage.showAndWait();
            });
            
        } else {
	        /* let the source know whether the string was successfully 
	         * transferred and used */
	        event.setDropCompleted(false);
	        event.consume();
        }
    }
}
