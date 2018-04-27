
package cn.northpark.query;

import cn.northpark.query.condition.UserQueryCondition;

public interface UserQuery {
    public String getSql(UserQueryCondition userQueryCondition);
}
