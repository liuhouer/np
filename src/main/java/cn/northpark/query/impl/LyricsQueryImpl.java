
package cn.northpark.query.impl;

import org.springframework.stereotype.Service;

import cn.northpark.query.LyricsQuery;
import cn.northpark.query.condition.LyricsQueryCondition;

@Service("LyricsQuery")

public class LyricsQueryImpl implements LyricsQuery {

    @Override
    public String getSql(LyricsQueryCondition lyricsQueryCondition) {
        StringBuilder sql = new StringBuilder(" where 1=1");


        return sql.toString();
    }

}



