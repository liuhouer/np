package cn.northpark.test.dataclean;

import cn.northpark.manager.MoviesManager;
import cn.northpark.model.Movies;
import cn.northpark.utils.FileUtils;
import cn.northpark.utils.HTMLParserUtil;
import cn.northpark.utils.PinyinUtil;
import cn.northpark.utils.TimeUtils;
import cn.northpark.utils.encrypt.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.weaver.ast.Var;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

/**
 * @author zhangyang
 * <p>
 * 定时爬取今日情圣文章
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-dao.xml", "classpath:application-service.xml",
        "classpath:application-transaction.xml"})
public class ImportMovieTXT {


    @Autowired
    public MoviesManager moviesManager;
    //

    public void runTask() {


        // =========================================================电影===========================================================================================
        // TODO ..爬虫电影代码

        StringBuilder sb = new StringBuilder();
        sb.append("<ul>\n" +
                " <li><input type=\"checkbox\" name=\"down_url_list_0\" class=\"b1134b3f6\" value=\"$LINK$\" file_name=\"$title$.磁力下载1\">\n" +
                " <a href=\"$LINK$\" class=\"b1085778cf\" title=\"《$title$.磁力下载1》\"> $title$.磁力下载1</a>\n" +
                " <span><label class=\"babf54e9\"><a href=\"$LINK$\" rel=\"nofollow\" target=\"_self\">迅雷下载 </a></label>&nbsp;\n" +
                " </span>\n" +
                " </li>\n" +
                "</ul>");

        List<String> lines = FileUtils.ReadFile("/Users/bruce/Downloads/409部蓝光演唱会磁力链接.txt");

        lines = lines.stream().filter(t->StringUtils.isNotBlank(t)).collect(Collectors.toList());


        try {

            for (String line : lines) {
                insertMer( sb,line);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("爬虫电影代码==============结束=" + TimeUtils.getNowTime());

        // TODO ..爬虫电影代码
        // =========================================================电影===========================================================================================


    }

    private void insertMer(StringBuilder sb, String line) {

        System.err.println(line);
        System.err.println("----");

//        if (list.size() > 1) {
//            String cn_title = "";
//            String path = "";
//
//            cn_title = list.get(0);
//            path = list.get(1);
//
//
//            String ret_code = MD5Utils.encrypt(cn_title, MD5Utils.MD5_KEY);
//
//            String tag = "磁力";
//            String tag_code = "cili";
//
//            if(StringUtils.isNotEmpty(path)){
//
//                path = sb.toString().replace("$LINK$", path).replace("$title$", cn_title);
//            }
//
//            if(list.size()>2){
//                String path2 = list.get(2);
//                if(StringUtils.isNotEmpty(path)){
//
//                    path += sb.toString().replace("$LINK$", path2).replace("$title$", cn_title);
//                }
//            }
//
//            System.err.println(path);
//
//            Movies model = new Movies();
//            model.setmovie_name(cn_title);
//            model.setadd_time("2020-12-26");
//            model.setDescription("<p>" + cn_title + "</p>" );
//            model.setPrice(1);
//            model.setret_code(ret_code);
//            model.setTag(tag);
//            model.settag_code(tag_code);
//            model.setview_num(HTMLParserUtil.geneview_num());
//            model.setColor(PinyinUtil.getFirstChar(PinyinUtil.paraseStringToPinyin(cn_title)));
//            model.setPath(path);
//            moviesManager.addMovies(model);
//        }

    }

    // 测试多页

    @Test
    public void save() {
        runTask();
    }

}
