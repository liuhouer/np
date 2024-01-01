
package cn.northpark.service.impl;

import cn.northpark.mapper.AstroMapper;
import cn.northpark.model.Astro;
import cn.northpark.service.AstroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bruce
 * @date 2016-12-01
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Service
public class AstroServiceImpl implements AstroService {

    @Autowired
    AstroMapper astroMapper;

    @Override
    public Astro findAstro(Integer id) {
        return astroMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Astro> findAll() {
        return astroMapper.selectAll();
    }

    @Override
    public void addAstro(Astro astro) {
        astroMapper.insert(astro);
    }

    @Override
    public boolean delAstro(Integer id) {
        astroMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateAstro(Astro astro) {
        astroMapper.updateByPrimaryKey(astro);
        return true;
    }

}

