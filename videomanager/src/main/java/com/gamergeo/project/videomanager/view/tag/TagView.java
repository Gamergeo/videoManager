//	package com.gamergeo.project.videomanager.gui.view.tag;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import com.gamergeo.lib.viewmodelfx.view.FXMLView;
//import com.gamergeo.project.videomanager.gui.viewmodel.tag.TagViewModel;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.layout.BorderPane;
//
//@Component
//@Scope("prototype")
//public class TagView extends FXMLView<TagViewModel>{
//	
//	@FXML
//	private BorderPane root;
//	
//	@FXML
//	private Label label;
//	
//	@FXML
//	private void initialize() { 
//		label.textProperty().bindBidirectional(viewModel.labelProperty());
//		root.setOnMouseClicked((event) -> viewModel.onClick());
//		
//		addSimpleChangeListener(viewModel.selectedProperty(), this::select);
//	}
//	
//	private void select(boolean isSelected) {
//		if (isSelected) {
//			root.getStyleClass().add("tag-border-pane-selected");
//		} else {
//			root.getStyleClass().remove("tag-border-pane-selected");
//		}
//	}
//}