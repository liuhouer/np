
package com.bruce.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.dao.LyricsDao;
import com.bruce.manager.LyricsManager;
import com.bruce.model.Lyrics;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

@Service("LyricsManager")
public class LyricsManagerImpl implements LyricsManager {

    @Autowired
	private LyricsDao lyricsDao;

	@Override
	public Lyrics findLyrics(Integer id) {
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
	public boolean delLyrics(Integer id) {
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

	/* (non-Javadoc)
	 * @see com.bruce.manager.LyricsManager#countSql(java.lang.String)
	 */
	@Override
	public int countSql(String sql) {
		// TODO Auto-generated method stub
		return lyricsDao.countSql(sql);
	}

	/* (non-Javadoc)
	 * @see com.bruce.manager.LyricsManager#countHql(com.bruce.model.Lyrics, java.lang.String)
	 */
	@Override
	public int countHql(Lyrics m, String wheresql) {
		// TODO Auto-generated method stub
		return lyricsDao.countHql(m.getClass(), wheresql);
	}

}

