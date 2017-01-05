
package cn.northpark.query.impl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import cn.northpark.query.TagsQuery;
import cn.northpark.query.condition.TagsQueryCondition;

/**
 * @author bruce
 * @date 2017-01-05
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
 
@Service("TagsQuery")
public class TagsQueryImpl implements TagsQuery {

	@Override
	public String getSql(TagsQueryCondition tagsQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



