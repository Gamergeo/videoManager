package com.gamergeo.project.videomanager.gui.cell;

import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class RatingCellFactory implements Callback<TableColumn<VideoViewModel, Number>, TableCell<VideoViewModel, Number>> {
	
    @Override
    public TableCell<VideoViewModel, Number> call(TableColumn<VideoViewModel, Number> param) {
        return new TableCell<VideoViewModel, Number>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(convertRatingToStars(item.doubleValue()));
                }
            }
        };
    }

    private String convertRatingToStars(double rating) {
        if (rating == 0d) {
            return "";
        }

        int fullStars = (int) rating;
        double fraction = rating - fullStars;
        String stars = "★".repeat(Math.max(0, fullStars));
        
        String halfStar = fraction >= 0.5 ? "½" : "";
        
        return stars + halfStar;
    }
}