package com.gamergeo.project.videomanager.gui.loader;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public interface ApplicationLoader {

	Node load(String name);

	FXMLLoader getLoader(String name);

	Node load(FXMLLoader loader);

}
