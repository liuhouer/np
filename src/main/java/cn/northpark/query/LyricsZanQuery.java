
package cn.northpark.query;

import cn.northpark.query.condition.LyricsZanQueryCondition;

public interface LyricsZanQuery {
    public String getSql(LyricsZanQueryCondition lyricszanQueryCondition);
}
