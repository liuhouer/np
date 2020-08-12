package cn.northpark.task.movie_spider;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * @author zy
 * @date 2020年08月11日 21:52:08
 */

@Gecco(matchUrl="http://www.btbuluo.com/moive/?p={p}", pipelines={"consolePipeline","MovieListPipeline"})
public class MovieRunner implements HtmlBean {

    private static final long serialVersionUID = 1L;



    @RequestParameter
    private String p;

    @Request
    private HttpRequest request;

    //电影列表
    @HtmlField(cssPath=".b1ab98edca7 ")
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

        for (int i = 2; i <= 896; i++) {
            GeccoEngine.create()
                    //Gecco搜索的包路径
                    .classpath("cn.northpark.task.movie_spider")
                    //开始抓取的页面地址
                    .start("http://www.btbuluo.com/moive/?p="+i)
                    //开启几个爬虫线程
                    .thread(1)
                    //单个爬虫每次抓取完一个请求后的间隔时间
                    .interval(2000)
                    .run();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}


