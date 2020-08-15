package cn.northpark.task.movie_spider;

import cn.northpark.utils.CacheUtil;
import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import org.springframework.util.CollectionUtils;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author zy
 * @date 2020年08月11日 21:52:08
 */

@Gecco(matchUrl = "http://www.btbuluo.com/moive/?p={p}", pipelines = {"consolePipeline", "MovieListPipeline"})
public class MovieRunner implements HtmlBean {

    private static final long serialVersionUID = 1L;

    public static final String eachMovieDIV = "b9288";

    @RequestParameter
    private String p;

    @Request
    private HttpRequest request;

    //电影列表
    @HtmlField(cssPath = "." + eachMovieDIV + "")
    private List<MovieListPage> list;


    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }


    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public List<MovieListPage> getList() {
        return list;
    }

    public void setList(List<MovieListPage> list) {
        this.list = list;
    }

    public static void main(String[] args) {

        for (int i = 179; i <= 896; i++) {

            MovieListPipeline.detailRequests.clear();
            CacheUtil.getCache().cleanUp();


            //先获取分类列表
            while (CollectionUtils.isEmpty(MovieListPipeline.detailRequests)) {
                genePageList(i);
            }


            //分类列表下的商品列表采用3线程抓取
            GeccoEngine.create()
                    .classpath("cn.northpark.task.movie_spider")
                    //开始抓取的页面地址
                    .start(MovieListPipeline.detailRequests)
                    //开启几个爬虫线程
                    .thread(4)
                    //单个爬虫每次抓取完一个请求后的间隔时间
                    .interval(3000)
                    .run();


        }


    }

    private static void genePageList(int i) {
        //先获取分类列表
        HttpGetRequest start = new HttpGetRequest("http://www.btbuluo.com/moive/?p=" + i);
        start.setCharset("UTF-8");
        GeccoEngine.create()
                .classpath("cn.northpark.task.movie_spider")
                //开始抓取的页面地址
                .start(start)
                //开启几个爬虫线程
                .thread(1)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                .run();
    }
}


