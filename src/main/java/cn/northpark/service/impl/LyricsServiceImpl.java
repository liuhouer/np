
package cn.northpark.service.impl;

import cn.northpark.mapper.LyricsMapper;
import cn.northpark.model.Lyrics;
import cn.northpark.service.LyricsService;
import cn.northpark.utils.page.PageView;
import cn.northpark.utils.page.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class LyricsServiceImpl implements LyricsService {

    @Autowired
    LyricsMapper lyricsMapper;

    @Override
    public Lyrics findLyrics(Integer id) {
        return lyricsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Lyrics> findAll() {
        return lyricsMapper.selectAll();
    }

    @Override
    public void addLyrics(Lyrics lyrics) {
        lyricsMapper.insert(lyrics);
    }

    @Override
    public boolean delLyrics(Integer id) {
        lyricsMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateLyrics(Lyrics lyrics) {
        lyricsMapper.updateByPrimaryKey(lyrics);
        return true;
    }

    @Override
    public List<Map<String, Object>> querySqlMap(String sql) {
        return lyricsMapper.querySqlMap(sql);
    }

}

