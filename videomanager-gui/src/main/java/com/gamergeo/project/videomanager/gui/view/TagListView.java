package com.gamergeo.project.videomanager.gui.view;

import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;
import com.gamergeo.project.videomanager.gui.viewmodel.TagListViewModel;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

@FXMLView
public class TagListView extends AbstractFXMLView<TagListViewModel> {
	
	@FXML
	private TextField search;
	
	@FXML
	private TilePane list;
	
	public TagListView(ApplicationContext applicationContext, TagListViewModel viewModel) {
		super(applicationContext, viewModel);
	}
	
	@FXML
	private void initialize() {
		
		viewModel.tags().addListener((ListChangeListener.Change<? extends Tag> change) -> {
			addTags();
		});
	}
	
	private void addTags() {
		list.getChildren().clear();
		
		viewModel.tags().forEach((tag) -> {
			TagView tagView = load(TagView.class);
			tagView.getViewModel().setTag(tag);
			list.getChildren().add(tagView.getRoot());
		});
	}
	
}
