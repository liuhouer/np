
package com.bruce.query;
import com.bruce.model.Eq;
import com.bruce.query.condition.EqQueryCondition;
/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmial.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
 
public interface EqQuery {
	public String getSql(EqQueryCondition eqQueryCondition);
}
