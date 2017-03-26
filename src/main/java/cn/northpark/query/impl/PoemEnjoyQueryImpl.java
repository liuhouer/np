
package cn.northpark.query.impl;
import org.springframework.stereotype.Service;

import cn.northpark.query.PoemEnjoyQuery;
import cn.northpark.query.condition.PoemEnjoyQueryCondition;

/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */
 
@Service("PoemEnjoyQuery")
public class PoemEnjoyQueryImpl implements PoemEnjoyQuery {

	@Override
	public String getSql(PoemEnjoyQueryCondition poemenjoyQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



