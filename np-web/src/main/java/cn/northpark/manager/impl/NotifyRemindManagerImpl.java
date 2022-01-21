
package cn.northpark.manager.impl;

import cn.northpark.dao.NotifyRemindDao;
import cn.northpark.manager.NotifyRemindManager;
import cn.northpark.model.NotifyRemind;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date 2021-11-02
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
@Service("NotifyRemindManager")
public class NotifyRemindManagerImpl implements NotifyRemindManager {

    @Autowired
	private NotifyRemindDao notifyremindDao;

	@Override
	public NotifyRemind findNotifyRemind(Integer id) {
		return notifyremindDao.find(id);
	}

	@Override
	public List<NotifyRemind> findAll() {
		return notifyremindDao.findAll();
	}

	@Override
	@Transactional
	public void addNotifyRemind(NotifyRemind notifyremind) {
		notifyremindDao.save(notifyremind);
	}

	@Override
	public boolean delNotifyRemind(Integer id) {
		NotifyRemind notifyremind = notifyremindDao.find(id);
		notifyremindDao.delete(notifyremind);
		return true;
	}

	@Override
	public boolean updateNotifyRemind(NotifyRemind notifyremind) {
		notifyremindDao.update(notifyremind);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<NotifyRemind> findByCondition(PageView<NotifyRemind> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = notifyremindDao.findByCondition(p.getFirstResult(),
				p.getMaxResult(), wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<NotifyRemind> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = notifyremindDao.findByCondition(
				 wheresql);
		return qrs;
	}
	
	@Override
	public List<NotifyRemind> querySql(String sql, Object... obj) {
		// TODO Auto-generated method stub
		return notifyremindDao.querySql(sql, NotifyRemind.class, obj);
	}

	@Override
	public List<NotifyRemind> querySql(String sql) {
		// TODO Auto-generated method stub
		return notifyremindDao.querySql(sql, NotifyRemind.class);
	}
	
	
	@Override
	public List<Map<String, Object>> querySqlMap(String sql) {
		// TODO Auto-generated method stub
		return notifyremindDao.querySql(sql);
	}

	
	/* (non-Javadoc)
	 * 根据sql查询条数
	 */
	@Override
	public int countSql(String sql) {
		// TODO Auto-generated method stub
		return notifyremindDao.countSql(sql);
	}

	/* (non-Javadoc)
	 * 根据hql查询条数
	 */
	@Override
	public int countHql(String wheresql) {
		// TODO Auto-generated method stub
		return notifyremindDao.countHql(NotifyRemind.class, wheresql);
	}
	
	
	@Override
	public void executeSql(String sql) {
		// TODO Auto-generated method stub
		 notifyremindDao.execSQL(sql);
	}

	@Override
	public void executeSql(String sql, Object... obj) {
		notifyremindDao.execSQL(sql,obj);
	}

	@Override
	public List<Map<String, Object>> findmixByCondition(PageView<List<Map<String,Object>>> pageView,String sql) {
		
		
		List<Map<String, Object>> list = notifyremindDao.querySQLForMapList(sql, pageView);
		
		return list;
		
	}
	
	
	
	@Override
	public PageView<List<Map<String, Object>>> getMixMapPage(PageView<List<Map<String, Object>>> pageView, String sql) {
		
		return notifyremindDao.querySQLCountForMapList(sql, pageView);
	}
	
}

