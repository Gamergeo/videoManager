package com.gamergeo.project.videomanager.viewmodel.table;

import java.util.List;
import java.util.StringJoiner;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.model.Tag;
import com.gamergeo.project.videomanager.model.Video;

import de.saxsys.mvvmfx.ViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

@Component
@Scope("prototype")
public class TableRowViewModel implements ViewModel {

	private Video video;
	
	private final StringProperty title = new SimpleStringProperty();
	private final StringProperty rating = new SimpleStringProperty();
	private final StringProperty tags = new SimpleStringProperty();
	
	public TableRowViewModel(Video video) {
		this.video = video;
		title.bind(video.titleProperty());
		setRating(video.getRating());
		FXUtils.addSimpleChangeListener(video.ratingProperty(), this::setRating);
		setTags(video.getTags());
		FXUtils.addSimpleListChangeListener(video.tagsProperty(), this::setTags);
	}
	
    private void setRating(Number newValue) {
        if (newValue.doubleValue() == 0d) {
        	setRating("");
        }

        int fullStars = newValue.intValue();
        double fraction = newValue.doubleValue() - fullStars;
        String stars = "★".repeat(Math.max(0, fullStars));
        
        String halfStar = fraction >= 0.5 ? "½" : "";
        
        setRating(stars + halfStar);
    }
    
    private void setTags(List<Tag> tags) {
		StringJoiner joiner = new StringJoiner(" / ");
		tags.forEach((tag) -> joiner.add(tag.getLabel()));
        setTags(joiner.toString());
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        TableRowViewModel row = (TableRowViewModel) o;
        
        return row.getVideo() != null && video.getId() == row.getVideo().getId();
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