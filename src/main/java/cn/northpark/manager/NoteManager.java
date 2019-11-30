
package cn.northpark.manager;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.northpark.model.Note;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

public interface NoteManager {

    public Note findNote(Integer id);

    public List<Note> findAll();

    public void addNote(Note note);

    public boolean delNote(Integer id);

    public boolean updateNote(Note note);

    /**
     * 单表返回clazz
     * 同步：返回分页数据和分页结构
     *
     * @param p
     * @param wheresql
     * @param order
     * @return
     */
    public QueryResult<Note> findByCondition(PageView<Note> p,
                                             String wheresql, LinkedHashMap<String, String> order);

    public QueryResult<Note> findByCondition(
            String wheresql);


    /**
     * 多表关联mix
     * <p>
     * 异步：根据页码获取当前页数据
     *
     * @param pageview
     * @param sql
     * @return
     */
    public List<Map<String, Object>> findmixByCondition(PageView<List<Map<String, Object>>> pageview, String sql);

    /**
     * 多表关联mix
     * <p>
     * 同步：获取分页结构不获取数据
     *
     * @param pageview
     * @param sql
     * @return
     */
    public PageView<List<Map<String, Object>>> getMixMapPage(PageView<List<Map<String, Object>>> pageview, String sql);


    public int findmixCount(String whereSql);

    /**
     * 根据sql语句查询条数
     *
     * @param sql SQL语句
     * @return int
     */
    public int countSql(String sql);


    /**
     * 根据实体查询条数
     *
     * @param sql SQL语句
     * @return int
     */
    public int countHql(String wheresql);

    /**
     * sql
     *
     * @return
     */
    public List<Note> querySql(String sql);


}


