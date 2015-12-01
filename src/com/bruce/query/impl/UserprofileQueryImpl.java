
package com.bruce.query.impl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.bruce.query.UserprofileQuery;
import com.bruce.query.condition.UserprofileQueryCondition;
@Service("UserprofileQuery")

public class UserprofileQueryImpl implements UserprofileQuery {

	@Override
	public String getSql(UserprofileQueryCondition userprofileQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



