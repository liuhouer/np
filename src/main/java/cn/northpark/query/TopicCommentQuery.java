
package cn.northpark.query;

import cn.northpark.query.condition.TopicCommentQueryCondition;
/**
 * @author bruce
 * @date 2020-11-29
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
 
public interface TopicCommentQuery {
	String getSql(TopicCommentQueryCondition topiccommentQueryCondition);
	
	String getMixSql(TopicCommentQueryCondition condition);
}
