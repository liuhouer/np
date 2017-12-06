
package cn.northpark.query;
import cn.northpark.query.condition.VpsQueryCondition;
/**
 * @author bruce
 * @date 2017-12-06
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
 
public interface VpsQuery {
	public String getSql(VpsQueryCondition vpsQueryCondition);

	public String getMixSql(VpsQueryCondition condition);
}
