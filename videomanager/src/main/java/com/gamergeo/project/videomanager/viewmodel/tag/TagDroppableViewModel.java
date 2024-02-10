package com.gamergeo.project.videomanager.viewmodel.tag;

public interface TagDroppableViewModel extends TagParentViewModel{

	void onMouseDragOver();

	void onMouseDragExited();

	void setDragReleased(boolean dragReleased);
	
}