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
    private String last_login;

    private String date_joined;

    private String headspanclass;

    private String headspan;

    private String headpath;

    private String tail_slug;//自己的域名空间【字母代号】

    private String meta;// 个性签名

    private String blogsite;//自己的个人页面

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", tail_slug='" + tail_slug + '\'' +
                '}';
    }
}