
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
import cn.northpark.utils.MyConstant;
import cn.northpark.utils.PageView;
import cn.northpark.utils.QueryResult;

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
	public PageView<List<Map<String, Object>>> getMixMapData(String currentpage,String userid) {
		String sql = " select a.id,a.title,a.artist,a.album,a.updatedate,a.albumImg,a.zan,a.pl,c.id as userid,c.username,c.email,  "
	               
				   + " case when  (select count(id) from bc_lyrics_zan d where d.lyricsid = a.id and d.userid = '"+userid+"' )> 0 "
	               +" then 'yizan' "
	               +" else '' "
	               +" end "
	               +" as yizan "
	
				   + "from bc_lyrics a join bc_user_lyrics b on a.id = b.lyricsid join bc_user c on c.id = b.userid ";

		       sql+=" order by a.updatedate desc";
		
		PageView<List<Map<String, Object>>> pageview = setPageviewParam(
				currentpage, userid,sql);
		pageview = userlyricsDao.QuerySQLForMapList(sql, pageview);
		return pageview;
		
		
	}

	/**
	 * @param currentpage
	 * @param userid
	 * @return
	 * @throws NumberFormatException
	 */
	private PageView<List<Map<String, Object>>> setPageviewParam(
			String currentpage, String userid,String sql) throws NumberFormatException {
		
		System.out.println(sql);
		//设置pageview参数
		PageView<List<Map<String, Object>>> pageview=new PageView<List<Map<String, Object>>>();
		
		int pages = 0; // 总页数
		//总条数
		int n = userlyricsDao.countSql(sql);
		
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
		PageView<List<Map<String, Object>>> pageview = setPageviewParam(
				currentpage, userid,sql);
		return pageview;
	}

}

