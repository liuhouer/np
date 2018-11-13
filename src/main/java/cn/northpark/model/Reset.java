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
@Table(name = "bc_reset")
@Data
public class Reset implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2550628003086306974L;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(length = 6)
    private Integer id;


    @Column(length = 32)
    private Integer user_id;

    @Column(length = 255)
    private String auth_code;

    @Column(length = 50)
    private String invalid_time;

    @Column(length = 1)
    private Integer is_email_authed;

    @Column(length = 50)
    private String created_time;

}