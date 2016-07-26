
package cn.northpark.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.northpark.dao.LyricsCommentDao;
import cn.northpark.manager.LyricsCommentManager;
import cn.northpark.model.LyricsComment;
import cn.northpark.utils.MyConstant;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;

@Service("LyricsCommentManager")
public class LyricsCommentManagerImpl implements LyricsCommentManager {

    @Autowired
	private LyricsCommentDao lyricscommentDao;

	@Override
	public LyricsComment findLyricsComment(Integer id) {
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
	public boolean delLyricsComment(Integer id) {
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

	@Override
	public List<LyricsComment> querySql(String sql, Object... obj) {
		// TODO Auto-generated method stub
		return lyricscommentDao.querySql(sql, LyricsComment.class, obj);
	}

	@Override
	public List<LyricsComment> querySql(String sql) {
		// TODO Auto-generated method stub
		return lyricscommentDao.querySql(sql, LyricsComment.class);
	}

	@Override
	public PageView<List<Map<String, Object>>> querySqlMap(String sql,PageView<List<Map<String,Object>>> pageView) {
		// TODO Auto-generated method stub
		return lyricscommentDao.QuerySQLForMapList(sql, pageView);
	}

	/* (non-Javadoc)
	 * @see cn.northpark.manager.LyricsCommentManager#countSql(java.lang.String)
	 */
	@Override
	public int countSql(String sql) {
		// TODO Auto-generated method stub
		return lyricscommentDao.countSql(sql);
	}

	/* (non-Javadoc)
	 * @see cn.northpark.manager.LyricsCommentManager#countHql(cn.northpark.model.Lyrics, java.lang.String)
	 */
	@Override
	public int countHql(LyricsComment m, String wheresql) {
		// TODO Auto-generated method stub
		return lyricscommentDao.countHql(m.getClass(), wheresql);
	}
}
