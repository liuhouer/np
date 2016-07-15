
package cn.northpark.query;
import cn.northpark.model.Eq;
import cn.northpark.query.condition.EqQueryCondition;
/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
 
public interface EqQuery {
	public String getSql(EqQueryCondition eqQueryCondition);
}
