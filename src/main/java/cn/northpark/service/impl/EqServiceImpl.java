
package cn.northpark.service.impl;

import cn.northpark.mapper.EqMapper;
import cn.northpark.model.Eq;
import cn.northpark.service.EqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Service
public class EqServiceImpl implements EqService {

    @Autowired
    private EqMapper eqMapper;

    @Override
    public Eq findEq(Integer id) {
        return eqMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Eq> findAll() {
        return eqMapper.selectAll();
    }

    @Override
    public void addEq(Eq eq) {
        eqMapper.insert(eq);
    }

    @Override
    public boolean delEq(Integer id) {
        eqMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateEq(Eq eq) {
        eqMapper.updateByPrimaryKey(eq);
        return true;
    }

    @Override
    public List<Eq> findByCondition(String whereSql, String orderBy) {
        return eqMapper.findByCondition( whereSql,  orderBy);
    }

}

