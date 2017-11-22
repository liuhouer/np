
package cn.northpark.manager.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.northpark.dao.PoemEnjoyDao;
import cn.northpark.manager.PoemEnjoyManager;
import cn.northpark.model.PoemEnjoy;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
@Service("PoemEnjoyManager")
public class PoemEnjoyManagerImpl implements PoemEnjoyManager {

    @Autowired
	private PoemEnjoyDao poemenjoyDao;

	@Override
	public PoemEnjoy findPoemEnjoy(Integer id) {
		return poemenjoyDao.find(id);
	}

	@Override
	public List<PoemEnjoy> findAll() {
		return poemenjoyDao.findAll();
	}

	@Override
	public void addPoemEnjoy(PoemEnjoy poemenjoy) {
		poemenjoyDao.save(poemenjoy);
	}

	@Override
	public boolean delPoemEnjoy(Integer id) {
		PoemEnjoy poemenjoy = poemenjoyDao.find(id);
		poemenjoyDao.delete(poemenjoy);
		return true;
	}

	@Override
	public boolean updatePoemEnjoy(PoemEnjoy poemenjoy) {
		poemenjoyDao.update(poemenjoy);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<PoemEnjoy> findByCondition(PageView<PoemEnjoy> p,
			String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = poemenjoyDao.findByCondition(p.getFirstResult(),
				p.getMaxresult(), wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<PoemEnjoy> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = poemenjoyDao.findByCondition(
				 wheresql);
		return qrs;
	}
	
	@Override
	public List<PoemEnjoy> querySql(String sql, Object... obj) {
		// TODO Auto-generated method stub
		return poemenjoyDao.querySql(sql, PoemEnjoy.class, obj);
	}

	@Override
	public List<PoemEnjoy> querySql(String sql) {
		// TODO Auto-generated method stub
		return poemenjoyDao.querySql(sql, PoemEnjoy.class);
	}

	@Override
	public List<Map<String, Object>> querySqlMap(String sql,PageView<List<Map<String,Object>>> pageView) {
		// TODO Auto-generated method stub
		return poemenjoyDao.QuerySQLForMapList(sql, pageView);
	}
	
	/* (non-Javadoc)
	 * 根据sql查询条数
	 */
	@Override
	public int countSql(String sql) {
		// TODO Auto-generated method stub
		return poemenjoyDao.countSql(sql);
	}

	/* (non-Javadoc)
	 * 根据hql查询条数
	 */
	@Override
	public int countHql(String wheresql) {
		// TODO Auto-generated method stub
		return poemenjoyDao.countHql(PoemEnjoy.class, wheresql);
	}
}

