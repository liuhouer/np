
package cn.northpark.service.impl;

import cn.northpark.mapper.PoemEnjoyMapper;
import cn.northpark.model.PoemEnjoy;
import cn.northpark.service.PoemEnjoyService;
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
public class PoemEnjoyServiceImpl implements PoemEnjoyService {

    @Autowired
    PoemEnjoyMapper poemenjoyMapper;

    @Override
    public PoemEnjoy findPoemEnjoy(Integer id) {
        return poemenjoyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PoemEnjoy> findAll() {
        return poemenjoyMapper.selectAll();
    }

    @Override
    public void addPoemEnjoy(PoemEnjoy poemenjoy) {
        poemenjoyMapper.insert(poemenjoy);
    }

    @Override
    public boolean delPoemEnjoy(Integer id) {
        poemenjoyMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updatePoemEnjoy(PoemEnjoy poemenjoy) {
        poemenjoyMapper.updateByPrimaryKey(poemenjoy);
        return true;
    }

}

