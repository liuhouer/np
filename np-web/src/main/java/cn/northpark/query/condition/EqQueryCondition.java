/////////////////////////////////////////////

package cn.northpark.query.condition;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bruce
 * @date ${date}
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
@Data
public class EqQueryCondition implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = -6578257695810046024L;


    private Integer id;


    private String title;


    private String img;


    private String brief;


    private String date;


    private String article;



}