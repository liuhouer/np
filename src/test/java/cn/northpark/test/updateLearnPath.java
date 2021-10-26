package cn.northpark.test;

import cn.northpark.utils.NPQueryRunner;
import cn.northpark.utils.PinyinUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Map;

/**
 * 更新学习下载
 */
@Slf4j
public class updateLearnPath {

    public static void main(String[] args) {
        String sql = "select id,content from bc_knowledge  where path is null or path = '' ";
        List<Map<String, Object>> maps = NPQueryRunner.query(sql, new MapListHandler());
        System.err.println(maps.size());
        //拿到集合并行更新
        maps.stream().parallel().unordered().forEach(item->{
            String id = item.get("id").toString();
            String content = item.get("content").toString();
            System.err.println(id+"----");
            System.err.println("content=========>"+content);
//            Document doc = Jsoup.parse(content);
//            //下载地址处理
//            Elements pre = doc.select("pre");
//            String path = pre.outerHtml().replace("www.lanzous.com", "www.lanzoui.com");
//            System.err.println("----------------"+ path);
//            //内容处理
//            doc.select("pre").remove();
//            content = doc.body().html();
//            System.err.println("-------------body---"+ content);
//            String up_sql = "update bc_knowledge set content = ? , path=? where id = ?";
//            NPQueryRunner.update(up_sql,content,path,id);
        });

    }

}
