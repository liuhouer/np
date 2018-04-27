
package cn.northpark.query;

import cn.northpark.query.condition.LyricsCommentQueryCondition;

public interface LyricsCommentQuery {
    public String getSql(LyricsCommentQueryCondition lyricscommentQueryCondition);
}
