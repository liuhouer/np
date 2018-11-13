package cn.northpark.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Builder;
import lombok.Data;

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
    private String retcode;

    @Column(length = 255)
    private String title;

    @Column()
    private String brief;

    @Column()
    private String content;


    @Column(length = 255)
    private String year;


    @Column(length = 255)
    private String month;


    @Column(length = 255)
    private String postdate;

    @Column(length = 255)
    private String os;

    @Column(length = 255)
    private String tags;

    @Column(length = 255)
    private String tagscode;

    @Column(length = 255)
    private String returl;

    @Column()
    private String path;

}