
package cn.northpark.query.impl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.northpark.query.UserLyricsQuery;
import cn.northpark.query.condition.UserLyricsQueryCondition;
@Service("UserLyricsQuery")

public class UserLyricsQueryImpl implements UserLyricsQuery {

	@Override
	public String getSql(UserLyricsQueryCondition userlyricsQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        if(StringUtils.isNotEmpty(userlyricsQueryCondition.getUserid())&&!userlyricsQueryCondition.getUserid().equals("null")){
        	sql.append(" and userid = '");
			sql.append(userlyricsQueryCondition.getUserid());
			sql.append("' ");
        }
		
		return sql.toString();
	}

}



