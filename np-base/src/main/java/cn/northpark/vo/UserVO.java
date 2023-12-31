package cn.northpark.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1l;

    private Integer id;

    private String username;

    private String email;
    
    /**
     * 上次登录详细信息 时间+IP+地址
     */ 
    private String tailSlug;//自己的域名空间【字母代号】

    private String meta;// 个性签名

    private String blogSite;//自己的个人页面

    private String headSpanClass;

    private String headSpan;

    private String headPath;

    private String dateJoined;

    private String lastLogin;



}