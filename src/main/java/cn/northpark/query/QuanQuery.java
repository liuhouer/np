
package cn.northpark.query;
import cn.northpark.model.Quan;
import cn.northpark.query.condition.QuanQueryCondition;

public interface QuanQuery {
	public String getSql(QuanQueryCondition quanQueryCondition);
}
