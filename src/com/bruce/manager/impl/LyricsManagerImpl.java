
package com.bruce.manager.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bruce.model.Lyrics;
import com.bruce.manager.LyricsManager;
import com.bruce.dao.LyricsDao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

@Service("LyricsManager")
public class LyricsManagerImpl implements LyricsManager {

    @Autowired
	private LyricsDao lyricsDao;

	@Override
	public Lyrics findLyrics(String id) {
		return lyricsDao.find(id);
	}

	@Override
	public List<Lyrics> findAll() {
		return lyricsDao.findAll();
	}

	@Override
	public void addLyrics(Lyrics lyrics) {
		lyricsDao.save(lyrics);
	}

	@Override
	public boolean delLyrics(String id) {
		Lyrics lyrics = lyricsDao.find(id);
		lyricsDao.delete(lyrics);
		return true;
	}

	@Override
	public boolean updateLyrics(Lyrics lyrics) {
		lyricsDao.update(lyrics);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Lyrics> findByCondition(PageView<Lyrics> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = lyricsDao.findByCondition(p.getStartindex(),
				MyConstant.MAXRESULT, wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Lyrics> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = lyricsDao.findByCondition(
				 wheresql);
		return qrs;
	}

}

