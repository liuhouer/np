
package cn.northpark.service.impl;

import cn.northpark.mapper.LyricsZanMapper;
import cn.northpark.model.LyricsZan;
import cn.northpark.service.LyricsZanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LyricsZanServiceImpl implements LyricsZanService {

    @Autowired
    LyricsZanMapper lyricszanMapper;

    @Override
    public LyricsZan findLyricsZan(Integer id) {
        return lyricszanMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<LyricsZan> findAll() {
        return lyricszanMapper.selectAll();
    }

    @Override
    public void addLyricsZan(LyricsZan lyricszan) {
        lyricszanMapper.insert(lyricszan);
    }

    @Override
    public boolean delLyricsZan(Integer id) {
        lyricszanMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateLyricsZan(LyricsZan lyricszan) {
        lyricszanMapper.updateByPrimaryKey(lyricszan);
        return true;
    }

}

