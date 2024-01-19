package com.gamergeo.project.videomanager.gui.viewmodel;

public interface IViewModel<Model> {

	public IViewModel<Model> toViewModel(Model model);
	
	public Model toModel(IViewModel<Model> viewModel);
}
