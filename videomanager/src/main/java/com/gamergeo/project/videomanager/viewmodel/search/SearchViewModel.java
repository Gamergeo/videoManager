package com.gamergeo.project.videomanager.viewmodel.search;

import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.model.Tag;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Component
public class SearchViewModel implements ViewModel {
	
	private SearchTagViewModel withTagsViewModel;
	private SearchTagViewModel withoutTagsViewModel;
	
	/** Search properties **/
	private final StringProperty title = new SimpleStringProperty();
	private final DoubleProperty rating = new SimpleDoubleProperty();
	private final ListProperty<Tag> withTags = new SimpleListProperty<Tag>(FXCollections.observableArrayList());
	private final ListProperty<Tag> withoutTags = new SimpleListProperty<Tag>(FXCollections.observableArrayList());
	
	/** Action properties **/
	private final BooleanProperty randomClick = new SimpleBooleanProperty();
	private final BooleanProperty resetClick = new SimpleBooleanProperty();
	
	/** Drag properties **/
	private final BooleanProperty dragWithReleased = new SimpleBooleanProperty();
	private final BooleanProperty dragWithoutReleased = new SimpleBooleanProperty();
	private final BooleanProperty dragOver = new SimpleBooleanProperty();

	public SearchViewModel() {
		// Semi rating
		rating.addListener((observable, oldValue, newValue) -> setRoundedRating(oldValue.doubleValue(), newValue.doubleValue()));
		
		FXUtils.addSimpleChangeListener(resetClick, this::reset);
	}
	
	public void reset(boolean isClicked) {
		if (isClicked) {
			title.set("");
			rating.set(0);
			resetClick.set(false);
		}
	}
	
	/**
	 * Round rating to 0.5
	 */
	private void setRoundedRating(Double oldValue, Double newValue) {
	    double roundedValue = Math.round(newValue * 2) / 2.0;
		if (oldValue != newValue && !newValue.equals(roundedValue)) {
	    	setRating(roundedValue);
		}
	}

	/**
	 * Init search tag view models
	 */
	public void initSearchTag(SearchTagViewModel viewModel, boolean with) {
		if (with) {
			withTagsViewModel = viewModel;
			withTags.bindContent(withTagsViewModel.getRenderedTags()); // Bind tag content
			FXUtils.addEmptyChangeListener(withTagsViewModel.dragOverProperty(), this::dragOver);
			FXUtils.addSimpleChangeListener(withTagsViewModel.dragReleasedProperty(), this::setDragWithReleased);
		} else {
			withoutTagsViewModel = viewModel;
			withoutTags.bindContent(withoutTagsViewModel.getRenderedTags()); // Bind tag content
			FXUtils.addEmptyChangeListener(withoutTagsViewModel.dragOverProperty(), this::dragOver);
			FXUtils.addSimpleChangeListener(withoutTagsViewModel.dragReleasedProperty(), this::setDragWithoutReleased);
		}
	}
	
	private void dragOver() {
		setDragOver((withTagsViewModel.isDragOver() || withoutTagsViewModel.isDragOver()));
	}

	/**
	 * Add tag on with tags
	 */
	public void addTags(ObservableList<Tag> selectedTags, boolean with) {
		if (selectedTags.isEmpty()) {
			return;
		}
		
		SearchTagViewModel searchTagViewModel = getSearchTagViewModel(with);
		
		// Add tag not present
		selectedTags.stream()
	    	.filter(tag -> !searchTagViewModel.renderedTagsProperty().contains(tag))
	    	.forEach(searchTagViewModel.getRenderedTags()::add);
	}
	
	private SearchTagViewModel getSearchTagViewModel(boolean with) {
		return with ? withTagsViewModel : withoutTagsViewModel;
	}

	public BooleanProperty dragReleasedProperty(boolean with) {
		return with ? dragWithReleasedProperty() : dragWithoutReleasedProperty();
	}

	public void setDragReleased(boolean with, boolean dragReleased) {
		dragReleasedProperty(with).set(dragReleased);
		getSearchTagViewModel(with).setDragReleased(dragReleased);
	}

	public void deleteTag(Tag tag) {
		withTagsViewModel.deleteTag(tag);
		withoutTagsViewModel.deleteTag(tag);
	}

	public final StringProperty titleProperty() {
		return this.title;
	}
	
	public final String getTitle() {
		return this.titleProperty().get();
	}
	
	public final void setTitle(final String title) {
		this.titleProperty().set(title);
	}
	
	public final DoubleProperty ratingProperty() {
		return this.rating;
	}
	
	public final double getRating() {
		return this.ratingProperty().get();
	}
	
	public final void setRating(final double rating) {
		this.ratingProperty().set(rating);
	}

	public final BooleanProperty randomClickProperty() {
		return this.randomClick;
	}
	
	public final boolean isRandomClick() {
		return this.randomClickProperty().get();
	}
	
	public final void setRandomClick(final boolean randomClick) {
		this.randomClickProperty().set(randomClick);
	}
	
	public final BooleanProperty resetClickProperty() {
		return this.resetClick;
	}

	public final boolean isResetClick() {
		return this.resetClickProperty().get();
	}

	public final void setResetClick(final boolean resetClick) {
		this.resetClickProperty().set(resetClick);
	}

	public final BooleanProperty dragWithReleasedProperty() {
		return this.dragWithReleased;
	}
	

	public final boolean isDragWithReleased() {
		return this.dragWithReleasedProperty().get();
	}
	

	public final void setDragWithReleased(final boolean dragWithReleased) {
		this.dragWithReleasedProperty().set(dragWithReleased);
	}
	

	public final BooleanProperty dragWithoutReleasedProperty() {
		return this.dragWithoutReleased;
	}

	public final boolean isDragWithoutReleased() {
		return this.dragWithoutReleasedProperty().get();
	}
	
	public final void setDragWithoutReleased(final boolean dragWithoutReleased) {
		this.dragWithoutReleasedProperty().set(dragWithoutReleased);
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

	public final ListProperty<Tag> withTagsProperty() {
		return this.withTags;
	}

	public final ObservableList<Tag> getWithTags() {
		return this.withTagsProperty().get();
	}

	public final void setWithTags(final ObservableList<Tag> withTags) {
		this.withTagsProperty().set(withTags);
	}
	
	public final ListProperty<Tag> withoutTagsProperty() {
		return this.withoutTags;
	}

	public final ObservableList<Tag> getWithoutTags() {
		return this.withoutTagsProperty().get();
	}
	
	public final void setWithoutTags(final ObservableList<Tag> withoutTags) {
		this.withoutTagsProperty().set(withoutTags);
	}
}