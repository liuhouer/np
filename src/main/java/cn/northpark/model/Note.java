package cn.northpark.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bc_note")
@Data
public class Note implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 8794166567111470371L;

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(length = 6)
    private Integer id;

    @Column()
    private String brief;

    @Column()
    private String note;

    @Column(length = 255)
    private String opened;

    @Column(length = 255)
    private String createtime;

    @Column()
    private Integer userid;


}