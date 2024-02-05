	package com.gamergeo.project.videomanager.gui.viewmodel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.gamergeo.lib.gamlib.javafx.viewmodel.AbstractViewModel;
import com.gamergeo.lib.gamlib.javafx.viewmodel.ViewModel;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ViewModel
public class TagsViewModel extends AbstractViewModel {
	
	@Autowired
	@Lazy
	protected SceneViewModel scene;
	
	@FXML
	private TextField search;
	
	@FXML
	private TilePane list;
	
	private List<TagViewModel> tags;

}
