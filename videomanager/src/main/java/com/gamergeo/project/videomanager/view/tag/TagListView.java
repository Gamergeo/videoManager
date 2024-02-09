package com.gamergeo.project.videomanager.view.tag;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.view.video.SearchView;
import com.gamergeo.project.videomanager.viewmodel.tag.TagListViewModel;
import com.gamergeo.project.videomanager.viewmodel.tag.TagViewModel;
import com.gamergeo.project.videomanager.viewmodel.video.SearchViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import de.saxsys.mvvmfx.ViewTuple;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;

@Component
public class TagListView implements FxmlView<TagListViewModel>, Initializable {
	
	@FXML
	private TextField search;
	
	@FXML
	private TilePane list;

    @InjectViewModel
    private TagListViewModel viewModel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		renderTags(viewModel.getDisplayedTags());
		FXUtils.addSimpleListChangeListener(viewModel.getDisplayedTags(), this::renderTags);
	}
	
	private void renderTags(ObservableList<Tag> tags) {
		tags.forEach(this::renderTag);
	}
	
	private void renderTag(Tag tag) {
		ViewTuple<TagView, TagViewModel> tuple = FXUtils.load(TagView.class);
		viewModel.addTag(tuple.getViewModel(), tag);
		list.getChildren().add(tuple.getView());
	}

//	@Override
//	protected ObservableList<Tag> getTags() {
//		return viewModel.getDisplayedTags();
//	}

//	private void onDragDetected(MouseEvent event) {
//		list.startFullDrag();
//		viewModel.onDragDetected();
//	}
}