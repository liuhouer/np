
package cn.northpark.query;
import cn.northpark.query.condition.UserprofileQueryCondition;

public interface UserprofileQuery {
	public String getSql(UserprofileQueryCondition userprofileQueryCondition);
}
