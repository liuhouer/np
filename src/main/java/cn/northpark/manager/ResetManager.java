
package cn.northpark.manager;

import cn.northpark.model.Reset;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;

public interface ResetManager {

     Reset findReset(Integer id);

     List<Reset> findAll();

     void addReset(Reset reset);

     boolean delReset(Integer id);

     boolean updateReset(Reset reset);

     QueryResult<Reset> findByCondition(PageView<Reset> p,
                                              String wheresql, LinkedHashMap<String, String> order);

     QueryResult<Reset> findByCondition(
            String wheresql);

}


