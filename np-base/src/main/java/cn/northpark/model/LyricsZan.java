package cn.northpark.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bc_lyrics_zan")
@Data
public class LyricsZan implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6905370184898544851L;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(length = 6)
    private Integer id;


    @Column(length = 32)
    private Integer lyricsid;

    @Column(length = 32)
    private Integer userid;

    @Column(length = 4)
    private String love_year;

    @Column(length = 10)
    private String love_date;


}