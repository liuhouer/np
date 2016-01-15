
package com.bruce.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.dao.UserLyricsDao;
import com.bruce.manager.UserLyricsManager;
import com.bruce.model.UserLyrics;
import com.bruce.query.condition.UserLyricsQueryCondition;
import com.bruce.utils.MyConstant;
import com.bruce.utils.PageView;
import com.bruce.utils.QueryResult;

@Service("UserLyricsManager")
public class UserLyricsManagerImpl implements UserLyricsManager {

    @Autowired
	private UserLyricsDao userlyricsDao;

	@Override
	public UserLyrics findUserLyrics(String id) {
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
	public boolean delUserLyrics(String id) {
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
		QueryResult qrs = userlyricsDao.findByCondition(p.getStartindex(),
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
	public PageView<List<Map<String, Object>>> getMixMapData(String currentpage) {
		// TODO Auto-generated method stub
		//,(select count(*) from bc_lyrics_zan  d    where a.id =d.lyricsid )as zan ,  (select count(*) from bc_lyrics_comment e where a.id =e.lyricsid )as pl
		String sql = "select a.*,c.id as userid,c.username,c.email  from bc_lyrics a join bc_user_lyrics b on a.id = b.lyricsid join bc_user c on c.id = b.userid ";
		/*if (!StringUtils.isEmpty(wheresql)) {
			sql += wheresql;
		}*/
		sql+=" order by a.updatedate desc";
		
		//设置pageview参数
		PageView<List<Map<String, Object>>> pageview=new PageView<List<Map<String, Object>>>();
		int pages = 0; // 总页数
		int n =  userlyricsDao.querySql(sql).size();
		int maxresult = pageview.getMaxresult();
		/** 每页显示记录数 **/
		if (n % maxresult == 0) {
			pages = n / maxresult;
		} else {
			pages = n / maxresult + 1;
		}
		if (StringUtils.isEmpty(currentpage)) {
			currentpage = "0";
		} else {
            try {//胡乱把currentpage写成英文，捕捉异常
            	if (Integer.parseInt(currentpage) >= pages) {
    				currentpage = pages - 1 +"";
    			}
    			if (Integer.parseInt(currentpage) < 0) {
    				currentpage = "0";
    			}
			} catch (Exception e) {
				// TODO: handle exception
				currentpage = "0";
			}
			
		}
		int startindex = Integer.parseInt(currentpage) * maxresult;
		int endindex = startindex + maxresult - 1;
		pageview.setStartindex(startindex);
		pageview.setEndindex(endindex);
		pageview.setTotalrecord(n);
		pageview.setCurrentpage(Integer.parseInt(currentpage));
		pageview.setTotalpage(pages);
		pageview.setMaxresult(maxresult);
		//设置pageview参数 end
		
		
		pageview = userlyricsDao.QuerySQLForMapList(sql, pageview);
		
		return pageview;
		
		
	}

}

