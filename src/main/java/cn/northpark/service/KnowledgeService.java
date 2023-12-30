
package cn.northpark.service;

import cn.northpark.model.Knowledge;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

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
public interface KnowledgeService{
	
	Knowledge findKnowledge(Integer id);

	List<Knowledge> findAll();

	void addKnowledge(Knowledge knowledge);

	boolean delKnowledge(Integer id);

	boolean updateKnowledge(Knowledge knowledge);

	List<Knowledge> findByCondition(String whereSql, String orderBy);

	List<Map<String, Object>> querySqlMap(String hot_sql);
}


