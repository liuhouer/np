
package cn.northpark.service.impl;

import cn.northpark.mapper.LyricsCommentMapper;
import cn.northpark.model.LyricsComment;
import cn.northpark.service.LyricsCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LyricsCommentServiceImpl implements LyricsCommentService {

    @Autowired
    private LyricsCommentMapper lcMapper;

    @Override
    public LyricsComment findLyricsComment(Integer id) {
        return lcMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<LyricsComment> findAll() {
        return lcMapper.selectAll();
    }

    @Override
    public void addLyricsComment(LyricsComment lyricscomment) {
        lcMapper.insert(lyricscomment);
    }

    @Override
    public boolean delLyricsComment(Integer id) {
        lcMapper.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateLyricsComment(LyricsComment lyricscomment) {
        lcMapper.updateByPrimaryKey(lyricscomment);
        return true;
    }

}

