
package cn.northpark.dao.impl;

import cn.northpark.dao.UserDao;
import cn.northpark.model.User;
import cn.northpark.utils.JsonUtil;
import cn.northpark.utils.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;

@Service("UserDao")
@Slf4j
public class UserDaoImpl extends HibernateDaoImpl<User, Serializable> implements UserDao {

    public User login(String email, String password ) {
        // TODO Auto-generated method stub
        String sql = "select * from bc_user where email=?  and password = ? and (is_del is null or is_del!=1)";
        List<User> list = querySql(sql, User.class, new Object[]{email, password});
        User user = null;
        if(!CollectionUtils.isEmpty(list)) {
            user = list.get(0);
        }else {
        	log.error("错误的登录尝试----->："+JsonUtil.object2json(TimeUtils.nowTime())+"---"+email);
        } 
        
        return user;
    }

}