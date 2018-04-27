
package cn.northpark.query;

import cn.northpark.query.condition.MoviesQueryCondition;

public interface MoviesQuery {
    public String getSql(MoviesQueryCondition moviesQueryCondition);
}
