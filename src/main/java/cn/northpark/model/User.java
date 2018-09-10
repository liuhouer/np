package cn.northpark.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "bc_user")
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


    public String getQq_openid() {
        return qq_openid;
    }

    public void setQq_openid(String qq_openid) {
        this.qq_openid = qq_openid;
    }

    public String getQq_info() {
        return qq_info;
    }

    public void setQq_info(String qq_info) {
        this.qq_info = qq_info;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(String date_joined) {
        this.date_joined = date_joined;
    }

    public String getHeadpath() {
        return headpath;
    }

    public void setHeadpath(String headpath) {
        this.headpath = headpath;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getTail_slug() {
        return tail_slug;
    }

    public void setTail_slug(String tail_slug) {
        this.tail_slug = tail_slug;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadspanclass() {
        return headspanclass;
    }

    public void setHeadspanclass(String headspanclass) {
        this.headspanclass = headspanclass;
    }

    public String getHeadspan() {
        return headspan;
    }

    public void setHeadspan(String headspan) {
        this.headspan = headspan;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getBlogsite() {
        return blogsite;
    }

    public void setBlogsite(String blogsite) {
        this.blogsite = blogsite;
    }

	public String getEmail_flag() {
		return email_flag;
	}

	public void setEmail_flag(String email_flag) {
		this.email_flag = email_flag;
	}
}