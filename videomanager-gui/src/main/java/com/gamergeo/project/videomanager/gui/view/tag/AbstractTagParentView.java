package com.gamergeo.project.videomanager.gui.view.tag;

import java.util.List;

import com.gamergeo.lib.viewmodelfx.parameter.Parameter;
import com.gamergeo.lib.viewmodelfx.view.FXMLView;
import com.gamergeo.lib.viewmodelfx.viewmodel.ViewModel;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

public abstract class AbstractTagParentView<VM extends ViewModel> extends FXMLView<VM> {

	protected void initialize() {
		addSimpleListChangeListener(getTags(), this::refreshTags);
	}
	
	private void refreshTags(List<Tag> tags) {
		getTagPane().getChildren().clear();
		
		tags.forEach((tag) -> {
			getTagPane().getChildren().add(getRoot(TagView.class, new Parameter<Tag>(tag)));
		});
	}

	protected abstract Pane getTagPane();
	protected abstract ObservableList<Tag> getTags();
}
