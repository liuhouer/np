
package cn.northpark.service.impl;

import cn.northpark.mapper.SoftMapper;
import cn.northpark.model.Soft;
import cn.northpark.service.SoftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author bruce
 * @date 2017-01-05
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Service
public class SoftServiceImpl implements SoftService {

    @Autowired
    SoftMapper softMapper;

    @Override
    public Soft findSoft(Integer id) {
        return softMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Soft> findAll() {
        return softMapper.selectAll();
    }

    @Override
    public void addSoft(Soft soft) {
        softMapper.insert(soft);
    }

    @Override
    public boolean delSoft(Integer id) {
        softMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateSoft(Soft soft) {
        softMapper.updateByPrimaryKey(soft);
        return true;
    }

    @Override
    public List<Soft> findByCondition(String whereSql, String orderBy) {
        return softMapper.findByCondition(whereSql,orderBy);
    }



    @Override
    public List<Map<String, Object>> querySqlMap(String sql) {
        return softMapper.querySqlMap(sql);
    }

}

