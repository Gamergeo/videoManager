package com.gamergeo.project.videomanager.gui.component;

import java.io.IOException;

import org.controlsfx.control.Rating;

import javafx.fxml.FXMLLoader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SemiRating extends Rating {
	
    public SemiRating() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/component/semirating.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
//		ratingProperty().addListener((obs, oldVal, newVal) -> {
//
//			log.info("Change rating semirating");
//		    double roundedValue = Math.round(newVal.doubleValue() * 2) / 2.0;
//		    if (!newVal.equals(roundedValue)) {
//		    	setRating(roundedValue);
//		    }
//		});
    }
}
