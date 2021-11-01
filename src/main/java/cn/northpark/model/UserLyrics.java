package cn.northpark.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bc_user_lyrics")
@Data
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


}