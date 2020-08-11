package cn.northpark.test;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * @author zy
 * @date 2020年08月11日 21:52:08
 */

@Gecco(matchUrl="http://www.btbuluo.com/moive/", pipelines="consolePipeline")
public class MovieRunner implements HtmlBean {

    private static final long serialVersionUID = -7127412585200687225L;

    @Text
    @HtmlField(cssPath=".bbc578d4cec")
    private String title;

    @Href(click = false)
    @HtmlField(cssPath=".b64f078207c87")
    private String a_href;

    @Image
    @HtmlField(cssPath=".ba02e0 img")
    private String img_url;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getA_href() {
        return a_href;
    }

    public void setA_href(String a_href) {
        this.a_href = a_href;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public static void main(String[] args) {
        GeccoEngine.create()
                //Gecco搜索的包路径
                .classpath("cn.northpark.test")
                //开始抓取的页面地址
                .start("http://www.btbuluo.com/moive/")
                //开启几个爬虫线程
                .thread(1)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                .start();
    }
}
