package cn.northpark.query.condition;

import java.io.Serializable;

import lombok.Data;

/**
 * @author bruce
 * @date 2017-12-06
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Data
public class VpsQueryCondition implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private Integer id;


    private String title;


    private String tags;


    private String brief;


    private String date;


    private String article;


    private String retcode;


    private String returl;


    private String color;



}