package cn.northpark.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author bruce
 * @date 2016-12-01
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */

@Entity
@Table(name = "bc_astro")
@Data
public class Astro implements Serializable {

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
    private String wx_cop_userid;

    @Column(length = 255)
    private String xzname;

    @Column(length = 255)
    private String add_time;

    @Column(length = 255)
    private String type;

    @Column(length = 255)
    private String status;
    
    
}