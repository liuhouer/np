
package cn.northpark.service.impl;

import cn.northpark.mapper.ResetMapper;
import cn.northpark.model.Reset;
import cn.northpark.service.ResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResetServiceImpl implements ResetService {

    @Autowired
    ResetMapper resetMapper;

    @Override
    public Reset findReset(Integer id) {
        return resetMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Reset> findAll() {
        return resetMapper.selectAll();
    }

    @Override
    public void addReset(Reset reset) {
        resetMapper.insert(reset);
    }

    @Override
    public boolean delReset(Integer id) {
        resetMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateReset(Reset reset) {
        resetMapper.updateByPrimaryKey(reset);
        return true;
    }

    @Override
    public List<Reset> findByCondition(String whereSql, String orderBy) {
        return resetMapper.findByCondition(whereSql,orderBy);
    }

}

