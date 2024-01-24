package com.gamergeo.project.videomanager.gui.loader;

import com.gamergeo.lib.gamlib.javafx.loader.ApplicationLoader;
import com.gamergeo.lib.gamlib.javafx.view.FXMLView;

public interface VideoManagerLoader extends ApplicationLoader {

	@SuppressWarnings("rawtypes")
	@Override
	FXMLView createView(Class viewClass);
}
