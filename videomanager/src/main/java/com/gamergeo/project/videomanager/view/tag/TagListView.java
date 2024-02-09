package com.gamergeo.project.videomanager.view.tag;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.helper.TagParentHelper;
import com.gamergeo.project.videomanager.viewmodel.tag.TagListViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;

@Component
public class TagListView implements FxmlView<TagListViewModel>, Initializable {
	
	@FXML
	private TextField search;
	
	@FXML
	private TilePane list;

    @InjectViewModel
    private TagListViewModel viewModel;
    
    private final TagParentHelper tagParentHelper;
    
	public TagListView(TagParentHelper tagParentHelper) {
		this.tagParentHelper = tagParentHelper;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tagParentHelper.initTags(list, viewModel);
		list.setOnDragDetected(this::onDragDetected);
	}
	
	private void onDragDetected(MouseEvent event) {
		list.startFullDrag();
		viewModel.setDragDetected(true);
		event.consume();
	}
}