
package com.bruce.query.impl;
import org.springframework.stereotype.Service;

import com.bruce.query.EqQuery;
import com.bruce.query.condition.EqQueryCondition;

/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmial.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
 
@Service("EqQuery")
public class EqQueryImpl implements EqQuery {

	@Override
	public String getSql(EqQueryCondition eqQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



