package cn.northpark.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author bruce
 * @date 2016-11-09
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */

@Entity
@Table(name = "bc_soft")
@Data
@Builder
public class Soft implements Serializable {
	
	/**
	 * 可以通过为方法或构造函数添加
	 * @Tolerate注解让Lombok忽略显式添加的方法或构造函数，
	 * 避免Lombok检测到同名方法或构造函数不自动生成的问题。
	 */
	@Tolerate
	Soft() {}

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    


    /* 下面是自增的native ID方式 */
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(length = 6)
    private Integer id;

    @Column(length = 20)
    private String ret_code;

    @Column(length = 255)
    private String title;

    @Column()
    private String brief;

    @Column()
    private String content;

    @Column()
    private String content_minio;//minio文本

    @Column(length = 255)
    private String year;

    @Column(length = 255)
    private String month;


    @Column(length = 255)
    private String post_date;

    @Column(length = 255)
    private String os;

    @Column(length = 255)
    private String tags;

    @Column(length = 255)
    private String tags_code;

    @Column(length = 255)
    private String ret_url;

    @Column()
    private String path;

    @Column(length = 5)
    private String color;

    @Column(length = 11)
    private Integer hot_index;

    @Column(length = 10)
    private String displayed;

    @Column(length = 1)
    private String use_minio;


}