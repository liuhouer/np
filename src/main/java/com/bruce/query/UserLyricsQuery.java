
package com.bruce.query;
import com.bruce.query.condition.UserLyricsQueryCondition;

public interface UserLyricsQuery {
	public String getSql(UserLyricsQueryCondition userlyricsQueryCondition);
}
