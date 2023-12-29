
package cn.northpark.service;

import cn.northpark.model.LyricsZan;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface LyricsZanService {

     LyricsZan findLyricsZan(Integer id);

     List<LyricsZan> findAll();

     void addLyricsZan(LyricsZan lyricszan);

     boolean delLyricsZan(Integer id);

     boolean updateLyricsZan(LyricsZan lyricszan);

     QueryResult<LyricsZan> findByCondition(PageView<LyricsZan> p,
                                                  String wheresql, LinkedHashMap<String, String> order);

     QueryResult<LyricsZan> findByCondition(
            String wheresql);


     int getCommentNumByLRC(String lyricsid);

     int getZanNumByLRC(String lyricsid);

     List<Map<String, Object>> mixSqlQuery(String sql,Object... objects );


    /**
     * 根据实体查询条数
     *
     * @param sql SQL语句
     * @return int
     */
     int countHql(String wheresql);
}


