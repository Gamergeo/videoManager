//package com.gamergeo.project.videomanager.gui.viewmodel.old;
//
//import java.io.Serializable;
//
//import com.gamergeo.project.videomanager.model.Tag;
//
//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;
//
//public class TagViewModelOld implements Serializable {
//	
//	private static final long serialVersionUID = 3009196317499043074L;
//
//	private Tag initialModel;
//	
//	private Tag model;
//	
//    private final StringProperty text = new SimpleStringProperty();
//    
//    public TagViewModelOld(Tag tag) {
//    	model = tag;
//    	initialModel = tag;
//
//    	this.text.set(tag.getText());
//    	this.text.addListener((x, oldValue, newValue) -> tag.setText(newValue));
//    }
//    
//	public final StringProperty textProperty() {
//		return this.text;
//	}
//	
//	public final String getText() {
//		return this.textProperty().get();
//	}
//
//	public final void setText(final String text) {
//		this.textProperty().set(text);
//	}
//
//	public Tag getInitialModel() {
//		return initialModel;
//	}
//
//	public void setInitialModel(Tag initialModel) {
//		this.initialModel = initialModel;
//	}
//
//	public Tag getModel() {
//		return model;
//	}
//
//	public void setModel(Tag model) {
//		this.model = model;
//	}
//	
//	public Long getId() {
//		return model.getId();
//	}
//}
