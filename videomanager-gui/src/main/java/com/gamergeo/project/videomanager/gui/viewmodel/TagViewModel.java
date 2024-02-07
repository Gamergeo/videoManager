package com.gamergeo.project.videomanager.gui.viewmodel;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.javafx.viewmodel.AbstractChildViewModel;
import com.gamergeo.project.videomanager.model.Tag;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Component
@Scope("prototype")
public class TagViewModel extends AbstractChildViewModel<TagListViewModel>{
	
	private Tag tag;
	
	private final StringProperty label = new SimpleStringProperty();
	
	public void setTag(Tag tag) {
		this.tag = tag;
		label.bindBidirectional(this.tag.titleProperty());
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
}
