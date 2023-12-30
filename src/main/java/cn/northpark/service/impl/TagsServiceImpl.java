
package cn.northpark.service.impl;

import cn.northpark.mapper.TagsMapper;
import cn.northpark.model.Tags;
import cn.northpark.service.TagsService;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date 2017-01-05
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsMapper tagsMapper;

    @Override
    public Tags findTags(Integer id) {
        return tagsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Tags> findAll() {
        return tagsMapper.selectAll();
    }

    @Override
    public void addTags(Tags tags) {
        tagsMapper.insert(tags);
    }

    @Override
    public boolean delTags(Integer id) {
        tagsMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateTags(Tags tags) {
        tagsMapper.updateByPrimaryKey(tags);
        return true;
    }

    @Override
    public List<Tags> findByCondition(String whereSql, String orderBy) {
        return tagsMapper.findByCondition( whereSql,  orderBy);
    }
}

