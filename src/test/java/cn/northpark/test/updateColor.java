package cn.northpark.test;

import cn.northpark.utils.NPQueryRunner;
import cn.northpark.utils.PinyinUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.List;
import java.util.Map;

@Slf4j
public class updateColor {

    public static void main(String[] args) {
        String sql = "select id,moviename from bc_movies  where color ='《' ";
        List<Map<String, Object>> maps = NPQueryRunner.query(sql, new MapListHandler());
        System.err.println(maps);

        for (int i = 0; i < maps.size(); i++) {
            String id = maps.get(i).get("id").toString();
            String moviename = maps.get(i).get("moviename").toString();
            String hanzi = moviename.substring(moviename.indexOf("《") + 1, moviename.indexOf("《") + 2);
            String color = PinyinUtil.getFirstChar(hanzi);
            System.err.println(color);
            String up_sql = "update bc_movies set color = '"+color+"' where id = "+id;
            NPQueryRunner.update(up_sql);

        }
    }

}
