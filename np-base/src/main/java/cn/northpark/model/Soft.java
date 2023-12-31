package cn.northpark.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("cm_link")
@Data
public class Soft {

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