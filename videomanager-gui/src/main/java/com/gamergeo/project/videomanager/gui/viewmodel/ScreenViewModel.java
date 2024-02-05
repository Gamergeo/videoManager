package com.gamergeo.project.videomanager.gui.viewmodel;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.javafx.viewmodel.AbstractViewModel;
import com.gamergeo.project.videomanager.gui.view.ScreenView;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor_ ={@Lazy})
public class ScreenViewModel extends AbstractViewModel<ScreenView> {
    
    private final ObjectProperty<VideoViewModel> video = new SimpleObjectProperty<VideoViewModel>();
    
    public ObjectProperty<VideoViewModel> video() {
    	return video;
    }
}
