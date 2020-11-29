
package cn.northpark.query.impl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import cn.northpark.query.TopicCommentQuery;
import cn.northpark.query.condition.TopicCommentQueryCondition;

/**
 * @author bruce
 * @date 2020-11-29
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
 
@Service("TopicCommentQuery")
public class TopicCommentQueryImpl implements TopicCommentQuery {

	@Override
	public String getSql(TopicCommentQueryCondition topiccommentQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}
	
	@Override
	public String getMixSql(TopicCommentQueryCondition condition) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



