
package cn.northpark.query;

import cn.northpark.query.condition.KnowledgeQueryCondition;
/**
 * @author bruce
 * @date 2021-10-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
 
public interface KnowledgeQuery {
	String getSql(KnowledgeQueryCondition knowledgeQueryCondition);
	
	String getMixSql(KnowledgeQueryCondition condition);
}
