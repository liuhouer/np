
package cn.northpark.query;
import cn.northpark.query.condition.UserFollowQueryCondition;

public interface UserFollowQuery {
	public String getSql(UserFollowQueryCondition userfollowQueryCondition);
}
