//
//package cn.northpark.service.impl;
//
//import cn.northpark.dao.KnowledgeMapper;
//import cn.northpark.manager.KnowledgeManager;
//import cn.northpark.model.Knowledge;
//import cn.northpark.utils.page.PageView;
//import cn.northpark.utils.page.QueryResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author bruce
// * @date 2021-10-25
// * @email zhangyang226@gmail.com
// * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
// *
// */
//@Service
//public class KnowledgeManagerImpl implements KnowledgeManager {
//
//    @Autowired
//	private KnowledgeMapper knowledgeMapper;
//
//	@Override
//	public Knowledge findKnowledge(Integer id) {
//		return knowledgeMapper.selectByPrimaryKey(id);
//	}
//
//	@Override
//	public List<Knowledge> findAll() {
//		return knowledgeMapper.selectAll();
//	}
//
//	@Override
//	public void addKnowledge(Knowledge knowledge) {
//		knowledgeMapper.insert(knowledge);
//	}
//
//	@Override
//	public boolean delKnowledge(Integer id) {
//		Knowledge knowledge = knowledgeMapper.selectByPrimaryKey(id);
//		knowledgeMapper.deleteByPrimaryKey(knowledge);
//		return true;
//	}
//
//	@Override
//	public boolean updateKnowledge(Knowledge knowledge) {
//		knowledgeMapper.updateByPrimaryKey(knowledge);
//		return true;
//	}
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Override
//	public QueryResult<Knowledge> findByCondition(PageView<Knowledge> p,
//												  String wheresql, LinkedHashMap<String, String> order) {
//		QueryResult qrs = knowledgeMapper.findByCondition(p.getFirstResult(),
//				p.getMaxResult(), wheresql, order);
//		return qrs;
//	}
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Override
//	public QueryResult<Knowledge> findByCondition(String wheresql) {
//		// TODO Auto-generated method stub
//		QueryResult qrs = knowledgeMapper.findByCondition(
//				 wheresql);
//		return qrs;
//	}
//
//	@Override
//	public List<Knowledge> querySql(String sql, Object... obj) {
//		// TODO Auto-generated method stub
//		return knowledgeMapper.querySql(sql, Knowledge.class, obj);
//	}
//
//	@Override
//	public List<Knowledge> querySql(String sql) {
//		// TODO Auto-generated method stub
//		return knowledgeMapper.querySql(sql, Knowledge.class);
//	}
//
//
//	@Override
//	public List<Map<String, Object>> querySqlMap(String sql) {
//		// TODO Auto-generated method stub
//		return knowledgeMapper.querySql(sql);
//	}
//
//
//	/* (non-Javadoc)
//	 * 根据sql查询条数
//	 */
//	@Override
//	public int countSql(String sql) {
//		// TODO Auto-generated method stub
//		return knowledgeMapper.countSql(sql);
//	}
//
//	/* (non-Javadoc)
//	 * 根据hql查询条数
//	 */
//	@Override
//	public int countHql(String wheresql) {
//		// TODO Auto-generated method stub
//		return knowledgeMapper.countHql(Knowledge.class, wheresql);
//	}
//
//
//	@Override
//	public void executeSql(String sql) {
//		// TODO Auto-generated method stub
//		 knowledgeMapper.execSQL(sql);
//	}
//
//	@Override
//	public List<Map<String, Object>> findmixByCondition(PageView<List<Map<String,Object>>> pageView,String sql) {
//
//
//		List<Map<String, Object>> list = knowledgeMapper.querySQLForMapList(sql, pageView);
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
//		return knowledgeMapper.querySQLCountForMapList(sql, pageView);
//	}
//
//}
//
