
package cn.northpark.manager;

import cn.northpark.model.Note;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface NoteManager {

     Note findNote(Integer id);

     List<Note> findAll();

     void addNote(Note note);

     boolean delNote(Integer id);

     boolean updateNote(Note note);

    /**
     * 单表返回clazz
     * 同步：返回分页数据和分页结构
     *
     * @param p
     * @param wheresql
     * @param order
     * @return
     */
     QueryResult<Note> findByCondition(PageView<Note> p,
                                             String wheresql, LinkedHashMap<String, String> order);

     QueryResult<Note> findByCondition(
            String wheresql);


    /**
     * 多表关联mix
     * <p>
     * 异步：根据页码获取当前页数据
     *
     * @param pageView
     * @param sql
     * @return
     */
     List<Map<String, Object>> findmixByCondition(PageView<List<Map<String, Object>>> pageView, String sql);

    /**
     * 多表关联mix
     * <p>
     * 同步：获取分页结构不获取数据
     *
     * @param pageView
     * @param sql
     * @return
     */
     PageView<List<Map<String, Object>>> getMixMapPage(PageView<List<Map<String, Object>>> pageView, String sql);


     int findmixCount(String whereSql);

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

    /**
     * sql
     *
     * @return
     */
     List<Note> querySql(String sql);


}


