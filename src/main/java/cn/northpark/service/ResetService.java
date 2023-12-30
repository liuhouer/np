
package cn.northpark.service;

import cn.northpark.model.Reset;

import java.util.List;

public interface ResetService {

    Reset findReset(Integer id);

    List<Reset> findAll();

    void addReset(Reset reset);

    boolean delReset(Integer id);

    boolean updateReset(Reset reset);

    List<Reset> findByCondition(String whereSql, String orderBy);
}


