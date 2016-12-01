
package cn.northpark.query;
import cn.northpark.model.Astro;
import cn.northpark.query.condition.AstroQueryCondition;
/**
 * @author bruce
 * @date 2016-12-01
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
 
public interface AstroQuery {
	public String getSql(AstroQueryCondition astroQueryCondition);
}
