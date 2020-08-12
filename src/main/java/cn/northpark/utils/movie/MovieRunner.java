package cn.northpark.utils.movie;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

import java.util.List;

/**
 * @author zy
 * @date 2020年08月11日 21:52:08
 */

@Gecco(matchUrl="http://www.btbuluo.com/moive/", pipelines={"consolePipeline","MovieListPipeline"})
public class MovieRunner implements HtmlBean {

    private static final long serialVersionUID = 1L;

    @Request
    private HttpRequest request;

    //电影列表
    @HtmlField(cssPath=".b9a3d")
    private List<MovieListPage> list;



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
        GeccoEngine.create()
                //Gecco搜索的包路径
                .classpath("cn.northpark.utils.movie")
                //开始抓取的页面地址
                .start("http://www.btbuluo.com/moive/")
                //开启几个爬虫线程
                .thread(1)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                .run();
    }
}


