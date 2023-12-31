package cn.northpark.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.apache.ibatis.type.Alias;

@Alias("cm_link")
@Data
@Builder
public class Soft {


    /**
     * 可以通过为方法或构造函数添加
     * @Tolerate注解让Lombok忽略显式添加的方法或构造函数，
     * 避免Lombok检测到同名方法或构造函数不自动生成的问题。
     */
    @Tolerate
    Soft() {}

    private Integer id;

    private String retCode;

    private String title;

    private String contentMinio;

    private String year;

    private String month;

    private String postDate;

    private String os;

    private String tags;

    private String tagsCode;

    private String retUrl;

    private String color;

    private Integer hotIndex;

    private String displayed;

    private String useMinio;

    private String brief;

    private String content;

    private String path;

}