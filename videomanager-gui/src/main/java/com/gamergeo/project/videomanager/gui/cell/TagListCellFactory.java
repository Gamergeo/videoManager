package com.gamergeo.project.videomanager.gui.cell;

import java.util.StringJoiner;

import com.gamergeo.project.videomanager.gui.viewmodel.TagViewModel;
import com.gamergeo.project.videomanager.gui.viewmodel.VideoViewModel;

import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class TagListCellFactory implements Callback<TableColumn<VideoViewModel, ObservableList<TagViewModel>>, TableCell<VideoViewModel, ObservableList<TagViewModel>>> {
	
    @Override
    public TableCell<VideoViewModel, ObservableList<TagViewModel>> call(TableColumn<VideoViewModel, ObservableList<TagViewModel>> param) {
        return new TableCell<VideoViewModel, ObservableList<TagViewModel>>() {
            @Override
            protected void updateItem(ObservableList<TagViewModel> item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
            		StringJoiner joiner = new StringJoiner(" / ");
            		item.forEach((videoTag) -> joiner.add(videoTag.getText()));
                    setText(joiner.toString());
                }
            }
        };
    }

}