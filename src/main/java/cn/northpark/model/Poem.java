package cn.northpark.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */

@Entity
@Table(name = "bc_poem")
@Data
public class Poem implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

	 /* 注释掉的uuid模板
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")  */

    /* 下面是自增的native ID方式 */
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(length = 6)
    private Integer id;

    @Column(length = 255)
    private String title;

    @Column(length = 255)
    private String years;

    @Column(length = 255)
    private String years_code;

    @Column(length = 255)
    private String author;

    //诗词内容摘要...
    @Column(length = 0)
    private String content;

    @Column(length = 255)
    private String createtime;

    @Column(length = 0)
    private String enjoys;

    @Column(length = 255)
    private String pic_poem;

    @Column(length = 255)
    private String retcode;


    @Column(length = 255)
    private String types;

    @Column(length = 255)
    private String types_code;


    //诗词内容
    @Column(length = 0)
    private String content1;

    //爬取单页面的url
    @Column(length = 255)
    private String returl;

}