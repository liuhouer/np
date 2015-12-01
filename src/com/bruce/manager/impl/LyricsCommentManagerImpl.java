
package com.bruce.manager.impl;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.bruce.model.LyricsComment;
import com.bruce.manager.LyricsCommentManager;
import com.bruce.dao.LyricsCommentDao;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

@Service("LyricsCommentManager")
public class LyricsCommentManagerImpl implements LyricsCommentManager {

    @Autowired
	private LyricsCommentDao lyricscommentDao;

	@Override
	public LyricsComment findLyricsComment(String id) {
		return lyricscommentDao.find(id);
	}

	@Override
	public List<LyricsComment> findAll() {
		return lyricscommentDao.findAll();
	}

	@Override
	public void addLyricsComment(LyricsComment lyricscomment) {
		lyricscommentDao.save(lyricscomment);
	}

	@Override
	public boolean delLyricsComment(String id) {
		LyricsComment lyricscomment = lyricscommentDao.find(id);
		lyricscommentDao.delete(lyricscomment);
		return true;
	}

	@Override
	public boolean updateLyricsComment(LyricsComment lyricscomment) {
		lyricscommentDao.update(lyricscomment);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<LyricsComment> findByCondition(PageView<LyricsComment> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = lyricscommentDao.findByCondition(p.getStartindex(),
				MyConstant.MAXRESULT, wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<LyricsComment> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = lyricscommentDao.findByCondition(
				 wheresql);
		return qrs;
	}
}

