package com.gamergeo.project.videomanager.view.parse;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.model.parse.Folder;
import com.gamergeo.project.videomanager.viewmodel.parse.ParseTabViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

@Component
@Scope("prototype")
public class ParseTabView implements FxmlView<ParseTabViewModel>, Initializable {
	
	@FXML
	Label message;
	
	@FXML
	TreeView<String> tree;

    @InjectViewModel
    private ParseTabViewModel viewModel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		message.textProperty().bind(viewModel.messageProperty());
		
		FXUtils.addSimpleChangeListener(viewModel.errorProperty(), this::isError);
		FXUtils.addSimpleChangeListener(viewModel.rootFolderProperty(), this::createFolderTree);
	}
	
	private void isError(boolean error) {
		// Todo
	}
	
	private void createFolderTree(Folder root) {
		TreeItem<String> rootItem = new TreeItem<String>(root.getName());
		buildTreeViewFromFolder(root, rootItem);
		
		rootItem.setExpanded(true);
		tree.setRoot(rootItem);
	}
	
    // Create folder and child, recursvely
    private void buildTreeViewFromFolder(Folder folder, TreeItem<String> parentItem) {
        for (Folder childFolder : folder.getChildFolder()) {
            TreeItem<String> childItem = new TreeItem<>(childFolder.getName());
            parentItem.getChildren().add(childItem);
            buildTreeViewFromFolder(childFolder, childItem); // Recusif call to get child
        }
    }
}
