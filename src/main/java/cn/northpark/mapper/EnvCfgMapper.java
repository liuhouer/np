package cn.northpark.mapper;

import cn.northpark.model.EnvCfg;
import java.util.List;

public interface EnvCfgMapper {
    int deleteByPrimaryKey(Integer lCfgId);

    int insert(EnvCfg record);

    EnvCfg selectByPrimaryKey(Integer lCfgId);

    List<EnvCfg> selectAll();

    int updateByPrimaryKey(EnvCfg record);
}