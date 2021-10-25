
package cn.northpark.query.impl;

import cn.northpark.query.KnowledgeQuery;
import cn.northpark.query.condition.KnowledgeQueryCondition;
import org.springframework.stereotype.Service;

/**
 * @author bruce
 * @date 2021-10-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
 
@Service("KnowledgeQuery")
public class KnowledgeQueryImpl implements KnowledgeQuery {

	@Override
	public String getSql(KnowledgeQueryCondition knowledgeQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}
	
	@Override
	public String getMixSql(KnowledgeQueryCondition condition) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



