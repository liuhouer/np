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
@Table(name = "bc_userprofile")
@Data
public class Userprofile implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1132876192910997061L;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(length = 6)
    private Integer id;

    @Column(length = 32)
    private Integer user_id;

    @Column(length = 255)
    private String name;

    @Column(length = 32)
    private String nickname;

    @Column(length = 255)
    private String language;

    @Column(length = 255)
    private String location;

    @Column(length = 255)
    private String meta;

    @Column(length = 255)
    private String courseware;

    @Column(length = 6)
    private String gender;

    @Column(length = 16)
    private String tel;

    @Column(length = 16)
    private String mailing_address;

    @Column(length = 255)
    private String year_of_birth;

    @Column(length = 6)
    private String level_of_education;

    @Column(length = 6)
    private String goals;

    @Column(length = 1)
    private Integer allow_certificate;

    @Column(length = 2)
    private String country;

    @Column(length = 2)
    private String city;

    @Column(length = 1)
    private Integer is_del;

    @Column(length = 255)
    private String user_slug;

}