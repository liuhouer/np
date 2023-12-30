package cn.northpark.mapper;

import cn.northpark.model.Astro;

import java.util.List;

public interface AstroMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Astro record);

    Astro selectByPrimaryKey(Integer id);

    List<Astro> selectAll();

    int updateByPrimaryKey(Astro record);
}