
package com.bruce.query;
import com.bruce.query.condition.MoviesQueryCondition;

public interface MoviesQuery {
	public String getSql(MoviesQueryCondition moviesQueryCondition);
}
