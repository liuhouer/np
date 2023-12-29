
package cn.northpark.service;

import cn.northpark.model.UserFollow;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface UserFollowService {

    UserFollow findUserFollow(Integer id);

    List<UserFollow> findAll();

    void addUserFollow(UserFollow userfollow);

    boolean delUserFollow(Integer id);

    boolean updateUserFollow(UserFollow userfollow);

    QueryResult<UserFollow> findByCondition(PageView<UserFollow> p,
                                            String wheresql, LinkedHashMap<String, String> order);

    QueryResult<UserFollow> findByCondition(
            String wheresql);

    List<Map<String, Object>> getFansList(String sql);

    /**
     * sql+
     *
     * @return
     */
    List<Map<String, Object>> querySql(String sql, Object... obj);

    /**
     * 根据wheresql计算条数
     *
     * @param string
     * @return
     */
    int getCountByCondition(String wheresql);
}


