package com.gamergeo.project.videomanager.gui.viewmodel.tag;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.javafx.viewmodel.AbstractChildViewModel;
import com.gamergeo.project.videomanager.gui.VideoManagerParameters;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Component
@Scope("prototype")
public class TagViewModel extends AbstractChildViewModel<TagListViewModel>{

	protected final VideoManagerParameters applicationParameters;
	
	private Tag tag;
	
	private final StringProperty label = new SimpleStringProperty();
	private final BooleanProperty selected = new SimpleBooleanProperty();
	
	public TagViewModel(VideoManagerParameters applicationParameters) {
		this.applicationParameters = applicationParameters;
	}
	
	@Override
	public void init() {
		super.init();
		this.tag = applicationParameters.tag();
		label.bindBidirectional(this.tag.labelProperty());
	}
	
	public void setTag(Tag tag) {
		this.tag = tag;
	}
	
	public void onClick() {
		selected.set(parent.onTagClick(tag));
	}

	public final StringProperty labelProperty() {
		return this.label;
	}

	public final String getLabel() {
		return this.labelProperty().get();
	}
	
	public final void setLabel(final String label) {
		this.labelProperty().set(label);
	}

	public final BooleanProperty selectedProperty() {
		return this.selected;
	}

	public final boolean isSelected() {
		return this.selectedProperty().get();
	}
	
	public final void setSelected(final boolean selected) {
		this.selectedProperty().set(selected);
	}
}
