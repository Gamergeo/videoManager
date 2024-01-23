	package com.gamergeo.project.videomanager.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamergeo.project.videomanager.model.Video;
import com.gamergeo.project.videomanager.model.VideoTag;
import com.gamergeo.project.videomanager.persistence.VideoDao;

@Service("videoService")
public class VideoServiceImpl implements VideoService { //extends HibernateDatabaseServiceImpl<Video> 
	
	@Autowired
	VideoDao videoDao;
//
//	@Override
//	public HibernateDao<Video> getDao() {
//		return videoDao;
//	}
	
	@Override
	public Video getVideo() {
		Video vid1 = new Video();
//		vid1.setId(1);
		vid1.setTitle("a");
		vid1.setUrl("link a");
//		vid1.setAddedDate(LocalDate.now());
//		vid1.setVideoTags(cats1);
		
//		videoDao.save(vid1);
		
		return videoDao.findById(1).get();
	}

	@Override
	public List<Video> getVideoList() {
		VideoTag cat1 = new VideoTag();
		cat1.setText("cat 1");
		
		VideoTag cat2 = new VideoTag();
		cat2.setText("cat 2");
		
		VideoTag cat3 = new VideoTag();
		cat3.setText("cat 3");
		
		List<VideoTag> cats1 = new ArrayList<VideoTag>();
		cats1.add(cat1);
		List<VideoTag> cats2 = new ArrayList<VideoTag>();
		cats2.add(cat2);
		cats2.add(cat3);
		
		Video vid1 = new Video();
//		vid1.setId(1);
		vid1.setTitle("a");
		vid1.setUrl("link a");
		vid1.setAddedDate(LocalDate.now());
		vid1.setVideoTags(cats1);
		
		Video vid2 = new Video();
//		vid1.setId(2);
		vid2.setTitle("b");
		vid2.setUrl("link b");
		vid2.setVideoTags(cats2);
		vid2.setAddedDate(LocalDate.now().minus(Period.ofMonths(2)));
		
		List<Video> vids = new ArrayList<Video>();
		vids.add(vid1);
		vids.add(vid2);
		
		return vids;
	}

}