package cn.northpark.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bc_user_follow")
@Data
public class UserFollow implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7855026603924363478L;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(length = 6)
    private Integer id;


    @Column(length = 32)
    private Integer author_id;

    @Column(length = 32)
    private Integer follow_id;

    @Column(length = 32)
    private String create_time;

    @Column(length = 11)
    private Integer status;


}