
package com.bruce.query;
import com.bruce.model.UidLink;
import com.bruce.query.condition.UidLinkQueryCondition;

public interface UidLinkQuery {
	public String getSql(UidLinkQueryCondition uidlinkQueryCondition);
}
