
package cn.northpark.query.impl;

import cn.northpark.query.LyricsQuery;
import cn.northpark.query.condition.LyricsQueryCondition;
import org.springframework.stereotype.Service;

@Service("LyricsQuery")

public class LyricsQueryImpl implements LyricsQuery {

    @Override
    public String getSql(LyricsQueryCondition lyricsQueryCondition) {
        StringBuilder sql = new StringBuilder(" where 1=1");


        return sql.toString();
    }

}



