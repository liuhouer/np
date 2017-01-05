
package cn.northpark.query;
import cn.northpark.model.Tags;
import cn.northpark.query.condition.TagsQueryCondition;
/**
 * @author bruce
 * @date 2017-01-05
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
 
public interface TagsQuery {
	public String getSql(TagsQueryCondition tagsQueryCondition);
}
