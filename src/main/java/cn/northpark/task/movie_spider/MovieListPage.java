package cn.northpark.task.movie_spider;

import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Image;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * @author zhangyang
 * @date 2020年08月12日 09:46:13
 */
public class MovieListPage implements HtmlBean {

    private static final long serialVersionUID = 1L;

    public static final String titleCSS = "b1e5f56717b";
    public static final String a_hrefCSS = "b6572f3ec8bd1";
    public static final String img_urlCSS = "b8f2f7";
    public static final String dateCSS = "b9e2a";
    public static final String tagsCSS = "b3d71fb1c9";

    @Text
    @HtmlField(cssPath = "span."+titleCSS+"")
    private String title;

    @Href(click = true)
    @HtmlField(cssPath = "a."+a_hrefCSS+"")
    private String a_href;


    @Image
    @HtmlField(cssPath = "div."+img_urlCSS+" img")
    private String img_url;


    @Text
    @HtmlField(cssPath = "span."+dateCSS+"")
    private String date;


    @Text
    @HtmlField(cssPath = "div."+tagsCSS+"")
    private String tags;


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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}