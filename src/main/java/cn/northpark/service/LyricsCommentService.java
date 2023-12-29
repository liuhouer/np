
package cn.northpark.service;

import cn.northpark.model.LyricsComment;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface LyricsCommentService {

     LyricsComment findLyricsComment(Integer id);

     List<LyricsComment> findAll();

     void addLyricsComment(LyricsComment lyricscomment);

     boolean delLyricsComment(Integer id);

     boolean updateLyricsComment(LyricsComment lyricscomment);

     QueryResult<LyricsComment> findByCondition(PageView<LyricsComment> p,
                                                      String wheresql, LinkedHashMap<String, String> order);

     QueryResult<LyricsComment> findByCondition(
            String wheresql);

    /**
     * 根据自己写的sql查询+条件查询
     *
     * @return
     */
     List<LyricsComment> querySql(String sql, Object... obj);

    /**
     * 根据自己写的sql查询
     *
     * @return
     */
     List<LyricsComment> querySql(String sql);


    /**
     * 根据自己写的sql查询返回map
     *
     * @return
     */
     List<Map<String, Object>> querySqlMap(String sql, PageView<List<Map<String, Object>>> pageView);


    /**
     * 根据sql语句查询条数
     *
     * @param sql SQL语句
     * @return int
     */
     int countSql(String sql);

    /**
     * 根据实体查询条数
     *
     * @param sql SQL语句
     * @return int
     */
     int countHql(String wheresql);


     List<Map<String, Object>> findmixByCondition(PageView<List<Map<String, Object>>> pageView, String sql);

    /**
     * 获取分页结构不获取数据
     *
     * @param pageView
     * @param userid
     * @return
     */
     PageView<List<Map<String, Object>>> getMixMapPage(PageView<List<Map<String, Object>>> pageView, String sql);

}


