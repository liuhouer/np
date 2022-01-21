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
    private String movie_name;

    @Column(length = 255)
    private String ret_code;

    @Column()
    private String movie_desc;

    @Column(length = 11)
    private Integer price;

    @Column(length = 2000)
    private String path;

    @Column(length = 255)
    private String add_time;


    @Column(length = 255)
    private String tag;


    @Column(length = 255)
    private String tag_code;

    @Column(length = 11)
    private Integer view_num;


    @Column(length = 255)
    private String color;

    @Column(length = 11)
    private Integer hot_index;


    @Column(length = 255)
    private String displayed;


    @Transient
    private List<Map<String, String>> tag_list;


}