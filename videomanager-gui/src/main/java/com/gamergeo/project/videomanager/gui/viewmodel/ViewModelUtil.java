//package com.gamergeo.project.videomanager.gui.viewmodel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.gamergeo.project.videomanager.model.Video;
//
//public class ViewModelUtil {
//	
//	private class ViewModelFactory<Model, ViewModel extends IViewModel<Model>> {
//		
//	    public List<ViewModel> copy(List<Model> models) {
//	    	List<ViewModel> viewModels =  new ArrayList<ViewModel>();
//	    	for(Model model : models) {
//	    		viewModels.add(toViewModel(model));
//	    	}
//	    	
//	    	return viewModels;
//	    }
//	}
//	
//	private static ViewModelFactory instance;
//	
//	public static void getInstance() {
//		
//		if (instance == null) {
//			instance = new ViewModelFactory<Model, IViewModel<Model>>();
//		}
//	}
//	
//    public static List<AbstractViewModel> copy(List models) {
//    	List<AbstractViewModel> viewModels =  new ArrayList<AbstractViewModel>();
//    	for(Object model : models) {
//    		viewModels.add(getViewModel(model));
//    	}
//    	
//    	return viewModels;
//    }
//
//}
