
package cn.northpark.test.sitemap;

import cn.northpark.utils.NPQueryRunner;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyang
 * <p>
 * 定时爬取今日情圣文章
 */
@Slf4j
public class Gen_Soft_TXT {



    public static void runTask(Integer lastNum) {

        //=========================================================新url的sitemap===========================================================================================

        //添加新url的sitemap
        StringBuilder sb = new StringBuilder();
        List<Map<String, Object>> list = NPQueryRunner.query(" select ret_code from bc_soft where id > " + lastNum + " order by id desc ",new MapListHandler());
        for (Map<String, Object> map : list) {
            log.info("map,==={}",map);
            String ret_code = (String) map.get("ret_code");
            sb.append("https://northpark.cn/soft/");
            sb.append(ret_code + ".html");
            sb.append("\r");
        }

        try {
            FileUtils.writeStringToFile(new File("C:\\Users\\Bruce\\Downloads\\soft.txt"), sb.toString());
        } catch (Exception e) {

            e.printStackTrace();
        }finally {
            try {
                Desktop.getDesktop().open(new File("C:\\Users\\Bruce\\Downloads"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    //测试多页

    public static void main(String[] args) {
        runTask(1);//NEWLY 2020年12月26日
    }

}
