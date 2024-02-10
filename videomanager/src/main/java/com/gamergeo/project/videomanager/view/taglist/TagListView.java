package com.gamergeo.project.videomanager.view.taglist;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.view.tag.TagParentView;
import com.gamergeo.project.videomanager.viewmodel.tag.TagParentViewModel;
import com.gamergeo.project.videomanager.viewmodel.taglist.TagListViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

@Component
public class TagListView implements FxmlView<TagListViewModel>, Initializable, TagParentView {
	
	@FXML
	private TextField search;
	
	@FXML
	private TilePane list;

    @InjectViewModel
    private TagListViewModel viewModel;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		renderTags(true);
		list.setOnDragDetected(this::onDragDetected);
	}
	
	private void onDragDetected(MouseEvent event) {
		list.startFullDrag();
		viewModel.setDragDetected(true);
		event.consume();
	}

	@Override
	public TagParentViewModel getViewModel() {
		return viewModel;
	}

	@Override
	public Pane getTagPane() {
		return list;
	}
}