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


}