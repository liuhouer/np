
package cn.northpark.manager;

import cn.northpark.model.Astro;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author bruce
 * @date 2016-12-01
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface AstroManager {

    public Astro findAstro(Integer id);

    public List<Astro> findAll();

    public void addAstro(Astro astro);

    public boolean delAstro(Integer id);

    public boolean updateAstro(Astro astro);

    public QueryResult<Astro> findByCondition(PageView<Astro> p,
                                              String wheresql, LinkedHashMap<String, String> order);

    public QueryResult<Astro> findByCondition(
            String wheresql);

    /**
     * sql+
     *
     * @return
     */
    public List<Astro> querySql(String sql, Object... obj);

    /**
     * sql
     *
     * @return
     */
    public List<Astro> querySql(String sql);




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
    public int countHql(Astro m, String wheresql);


}


