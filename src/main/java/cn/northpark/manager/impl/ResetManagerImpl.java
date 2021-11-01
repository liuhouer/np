
package cn.northpark.manager.impl;

import cn.northpark.dao.ResetDao;
import cn.northpark.manager.ResetManager;
import cn.northpark.model.Reset;
import cn.northpark.utils.page.MyConstant;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service("ResetManager")
public class ResetManagerImpl implements ResetManager {

    @Autowired
    private ResetDao resetDao;

    @Override
    public Reset findReset(Integer id) {
        return resetDao.find(id);
    }

    @Override
    public List<Reset> findAll() {
        return resetDao.findAll();
    }

    @Override
    public void addReset(Reset reset) {
        resetDao.save(reset);
    }

    @Override
    public boolean delReset(Integer id) {
        Reset reset = resetDao.find(id);
        resetDao.delete(reset);
        return true;
    }

    @Override
    public boolean updateReset(Reset reset) {
        resetDao.update(reset);
        return true;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Reset> findByCondition(PageView<Reset> p,
                                              String wheresql, LinkedHashMap<String, String> order) {
        QueryResult qrs = resetDao.findByCondition(p.getFirstResult(),
                MyConstant.MAXRESULT, wheresql, order);
        return qrs;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public QueryResult<Reset> findByCondition(String wheresql) {
        // TODO Auto-generated method stub
        QueryResult qrs = resetDao.findByCondition(
                wheresql);
        return qrs;
    }
}

