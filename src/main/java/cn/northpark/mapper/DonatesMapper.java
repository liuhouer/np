package cn.northpark.mapper;

import cn.northpark.model.Donates;

import java.util.List;

public interface DonatesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Donates record);

    Donates selectByPrimaryKey(Integer id);

    List<Donates> selectAll();

    int updateByPrimaryKey(Donates record);
}