package com.gamergeo.project.videomanager.gui.view.tag;

import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.gamlib.javafx.view.FXMLView;
import com.gamergeo.project.videomanager.gui.VideoManagerParameters;
import com.gamergeo.project.videomanager.gui.viewmodel.tag.TagListViewModel;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

@FXMLView
public class TagListView extends AbstractTagParentView<TagListViewModel> {
	
	@FXML
	private TextField search;
	
	@FXML
	private TilePane list;
	
	public TagListView(ApplicationContext applicationContext, TagListViewModel viewModel, VideoManagerParameters applicationParameters) {
		super(applicationContext, viewModel, applicationParameters);
	}
	
	@FXML
	protected void initialize() {
		super.initialize();
		
		list.setOnDragDetected(viewModel::onDragDetected);
	}

	@Override
	protected Pane getTagPane() {
		return list;
	}

	@Override
	protected ObservableList<Tag> getTags() {
		return viewModel.getDisplayedTags();
	}
	
	
}
