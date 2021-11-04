
package cn.northpark.manager;

import cn.northpark.model.User;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface UserManager {


     User findUser(Integer id);

     List<User> findAll();

     void addUser(User user);

     boolean delUser(Integer id);

     boolean updateUser(User user);

     QueryResult<User> findByCondition(PageView<User> p,
                                             String wheresql, LinkedHashMap<String, String> order);

     QueryResult<User> findByCondition(
            String wheresql);

     User login(String email, String password, String ipAndDetail);

     void pwddd(String sql);

    /**
     *
     * @return
     */
     List<User> querySql(String sql, Object... param);


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
     * 根绝sql 预查寻返回list<map
     *
     * @param sql
     * @param objects
     * @return
     */
     List<Map<String, Object>> querySqlMap(String sql, Object... objects);

}


