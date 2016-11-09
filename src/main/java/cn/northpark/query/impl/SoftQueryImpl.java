
package cn.northpark.query.impl;
import org.springframework.stereotype.Service;

import cn.northpark.query.SoftQuery;
import cn.northpark.query.condition.SoftQueryCondition;

/**
 * @author bruce
 * @date 2016-11-09
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
 
@Service("SoftQuery")
public class SoftQueryImpl implements SoftQuery {

	@Override
	public String getSql(SoftQueryCondition softQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



