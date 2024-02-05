	package com.gamergeo.project.videomanager.gui.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.gamergeo.lib.gamlib.javafx.view.AbstractView;
import com.gamergeo.lib.gamlib.javafx.view.View;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@View
public class TagsViewModel extends AbstractView {
	
	@Autowired
	@Lazy
	protected SceneView scene;
	
	@FXML
	private TextField search;
	
	@FXML
	private TilePane list;
	
	private List<TagViewModel> tags;

}
