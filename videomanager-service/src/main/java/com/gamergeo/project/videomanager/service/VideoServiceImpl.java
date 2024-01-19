	package com.gamergeo.project.videomanager.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.gamergeo.project.videomanager.model.Category;
import com.gamergeo.project.videomanager.model.Video;

@Service("videoService")
public class VideoServiceImpl implements VideoService {

	@Override
	public List<Video> getVideoList() {
		Category cat1 = new Category();
		cat1.setText("cat 1");
		
		Category cat2 = new Category();
		cat2.setText("cat 2");
		
		Category cat3 = new Category();
		cat3.setText("cat 3");
		
		List<Category> cats1 = new ArrayList<Category>();
		cats1.add(cat1);
		List<Category> cats2 = new ArrayList<Category>();
		cats2.add(cat2);
		cats2.add(cat3);
		
		Video vid1 = new Video();
//		vid1.setId(1);
		vid1.setTitle("a");
		vid1.setLink("link a");
		vid1.setCategories(cats1);
		
		Video vid2 = new Video();
//		vid1.setId(2);
		vid2.setTitle("b");
		vid2.setLink("link b");
		vid2.setCategories(cats2);
		
		List<Video> vids = new ArrayList<Video>();
		vids.add(vid1);
		vids.add(vid2);
		
		return vids;
	}

}