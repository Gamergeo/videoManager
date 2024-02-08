package com.gamergeo.project.videomanager.gui.view.tag;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;
import com.gamergeo.lib.gamlib.javafx.viewmodel.ViewModel;
import com.gamergeo.project.videomanager.gui.VideoManagerParameters;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

public abstract class AbstractTagParentView<VM extends ViewModel> extends AbstractFXMLView<VM> {

	protected final VideoManagerParameters applicationParameters;
	
	public AbstractTagParentView(ApplicationContext applicationContext, VM viewModel, VideoManagerParameters applicationParameters) {
		super(applicationContext, viewModel);
		this.applicationParameters = applicationParameters;
	}
	
	protected void initialize() {
		addSimpleListChangeListener(getTags(), this::refreshTags);
	}
	
	private void refreshTags(List<Tag> tags) {
		getTagPane().getChildren().clear();
		
		tags.forEach((tag) -> {
			getTagPane().getChildren().add(getRoot(TagView.class, applicationParameters.tag(tag)));
		});
	}

	protected abstract Pane getTagPane();
	protected abstract ObservableList<Tag> getTags();
}
