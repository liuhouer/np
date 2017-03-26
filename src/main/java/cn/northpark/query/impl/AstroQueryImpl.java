
package cn.northpark.query.impl;
import org.springframework.stereotype.Service;

import cn.northpark.query.AstroQuery;
import cn.northpark.query.condition.AstroQueryCondition;

/**
 * @author bruce
 * @date 2016-12-01
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
 
@Service("AstroQuery")
public class AstroQueryImpl implements AstroQuery {

	@Override
	public String getSql(AstroQueryCondition astroQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



