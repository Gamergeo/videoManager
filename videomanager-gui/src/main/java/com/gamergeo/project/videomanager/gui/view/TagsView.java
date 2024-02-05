	package com.gamergeo.project.videomanager.gui.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TagsView {
	
	@Autowired
	@Lazy
	protected SceneView scene;
	
	@FXML
	private TextField search;
	
	@FXML
	private TilePane list;
	
	private List<TagView> tags;

}
