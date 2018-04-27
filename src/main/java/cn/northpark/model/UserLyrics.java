package cn.northpark.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "bc_user_lyrics")
public class UserLyrics implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -3554243167217151099L;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(length = 6)
    private Integer id;


    @Column(length = 32)
    private Integer userid;

    @Column(length = 32)
    private Integer lyricsid;


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getLyricsid() {
        return lyricsid;
    }

    public void setLyricsid(Integer lyricsid) {
        this.lyricsid = lyricsid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}