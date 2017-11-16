
package cn.northpark.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.northpark.dao.UserLyricsDao;
import cn.northpark.manager.UserLyricsManager;
import cn.northpark.model.UserLyrics;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

@Service("UserLyricsManager")
public class UserLyricsManagerImpl implements UserLyricsManager {

    @Autowired
	private UserLyricsDao userlyricsDao;

	@Override
	public UserLyrics findUserLyrics(Integer id) {
		return userlyricsDao.find(id);
	}

	@Override
	public List<UserLyrics> findAll() {
		return userlyricsDao.findAll();
	}

	@Override
	public void addUserLyrics(UserLyrics userlyrics) {
		userlyricsDao.save(userlyrics);
	}

	@Override
	public boolean delUserLyrics(Integer id) {
		UserLyrics userlyrics = userlyricsDao.find(id);
		userlyricsDao.delete(userlyrics);
		return true;
	}

	@Override
	public boolean updateUserLyrics(UserLyrics userlyrics) {
		userlyricsDao.update(userlyrics);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<UserLyrics> findByCondition(PageView<UserLyrics> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = userlyricsDao.findByCondition(p.getFirstResult(),
				MyConstant.MAXRESULT, wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<UserLyrics> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = userlyricsDao.findByCondition(
				 wheresql);
		return qrs;
	}

	@Override
	public PageView<List<Map<String, Object>>> getMixMapData(String currentpage,String userid) {
		String sql = " select a.id,a.title,a.artist,a.album,a.updatedate,a.albumImg,a.zan,a.pl,c.id as userid,c.username,c.email,  "
	               
				   + " case when  (select count(id) from bc_lyrics_zan d where d.lyricsid = a.id and d.userid = '"+userid+"' )> 0 "
	               +" then 'yizan' "
	               +" else '' "
	               +" end "
	               +" as yizan "
	
				   + "from bc_lyrics a join bc_user_lyrics b on a.id = b.lyricsid join bc_user c on c.id = b.userid ";

		       sql+=" order by a.updatedate desc";
		
		PageView<List<Map<String, Object>>> pageview = new PageView<List<Map<String,Object>>>(Integer.parseInt(currentpage),MyConstant.MAXRESULT);
		List<Map<String, Object>> list  = userlyricsDao.QuerySQLForMapList(sql, pageview);
		pageview.setQueryResult(new QueryResult<List<Map<String,Object>>>(list));
		return pageview;
		
		
	}


	@Override
	public PageView<List<Map<String, Object>>> getMixMapPage(
			String currentpage, String userid) {
		// TODO Auto-generated method stub
		String sql = " select a.id,a.title,a.artist,a.album,a.updatedate,a.albumImg,a.zan,a.pl,c.id as userid,c.username,c.email,  "
	               
				   + " case when  (select count(id) from bc_lyrics_zan d where d.lyricsid = a.id and d.userid = '"+userid+"' )> 0 "
	               +" then 'yizan' "
	               +" else '' "
	               +" end "
	               +" as yizan "
	
				   + "from bc_lyrics a join bc_user_lyrics b on a.id = b.lyricsid join bc_user c on c.id = b.userid ";

		       sql+=" order by a.updatedate desc";
		
		//只设置分页信息
		       PageView<List<Map<String, Object>>> pageview = new PageView<List<Map<String,Object>>>(Integer.parseInt(currentpage),MyConstant.MAXRESULT);
				List<Map<String, Object>> list  = userlyricsDao.QuerySQLForMapList(sql, pageview);
				pageview.setQueryResult(new QueryResult<List<Map<String,Object>>>(list));
		return pageview;
	}

}

