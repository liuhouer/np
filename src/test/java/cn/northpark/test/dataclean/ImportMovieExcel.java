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

/**
 * @author zhangyang
 * <p>
 * 定时爬取今日情圣文章
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-dao.xml", "classpath:application-service.xml",
        "classpath:application-transaction.xml"})
public class ImportMovieExcel {

    public static int count = 0;

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
        List<List<String>> lists = FileUtils.readExcel("/Users/bruce/Downloads/MP4BA电影.xlsx");


        try {

            for (List<String> list : lists) {
                insertMer( sb,list);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("爬虫电影代码==============结束=" + TimeUtils.getNowTime());

        // TODO ..爬虫电影代码
        // =========================================================电影===========================================================================================


    }

    private void insertMer(StringBuilder sb, List<String> list) {

        if (list.size() > 1) {
            String cn_title = "";
            String path = "";

            cn_title = list.get(0);
            path = list.get(1);


            String retcode = MD5Utils.encrypt(cn_title, MD5Utils.MD5_KEY);

            String tag = "磁力";
            String tagcode = "cili";

            if(StringUtils.isNotEmpty(path)){

                path = sb.toString().replace("$LINK$", path).replace("$title$", cn_title);
            }

            if(list.size()>2){
                String path2 = list.get(2);
                if(StringUtils.isNotEmpty(path)){

                    path += sb.toString().replace("$LINK$", path2).replace("$title$", cn_title);
                }
            }

            System.err.println(path);

            Movies model = new Movies();
            model.setMoviename(cn_title);
            model.setAddtime("2020-12-26");
            model.setDescription("<p>" + cn_title + "</p>" );
            model.setPrice(1);
            model.setRetcode(retcode);
            model.setTag(tag);
            model.setTagcode(tagcode);
            model.setViewnum(HTMLParserUtil.geneViewNum());
            model.setColor(PinyinUtil.getFirstChar(PinyinUtil.paraseStringToPinyin(cn_title)));
            model.setPath(path);
            moviesManager.addMovies(model);
        }

    }

    // 测试多页

    @Test
    public void save() {
        runTask();
    }

    /**
     * 高并发插入上万条数据
     *
     * @param totalCount
     * @param threadTotal
     * @throws InterruptedException
     * @author Bruce
     */
    public void CurrencyAdd(int totalCount, int threadTotal, List<List<String>> lists) throws InterruptedException {

        StringBuilder sb = new StringBuilder();
        sb.append("<ul>\n" +
                " <li><input type=\"checkbox\" name=\"down_url_list_0\" class=\"b1134b3f6\" value=\"$LINK$\" file_name=\"$title$.磁力下载1\">\n" +
                " <a href=\"$LINK$\" class=\"b1085778cf\" title=\"《$title$.磁力下载1》\"> $title$.磁力下载1</a>\n" +
                " <span><label class=\"babf54e9\"><a href=\"$LINK$\" rel=\"nofollow\" target=\"_self\">迅雷下载 </a></label>&nbsp;\n" +
                " </span>\n" +
                " </li>\n" +
                "</ul>");
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 限制同时执行的线程数
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(totalCount);

        for (int i = 0; i < totalCount; i++) {
            List<String> list = lists.get(i);
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();

                    //insert
                    insertMer(sb, list);


                    semaphore.release();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                countDownLatch.countDown();

            });
        }

        //异步不等待执行过程就把这行注掉
        countDownLatch.await();
        executorService.shutdown();
        log.info("count{}", count);
    }

    private synchronized static void add() {

        count++;

    }

}
