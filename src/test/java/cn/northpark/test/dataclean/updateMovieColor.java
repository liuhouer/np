package cn.northpark.test.dataclean;

import cn.northpark.constant.BC_Constant;
import cn.northpark.utils.NPQueryRunner;
import cn.northpark.utils.PinyinUtil;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

@Slf4j
public class updateMovieColor {

    public static void main(String[] args) {
        ImmutableList cList = BC_Constant.specColorMap.keySet().asList();
        cList.parallelStream().forEach(c->{
            System.err.println("spec color---->"+c);

            String sql = "select id,movie_name from bc_movies  where color = ?";
            List<Map<String, Object>> maps = NPQueryRunner.query(sql, new MapListHandler(),c);

            //尝试从标题截图赋值
            maps.parallelStream().forEach(item->{
                String id = item.get("id").toString();
                String movie_name = item.get("movie_name").toString();
                for (Object cc : cList) {
                    movie_name = movie_name.replace(String.valueOf(cc),String.valueOf(BC_Constant.specColorMap.get(String.valueOf(cc))));
                }
                System.err.println("mod movie_name---->"+movie_name);
                if(StringUtils.isNotEmpty(movie_name)){

                    String color = PinyinUtil.getFirstChar(movie_name);
                    System.err.println("mod color---->"+color);
                    String up_sql = "update bc_movies set color = '"+color+"' where id = "+id;
                    NPQueryRunner.update(up_sql);
                }
            });

            //全是特殊字符的直接替换color,不从标题截取
//            maps.parallelStream().forEach(item-> {
//                String id = item.get("id").toString();
//                String up_sql = "update bc_movies set color = '" + String.valueOf(BC_Constant.specColorMap.get(String.valueOf(c))) + "' where id = " + id;
//                NPQueryRunner.update(up_sql);
//            });
        });
    }

}
