//package com.gamergeo.project.videomanager.gui.cell;
//
//import java.util.StringJoiner;
//
//import com.gamergeo.project.videomanager.gui.viewmodel.old.TagViewModelOld;
//import com.gamergeo.project.videomanager.gui.viewmodel.old.VideoViewModelOld;
//
//import javafx.collections.ObservableList;
//import javafx.scene.control.TableCell;
//import javafx.scene.control.TableColumn;
//import javafx.util.Callback;
//
//public class TagListCellFactory implements Callback<TableColumn<VideoViewModelOld, ObservableList<TagViewModelOld>>, TableCell<VideoViewModelOld, ObservableList<TagViewModelOld>>> {
//	
//    @Override
//    public TableCell<VideoViewModelOld, ObservableList<TagViewModelOld>> call(TableColumn<VideoViewModelOld, ObservableList<TagViewModelOld>> param) {
//        return new TableCell<VideoViewModelOld, ObservableList<TagViewModelOld>>() {
//            @Override
//            protected void updateItem(ObservableList<TagViewModelOld> item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty || item == null) {
//                    setText(null);
//                    setGraphic(null);
//                } else {
//            		StringJoiner joiner = new StringJoiner(" / ");
//            		item.forEach((videoTag) -> joiner.add(videoTag.getText()));
//                    setText(joiner.toString());
//                }
//            }
//        };
//    }
//
//}