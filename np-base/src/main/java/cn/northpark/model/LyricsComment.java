package cn.northpark.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

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