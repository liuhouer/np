
package cn.northpark.service;

import cn.northpark.model.Soft;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date 2016-11-09
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public interface SoftService {

    Soft findSoft(Integer id);

    List<Soft> findAll();

    void addSoft(Soft soft);

    boolean delSoft(Integer id);

    boolean updateSoft(Soft soft);

    List<Soft> findByCondition(String whereSql, String orderBy);

    /**
     * sql
     *
     * @return
     */
    List<Map<String, Object>> querySqlMap(String sql);


}


