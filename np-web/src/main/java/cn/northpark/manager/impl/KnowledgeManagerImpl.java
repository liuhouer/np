
package cn.northpark.manager.impl;

import cn.northpark.dao.KnowledgeDao;
import cn.northpark.manager.KnowledgeManager;
import cn.northpark.model.Knowledge;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date 2021-10-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
@Service("KnowledgeManager")
public class KnowledgeManagerImpl implements KnowledgeManager {

    @Autowired
	private KnowledgeDao knowledgeDao;

	@Override
	public Knowledge findKnowledge(Integer id) {
		return knowledgeDao.find(id);
	}

	@Override
	public List<Knowledge> findAll() {
		return knowledgeDao.findAll();
	}

	@Override
	public void addKnowledge(Knowledge knowledge) {
		knowledgeDao.save(knowledge);
	}

	@Override
	public boolean delKnowledge(Integer id) {
		Knowledge knowledge = knowledgeDao.find(id);
		knowledgeDao.delete(knowledge);
		return true;
	}

	@Override
	public boolean updateKnowledge(Knowledge knowledge) {
		knowledgeDao.update(knowledge);
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Knowledge> findByCondition(PageView<Knowledge> p,
												  String wheresql, LinkedHashMap<String, String> order) {
		QueryResult qrs = knowledgeDao.findByCondition(p.getFirstResult(),
				p.getMaxResult(), wheresql, order);
		return qrs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public QueryResult<Knowledge> findByCondition(String wheresql) {
		// TODO Auto-generated method stub
		QueryResult qrs = knowledgeDao.findByCondition(
				 wheresql);
		return qrs;
	}
	
	@Override
	public List<Knowledge> querySql(String sql, Object... obj) {
		// TODO Auto-generated method stub
		return knowledgeDao.querySql(sql, Knowledge.class, obj);
	}

	@Override
	public List<Knowledge> querySql(String sql) {
		// TODO Auto-generated method stub
		return knowledgeDao.querySql(sql, Knowledge.class);
	}
	
	
	@Override
	public List<Map<String, Object>> querySqlMap(String sql) {
		// TODO Auto-generated method stub
		return knowledgeDao.querySql(sql);
	}

	
	/* (non-Javadoc)
	 * 根据sql查询条数
	 */
	@Override
	public int countSql(String sql) {
		// TODO Auto-generated method stub
		return knowledgeDao.countSql(sql);
	}

	/* (non-Javadoc)
	 * 根据hql查询条数
	 */
	@Override
	public int countHql(String wheresql) {
		// TODO Auto-generated method stub
		return knowledgeDao.countHql(Knowledge.class, wheresql);
	}
	
	
	@Override
	public void executeSql(String sql) {
		// TODO Auto-generated method stub
		 knowledgeDao.execSQL(sql);
	}
	
	@Override
	public List<Map<String, Object>> findmixByCondition(PageView<List<Map<String,Object>>> pageView,String sql) {
		
		
		List<Map<String, Object>> list = knowledgeDao.querySQLForMapList(sql, pageView);
		
		return list;
		
	}
	
	
	
	@Override
	public PageView<List<Map<String, Object>>> getMixMapPage(PageView<List<Map<String, Object>>> pageView, String sql) {
		
		return knowledgeDao.querySQLCountForMapList(sql, pageView);
	}
	
}

