package com.gamergeo.project.videomanager.gui.viewmodel;

import java.util.List;
import java.util.StringJoiner;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.viewmodel.AbstractChildViewModel;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.model.Video;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Component
@Scope("prototype")
public class TableRowViewModel extends AbstractChildViewModel<TableViewModel> {

	private Video video;
	
	private final StringProperty title = new SimpleStringProperty();
	private final StringProperty rating = new SimpleStringProperty();
	private final StringProperty tags = new SimpleStringProperty();
	
	public TableRowViewModel(Video video) {
		this.video = video;
		title.bind(video.titleProperty());
		setRating(video.getRating());
		addSimpleChangeListener(video.ratingProperty(), this::setRating);
		setTags(video.getTags());
		addSimpleListChangeListener(video.tagsProperty(), this::setTags);
	}
	
    private void setRating(Number rating) {
        if (rating.doubleValue() == 0d) {
        	setRating("");
        }

        int fullStars = rating.intValue();
        double fraction = rating.doubleValue() - fullStars;
        String stars = "★".repeat(Math.max(0, fullStars));
        
        String halfStar = fraction >= 0.5 ? "½" : "";
        
        setRating(stars + halfStar);
    }
    
    private void setTags(List<Tag> tags) {
		StringJoiner joiner = new StringJoiner(" / ");
		tags.forEach((tag) -> joiner.add(tag.getLabel()));
        setTags(joiner.toString());
    }

	public Video getVideo() {
		return video;
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

	public final StringProperty ratingProperty() {
		return this.rating;
	}
	
	public final String getRating() {
		return this.ratingProperty().get();
	}
	
	public final void setRating(final String rating) {
		this.ratingProperty().set(rating);
	}

	public final StringProperty tagsProperty() {
		return this.tags;
	}

	public final String getTags() {
		return this.tagsProperty().get();
	}

	public final void setTags(final String tags) {
		this.tagsProperty().set(tags);
	}
	
	
	
}
