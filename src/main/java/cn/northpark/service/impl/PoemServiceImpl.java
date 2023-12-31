
package cn.northpark.service.impl;

import cn.northpark.mapper.PoemMapper;
import cn.northpark.model.Note;
import cn.northpark.model.Poem;
import cn.northpark.service.PoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Service
public class PoemServiceImpl implements PoemService {

    @Autowired
    PoemMapper poemMapper;

    @Override
    public Poem findPoem(Integer id) {
        return poemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Poem> findAll() {
        return poemMapper.selectAll();
    }

    @Override
    public void addPoem(Poem poem) {
        poemMapper.insert(poem);
    }

    @Override
    public boolean delPoem(Integer id) {
        poemMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updatePoem(Poem poem) {
        poemMapper.updateByPrimaryKey(poem);
        return true;
    }

    @Override
    public List<Poem> querySql(String sql) {
        return poemMapper.querySql(sql);
    }

    @Override
    public List<Poem> findByCondition(String whereSql, String orderBy) {
        return poemMapper.findByCondition(whereSql,orderBy);
    }

}

