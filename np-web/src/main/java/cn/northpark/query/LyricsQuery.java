
package cn.northpark.query;

import cn.northpark.query.condition.LyricsQueryCondition;

public interface LyricsQuery {
    String getSql(LyricsQueryCondition lyricsQueryCondition);
}
