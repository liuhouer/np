//
//package cn.northpark.service.impl;
//
//import cn.northpark.dao.UserFollowMapper;
//import cn.northpark.manager.UserFollowManager;
//import cn.northpark.model.UserFollow;
//import cn.northpark.utils.page.MyConstant;
//import cn.northpark.utils.page.PageView;
//import cn.northpark.utils.page.QueryResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class UserFollowManagerImpl implements UserFollowManager {
//
//    @Autowired
//    private UserFollowMapper userfollowMapper;
//
//    @Override
//    public UserFollow findUserFollow(Integer id) {
//        return userfollowMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public List<UserFollow> findAll() {
//        return userfollowMapper.selectAll();
//    }
//
//    @Override
//    public void addUserFollow(UserFollow userfollow) {
//        userfollowMapper.insert(userfollow);
//    }
//
//    @Override
//    public boolean delUserFollow(Integer id) {
//        UserFollow userfollow = userfollowMapper.selectByPrimaryKey(id);
//        userfollowMapper.deleteByPrimaryKey(userfollow);
//        return true;
//    }
//
//    @Override
//    public boolean updateUserFollow(UserFollow userfollow) {
//        userfollowMapper.updateByPrimaryKey(userfollow);
//        return true;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<UserFollow> findByCondition(PageView<UserFollow> p,
//                                                   String wheresql, LinkedHashMap<String, String> order) {
//        QueryResult qrs = userfollowMapper.findByCondition(p.getFirstResult(),
//                MyConstant.MAX_RESULT, wheresql, order);
//        return qrs;
//    }
//
//    @SuppressWarnings({"rawtypes", "unchecked"})
//    @Override
//    public QueryResult<UserFollow> findByCondition(String wheresql) {
//        // TODO Auto-generated method stub
//        QueryResult qrs = userfollowMapper.findByCondition(
//                wheresql);
//        return qrs;
//    }
//
//    @Override
//    public List<Map<String, Object>> getFansList(String sql) {
//        // TODO Auto-generated method stub
//        return userfollowMapper.querySql(sql);
//    }
//
//    @Override
//    public List<Map<String, Object>> querySql(String sql, Object... obj) {
//        // TODO Auto-generated method stub
//        return userfollowMapper.querySql(sql, obj);
//    }
//
//    @Override
//    public int getCountByCondition(String wheresql) {
//        // TODO Auto-generated method stub
//        return userfollowMapper.countHql(UserFollow.class, wheresql);
//    }
//}
//
