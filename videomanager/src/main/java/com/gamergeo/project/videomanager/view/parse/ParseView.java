package com.gamergeo.project.videomanager.view.parse;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.viewmodel.parse.ParseTabViewModel;
import com.gamergeo.project.videomanager.viewmodel.parse.ParseViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

@Component
public class ParseView implements FxmlView<ParseViewModel>, Initializable {
	
	@FXML
	TabPane tabs;

    @InjectViewModel
    private ParseViewModel viewModel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FXUtils.addSimpleChangeListener(viewModel.filesProperty(), this::initTable);
	}
	
	private void initTable(ObservableList<File> files) {
		// We create one tab per file
		tabs.getTabs().clear();
		
		for (File file : files) {
			Tab fileTab = new Tab(file.getName());
			
	    	ViewTuple<ParseTabView, ParseTabViewModel> model = FXUtils.load(ParseTabView.class);
	    	model.getViewModel().setRawFile(file);
	    	fileTab.setContent(model.getView());
	    	fileTab.setClosable(false);
	    	tabs.getTabs().add(fileTab);
		}
		
	}
}
