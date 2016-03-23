
package com.bruce.query.impl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.bruce.query.UidLinkQuery;
import com.bruce.query.condition.UidLinkQueryCondition;
@Service("UidLinkQuery")

public class UidLinkQueryImpl implements UidLinkQuery {

	@Override
	public String getSql(UidLinkQueryCondition uidlinkQueryCondition) {
		StringBuilder sql = new StringBuilder(" where 1=1");
		
        
		return sql.toString();
	}

}



