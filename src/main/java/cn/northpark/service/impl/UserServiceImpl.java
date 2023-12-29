
package cn.northpark.service.impl;

import cn.northpark.mapper.UserMapper;
import cn.northpark.model.User;
import cn.northpark.service.UserService;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public boolean delUser(Integer id) {
        userMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        userMapper.updateByPrimaryKey(user);
        return true;
    }

//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<User> findByCondition(PageView<User> p,
//                                             String wheresql, LinkedHashMap<String, String> order) {
//        QueryResult qrs = userMapper.findByCondition(p.getFirstResult(),
//                MyConstant.MAX_RESULT, wheresql, order);
//        return qrs;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<User> findByCondition(String wheresql) {
//        // TODO Auto-generated method stub
//        QueryResult qrs = userMapper.findByCondition(
//                wheresql);
//        return qrs;
//    }
//
//    @Override
//    public User login(String email, String password) {
//        // TODO Auto-generated method stub
//        return userMapper.login(email, password);
//    }
//
//    @Override
//    public void pwddd(String sql) {
//        // TODO Auto-generated method stub
//        userMapper.execSQL(sql);
//    }
//
//    @Override
//    public List<User> querySql(String sql, Object... param) {
//        // TODO Auto-generated method stub
//        return userMapper.querySql(sql, User.class, param) ;
//    }
//
//    /* (non-Javadoc)
//     * @see cn.northpark.manager.UserManager#countSql(java.lang.String)
//     */
//    @Override
//    public int countSql(String sql) {
//        // TODO Auto-generated method stub
//
//        return userMapper.countSql(sql);
//    }
//
//    /* (non-Javadoc)
//     * @see cn.northpark.manager.UserManager#countHql(cn.northpark.model.User, java.lang.String)
//     */
//    @Override
//    public int countHql(String wheresql) {
//        // TODO Auto-generated method stub
//        return userMapper.countHql(User.class, wheresql);
//    }
//
//    @Override
//    public List<Map<String, Object>> querySqlMap(String sql, Object... objects) {
//        // TODO Auto-generated method stub
//        return userMapper.querySql(sql, objects);
//    }


    @Override
    public QueryResult<User> findByCondition(PageView<User> p, String wheresql, LinkedHashMap<String, String> order) {
        return null;
    }

    @Override
    public QueryResult<User> findByCondition(String wheresql) {
        return null;
    }

    @Override
    public User login(String email, String password) {
        return null;
    }

    @Override
    public void pwddd(String sql) {

    }

    @Override
    public List<User> querySql(String sql, Object... param) {
        return null;
    }

    @Override
    public int countSql(String sql) {
        return 0;
    }

    @Override
    public int countHql(String wheresql) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> querySqlMap(String sql, Object... objects) {
        return null;
    }
}

