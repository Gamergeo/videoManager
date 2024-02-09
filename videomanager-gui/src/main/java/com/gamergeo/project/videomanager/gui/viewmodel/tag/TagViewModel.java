package com.gamergeo.project.videomanager.gui.viewmodel.tag;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.viewmodel.DefaultChildViewModel;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Component
@Scope("prototype")
public class TagViewModel extends DefaultChildViewModel<TagListViewModel>{

	private Tag tag;
	
	private final StringProperty label = new SimpleStringProperty();
	private final BooleanProperty selected = new SimpleBooleanProperty();
	
	@Override
	public void init() {
		super.init();
		this.tag = getParameter(Tag.class);
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
