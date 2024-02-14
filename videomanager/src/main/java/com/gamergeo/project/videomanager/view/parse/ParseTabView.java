package com.gamergeo.project.videomanager.view.parse;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.service.parse.Folder;
import com.gamergeo.project.videomanager.viewmodel.parse.ParseTabViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

@Component
@Scope("prototype")
public class ParseTabView implements FxmlView<ParseTabViewModel>, Initializable {
	
	@FXML
	Label message;
	
	@FXML
	TreeView<Folder> tree;

	@FXML
	Button parse;

    @InjectViewModel
    private ParseTabViewModel viewModel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		message.textProperty().bind(viewModel.messageProperty());
		
		FXUtils.addSimpleChangeListener(viewModel.rootFolderProperty(), this::createFolderTree);
		
		parse.setOnAction(this::onParse);
	}
	
	private void createFolderTree(Folder root) {
		TreeItem<Folder> rootItem = new TreeItem<Folder>(root);
		buildTreeViewFromFolder(root, rootItem);
		
		rootItem.setExpanded(true);
		tree.setRoot(rootItem);
		
		FXUtils.addEmptyChangeListener(tree.getSelectionModel().selectedItemProperty(), this::select);
	}
	
    // Create folder and child, recursvely
    private void buildTreeViewFromFolder(Folder folder, TreeItem<Folder> parentItem) {
        for (Folder childFolder : folder.getChildFolder()) {
            TreeItem<Folder> childItem = new TreeItem<>(childFolder);
            parentItem.getChildren().add(childItem);
            buildTreeViewFromFolder(childFolder, childItem); // Recusif call to get child
        }
    }
    
    private void select() {
		viewModel.setSelectedFolder(tree.getSelectionModel().getSelectedItem().getValue());
    }
    
    private void onParse(ActionEvent e) {
    	viewModel.setParse(true);
    }
}
