package cn.northpark.utils.movie;

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

    public static final String titleCSS = "bbd66a8f60b";
    public static final String a_hrefCSS = "baca92c9178ea";
    public static final String img_urlCSS = "be5269";
    public static final String dateCSS = "b027c";
    public static final String tagsCSS = "b357aa9baf";

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