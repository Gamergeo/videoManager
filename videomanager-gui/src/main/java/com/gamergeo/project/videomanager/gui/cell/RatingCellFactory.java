package com.gamergeo.project.videomanager.gui.cell;

import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class RatingCellFactory implements Callback<TableColumn<VideoViewModel, Double>, TableCell<VideoViewModel, Double>> {
	
    @Override
    public TableCell<VideoViewModel, Double> call(TableColumn<VideoViewModel, Double> param) {
        return new TableCell<VideoViewModel, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(convertRatingToStars(item));
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

