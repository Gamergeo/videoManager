package com.gamergeo.project.videomanager.gui.viewmodel.tag;

import com.gamergeo.lib.viewmodelfx.viewmodel.AbstractChildViewModel;
import com.gamergeo.lib.viewmodelfx.viewmodel.ViewModel;
import com.gamergeo.project.videomanager.model.Tag;

public abstract class TagParentViewModel<VM extends ViewModel> extends AbstractChildViewModel<VM> {
	
	/**
	 * Select / Unselect a tag
	 * @return true if tag is selected, false if unselected
	 */
	public boolean onTagClick(Tag tag) {
		return false;
	};
}
