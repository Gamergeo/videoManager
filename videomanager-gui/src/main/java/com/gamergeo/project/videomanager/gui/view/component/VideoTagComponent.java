package com.gamergeo.project.videomanager.gui.view.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.gamlib.javafx.loader.ApplicationLoader;
import com.gamergeo.lib.gamlib.javafx.view.AbstractFXMLView;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class VideoTagComponent extends AbstractFXMLView implements FXMLView {
	
	@Autowired
	ApplicationLoader loader;
	
	@Override
	public String getClasspath() {
		return "fxml/videoTagComponent.fxml";
	}
	
}
