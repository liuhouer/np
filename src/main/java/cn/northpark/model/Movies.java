package cn.northpark.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "bc_movies")
@Data
public class Movies implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5716389884123787304L;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(length = 6)
    private Integer id;

    @Column(length = 2000)
    private String moviename;

    @Column(length = 255)
    private String retcode;

    @Column()
    private String description;

    @Column(length = 11)
    private Integer price;

    @Column(length = 2000)
    private String path;

    @Column(length = 255)
    private String addtime;


    @Column(length = 255)
    private String tag;


    @Column(length = 255)
    private String tagcode;

    @Column(length = 11)
    private Integer viewnum;


    @Column(length = 255)
    private String color;

    @Column(length = 11)
    private Integer hotindex;


    @Column(length = 255)
    private String displayed;


    @Transient
    private List<Map<String, String>> taglist;


}