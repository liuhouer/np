package cn.northpark.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

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

    @Column(length = 32)
    private String updatedate;

    @Column(length = 10)
    private String love_date;

    @Column(length = 255)
    private String albumImg;

    @Column(length = 11)
    private Integer zan;

    @Column(length = 11)
    private Integer pl;


}