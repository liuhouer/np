
package cn.northpark.manager;

import cn.northpark.model.Reset;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;

public interface ResetManager {

    public Reset findReset(Integer id);

    public List<Reset> findAll();

    public void addReset(Reset reset);

    public boolean delReset(Integer id);

    public boolean updateReset(Reset reset);

    public QueryResult<Reset> findByCondition(PageView<Reset> p,
                                              String wheresql, LinkedHashMap<String, String> order);

    public QueryResult<Reset> findByCondition(
            String wheresql);

}


