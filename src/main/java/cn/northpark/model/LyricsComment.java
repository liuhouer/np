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
@Table(name = "bc_lyrics_comment")
@Data
public class LyricsComment implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8460236276757497795L;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(length = 6)
    private Integer id;


    @Column(length = 11)
    private Integer lyricsid;

    @Column(length = 11)
    private Integer userid;

    @Column(length = 2000)
    private String comment;

    @Column(length = 200)
    private String create_time;


}