
package cn.northpark.query;

import cn.northpark.query.condition.UserLyricsQueryCondition;

public interface UserLyricsQuery {
    public String getSql(UserLyricsQueryCondition userlyricsQueryCondition);
}
