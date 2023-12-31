
package cn.northpark.service.impl;

import cn.northpark.mapper.KnowledgeMapper;
import cn.northpark.model.Knowledge;
import cn.northpark.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date 2021-10-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 *
 */
@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
	private KnowledgeMapper knowledgeMapper;

	@Override
	public Knowledge findKnowledge(Integer id) {
		return knowledgeMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Knowledge> findAll() {
		return knowledgeMapper.selectAll();
	}

	@Override
	public void addKnowledge(Knowledge knowledge) {
		knowledgeMapper.insert(knowledge);
	}

	@Override
	public boolean delKnowledge(Integer id) {
		knowledgeMapper.deleteByPrimaryKey(id);
		return true;
	}

	@Override
	public boolean updateKnowledge(Knowledge knowledge) {
		knowledgeMapper.updateByPrimaryKey(knowledge);
		return true;
	}

    @Override
    public List<Knowledge> findByCondition(String whereSql, String orderBy) {
        return knowledgeMapper.findByCondition(whereSql,orderBy);
    }

	@Override
	public List<Map<String, Object>> querySqlMap(String hot_sql) {
		return knowledgeMapper.querySqlMap( hot_sql) ;
	}


}

