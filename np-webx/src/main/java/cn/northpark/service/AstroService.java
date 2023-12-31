
package cn.northpark.service;

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
public interface AstroService {

     Astro findAstro(Integer id);

     List<Astro> findAll();

     void addAstro(Astro astro);

     boolean delAstro(Integer id);

     boolean updateAstro(Astro astro);

     QueryResult<Astro> findByCondition(PageView<Astro> p,
                                              String wheresql, LinkedHashMap<String, String> order);

     QueryResult<Astro> findByCondition(
            String wheresql);

    /**
     * sql+
     *
     * @return
     */
     List<Astro> querySql(String sql, Object... obj);

    /**
     * sql
     *
     * @return
     */
     List<Astro> querySql(String sql);




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
     int countHql(Astro m, String wheresql);


}


