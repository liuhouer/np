
package cn.northpark.query;
import cn.northpark.query.condition.ResetQueryCondition;

public interface ResetQuery {
	public String getSql(ResetQueryCondition resetQueryCondition);
}
