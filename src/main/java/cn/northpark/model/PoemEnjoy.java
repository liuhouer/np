package cn.northpark.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */

@Entity
@Table(name = "bc_poem_enjoy")
@Data
public class PoemEnjoy implements Serializable {

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

    @Column(length = 11)
    private Integer poem_id;

    @Column(length = 255)
    private String title;

    @Column(length = 2000)
    private String comment;

    @Column(length = 2000)
    private String translation;

    @Column(length = 2000)
    private String enjoying;

    @Column(length = 2000)
    private String author_info;

    @Column(length = 255)
    private String tag;

    @Column(length = 255)
    private String tagcode;


}