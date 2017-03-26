
package cn.northpark.query.impl;
import org.springframework.stereotype.Service;

import cn.northpark.query.PoemQuery;
import cn.northpark.query.condition.PoemQueryCondition;

/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
 
@Service("PoemQuery")
public class PoemQueryImpl implements PoemQuery {

	@Override
	public String getSql(PoemQueryCondition poemQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



