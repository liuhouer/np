package cn.northpark.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

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