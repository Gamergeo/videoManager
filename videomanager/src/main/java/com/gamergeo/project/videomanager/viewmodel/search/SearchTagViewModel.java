package com.gamergeo.project.videomanager.viewmodel.search;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.viewmodel.tag.TagDroppableViewModel;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
@Scope("prototype")
public class SearchTagViewModel implements ViewModel, TagDroppableViewModel {
	
	private final BooleanProperty with = new SimpleBooleanProperty();
	private final StringProperty label = new SimpleStringProperty();
	private final ListProperty<Tag> renderedTags = new SimpleListProperty<Tag>(FXCollections.observableArrayList());
	private final BooleanProperty dragOver = new SimpleBooleanProperty();
	private final BooleanProperty dragReleased = new SimpleBooleanProperty();

	@Override
	public void deleteTag(Tag tag) {
		renderedTags.remove(tag);
	}

	public final ListProperty<Tag> renderedTagsProperty() {
		return this.renderedTags;
	}

	public final ObservableList<Tag> getRenderedTags() {
		return this.renderedTagsProperty().get();
	}

	@Override
	public void onMouseDragOver() {
		setDragOver(true);
	}

	@Override
	public void onMouseDragExited() {
		setDragOver(false);
	}

	@Override
	public void onMouseDragReleased() {
		setDragReleased(true);
	}

	public final void setWith(final boolean with) {	
		setLabel(with ? "With: " : "Without: ");
		this.withProperty().set(with);
	}

	public final void setRenderedTags(final ObservableList<Tag> renderedTags) {
		this.renderedTagsProperty().set(renderedTags);
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
	
	public final BooleanProperty withProperty() {
		return this.with;
	}

	public final boolean isWith() {
		return this.withProperty().get();
	}

	public final BooleanProperty dragOverProperty() {
		return this.dragOver;
	}
	
	public final boolean isDragOver() {
		return this.dragOverProperty().get();
	}

	public final void setDragOver(final boolean dragOver) {
		this.dragOverProperty().set(dragOver);
	}
	
	public final BooleanProperty dragReleasedProperty() {
		return this.dragReleased;
	}
	
	public final boolean isDragReleased() {
		return this.dragReleasedProperty().get();
	}

	public final void setDragReleased(final boolean dragReleased) {
		this.dragReleasedProperty().set(dragReleased);
	}
	
}
