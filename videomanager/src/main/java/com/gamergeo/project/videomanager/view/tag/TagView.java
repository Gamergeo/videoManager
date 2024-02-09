	package com.gamergeo.project.videomanager.view.tag;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.gamergeo.lib.viewmodelfx.view.FXUtils;
import com.gamergeo.project.videomanager.viewmodel.tag.TagViewModel;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

@Component
@Scope("prototype")
public class TagView implements FxmlView<TagViewModel>, Initializable {
	
	@FXML
	private BorderPane root;
	
	@FXML
	private Label label;

    @InjectViewModel
    private TagViewModel viewModel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		label.textProperty().bindBidirectional(viewModel.labelProperty());
		root.setOnMouseClicked((event) -> viewModel.setClicked(true));
		FXUtils.addSimpleChangeListener(viewModel.clickedProperty(), this::select);
	}
	
	private void select(boolean isSelected) {
		if (viewModel.isSelectable()) {
			if (isSelected) {
				root.getStyleClass().add("tag-border-pane-selected");
			} else {
				root.getStyleClass().remove("tag-border-pane-selected");
			}
		}
	}
}