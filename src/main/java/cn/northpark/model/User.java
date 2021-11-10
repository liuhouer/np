package cn.northpark.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bc_user")
@Data
public class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -414037746823323884L;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(length = 6)
    private Integer id;

    @Column(length = 30)
    private String username;

    @Column(length = 75)
    private String email;
    
    /**
     * 邮箱是否验证[-1:初始爬取僵尸用户，0：未验证通过，1：已验证]
     */
    @Column(length = 11)
    private String email_flag;

    @Column(length = 128)
    private String password;


    /**
     * 上次登录详细信息 时间+IP+地址
     */ 
    @Column(length = 1000)
    private String last_login;

    @Column(length = 32)
    private String date_joined;

    @Column(length = 20)
    private String headspanclass;

    @Column(length = 20)
    private String headspan;

    @Column(length = 255)
    private String headpath;

    @Column(length = 255)
    private String qq_openid;

    @Column(length = 2000)
    private String qq_info;

    @Column(length = 2000)
    private String tail_slug;//自己的域名空间【字母代号】

    @Column(length = 255)
    private String meta;// 个性签名

    @Column(length = 2000)
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