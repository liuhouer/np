
package cn.northpark.query;

import cn.northpark.query.condition.LyricsQueryCondition;

public interface LyricsQuery {
    public String getSql(LyricsQueryCondition lyricsQueryCondition);
}
