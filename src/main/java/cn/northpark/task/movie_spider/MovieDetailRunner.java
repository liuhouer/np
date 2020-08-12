package cn.northpark.task.movie_spider;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * @author zy
 * @date 2020年08月11日 21:52:08
 */

@Gecco(matchUrl="http://www.btbuluo.com/a/{a_href}", pipelines={"consolePipeline","MovieDetailPipeline"})
public class MovieDetailRunner implements HtmlBean {

    private static final long serialVersionUID = 1L;

    private static final String down_CSS="b799db63";
    private static final String detail_CSS="b512";

    @Request
    private HttpRequest request;

    /**
     * 详情链接
     */
    @RequestParameter
    private String a_href;


    @Html
    @HtmlField(cssPath="div."+detail_CSS+":nth-child(1)")
    private String detail;


    @Html
    @HtmlField(cssPath="div."+down_CSS+"")
    private String download;


    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public String getA_href() {
        return a_href;
    }

    public void setA_href(String a_href) {
        this.a_href = a_href;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }




}


