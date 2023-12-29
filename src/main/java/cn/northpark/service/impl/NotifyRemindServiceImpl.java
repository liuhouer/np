
package cn.northpark.service.impl;

import cn.northpark.mapper.NotifyRemindMapper;
import cn.northpark.model.NotifyRemind;
import cn.northpark.service.NotifyRemindService;
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
@Service
public class NotifyRemindServiceImpl implements NotifyRemindService {

    @Autowired
	private NotifyRemindMapper notifyremindMapper;

	@Override
	public NotifyRemind findNotifyRemind(Integer id) {
		return notifyremindMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<NotifyRemind> findAll() {
		return notifyremindMapper.selectAll();
	}

	@Override
	@Transactional
	public void addNotifyRemind(NotifyRemind notifyremind) {
		notifyremindMapper.insert(notifyremind);
	}

	@Override
	public boolean delNotifyRemind(Integer id) {
		notifyremindMapper.deleteByPrimaryKey(id);
		return true;
	}

	@Override
	public boolean updateNotifyRemind(NotifyRemind notifyremind) {
		notifyremindMapper.updateByPrimaryKey(notifyremind);
		return true;
	}
	
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Override
//	public QueryResult<NotifyRemind> findByCondition(PageView<NotifyRemind> p,
//			String wheresql, LinkedHashMap<String, String> order) {
//		QueryResult qrs = notifyremindMapper.findByCondition(p.getFirstResult(),
//				p.getMaxResult(), wheresql, order);
//		return qrs;
//	}
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Override
//	public QueryResult<NotifyRemind> findByCondition(String wheresql) {
//		// TODO Auto-generated method stub
//		QueryResult qrs = notifyremindMapper.findByCondition(
//				 wheresql);
//		return qrs;
//	}
//
//	@Override
//	public List<NotifyRemind> querySql(String sql, Object... obj) {
//		// TODO Auto-generated method stub
//		return notifyremindMapper.querySql(sql, NotifyRemind.class, obj);
//	}
//
//	@Override
//	public List<NotifyRemind> querySql(String sql) {
//		// TODO Auto-generated method stub
//		return notifyremindMapper.querySql(sql, NotifyRemind.class);
//	}
//
//
//	@Override
//	public List<Map<String, Object>> querySqlMap(String sql) {
//		// TODO Auto-generated method stub
//		return notifyremindMapper.querySql(sql);
//	}
//
//
//	/* (non-Javadoc)
//	 * 根据sql查询条数
//	 */
//	@Override
//	public int countSql(String sql) {
//		// TODO Auto-generated method stub
//		return notifyremindMapper.countSql(sql);
//	}
//
//	/* (non-Javadoc)
//	 * 根据hql查询条数
//	 */
//	@Override
//	public int countHql(String wheresql) {
//		// TODO Auto-generated method stub
//		return notifyremindMapper.countHql(NotifyRemind.class, wheresql);
//	}
//
//
//	@Override
//	public void executeSql(String sql) {
//		// TODO Auto-generated method stub
//		 notifyremindMapper.execSQL(sql);
//	}
//
//	@Override
//	public void executeSql(String sql, Object... obj) {
//		notifyremindMapper.execSQL(sql,obj);
//	}
//
//	@Override
//	public List<Map<String, Object>> findmixByCondition(PageView<List<Map<String,Object>>> pageView,String sql) {
//
//
//		List<Map<String, Object>> list = notifyremindMapper.querySQLForMapList(sql, pageView);
//
//		return list;
//
//	}
//
//
//
//	@Override
//	public PageView<List<Map<String, Object>>> getMixMapPage(PageView<List<Map<String, Object>>> pageView, String sql) {
//
//		return notifyremindMapper.querySQLCountForMapList(sql, pageView);
//	}


	@Override
	public QueryResult<NotifyRemind> findByCondition(PageView<NotifyRemind> p, String wheresql, LinkedHashMap<String, String> order) {
		return null;
	}

	@Override
	public QueryResult<NotifyRemind> findByCondition(String wheresql) {
		return null;
	}

	@Override
	public List<NotifyRemind> querySql(String sql, Object... obj) {
		return null;
	}

	@Override
	public List<NotifyRemind> querySql(String sql) {
		return null;
	}

	@Override
	public List<Map<String, Object>> querySqlMap(String sql) {
		return null;
	}

	@Override
	public int countSql(String sql) {
		return 0;
	}

	@Override
	public int countHql(String whereSql) {
		return 0;
	}

	@Override
	public void executeSql(String sql) {

	}

	@Override
	public void executeSql(String sql, Object... obj) {

	}

	@Override
	public List<Map<String, Object>> findmixByCondition(PageView<List<Map<String, Object>>> pageView, String sql) {
		return null;
	}

	@Override
	public PageView<List<Map<String, Object>>> getMixMapPage(PageView<List<Map<String, Object>>> pageView, String sql) {
		return null;
	}
}

