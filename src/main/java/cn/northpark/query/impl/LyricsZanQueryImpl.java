
package cn.northpark.query.impl;

import org.springframework.stereotype.Service;

import cn.northpark.query.LyricsZanQuery;
import cn.northpark.query.condition.LyricsZanQueryCondition;

@Service("LyricsZanQuery")

public class LyricsZanQueryImpl implements LyricsZanQuery {

    @Override
    public String getSql(LyricsZanQueryCondition lyricszanQueryCondition) {
        StringBuilder sql = new StringBuilder(" where 1=1");


        return sql.toString();
    }

}



