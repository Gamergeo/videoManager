package com.gamergeo.project.videomanager.gui.viewmodel.view;

import com.gamergeo.project.videomanager.VideoManagerException;

public class SceneElementViewModel {
	
	protected SceneViewModel scene;
	
	public void setScene(SceneViewModel scene) {
		if (scene == null) {
			throw new VideoManagerException("SceneModel has not been set");
		}
		
		this.scene = scene;
	}
}
