package com.gamergeo.project.videomanager.viewmodel.tag;

import org.springframework.context.annotation.Scope;

import org.springframework.stereotype.Component;

import com.gamergeo.project.videomanager.model.Tag;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Component
@Scope("prototype")
public class TagViewModel implements ViewModel {

	private Tag tag;
	
	private final StringProperty label = new SimpleStringProperty();
	private final BooleanProperty clicked = new SimpleBooleanProperty(); // true on click
	private final BooleanProperty selectable = new SimpleBooleanProperty(); // true if selectable
	
	public void setTag(Tag tag) {
		if (this.tag != null) { // Useless but for security, normally tag should not change after initialisation
			label.unbindBidirectional(this.tag.labelProperty());
		}
		this.tag = tag;
		label.bindBidirectional(this.tag.labelProperty());
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

	public final BooleanProperty clickedProperty() {
		return this.clicked;
	}

	public final boolean isClicked() {
		return this.clickedProperty().get();
	}
	
	public final void setClicked(final boolean clicked) {
		this.clickedProperty().set(clicked);
	}

	public final BooleanProperty selectableProperty() {
		return this.selectable;
	}
	
	public final boolean isSelectable() {
		return this.selectableProperty().get();
	}

	public final void setSelectable(final boolean selectable) {
		this.selectableProperty().set(selectable);
	}
	
}