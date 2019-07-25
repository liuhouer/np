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
@Table(name = "bc_lyrics")
@Data
public class Lyrics implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7352450415620898579L;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(length = 6)
    private Integer id;


    @Column(length = 255)
    private String title;

    @Column(length = 255)
    private String titlecode;


    @Column(length = 255)
    private String updatedate;

    @Column(length = 255)
    private String albumImg;

    @Column(length = 11)
    private Integer zan;

    @Column(length = 11)
    private Integer pl;


}