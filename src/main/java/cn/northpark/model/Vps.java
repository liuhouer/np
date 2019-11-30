package cn.northpark.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;

import com.google.common.collect.Lists;

import lombok.Data;

/**
 * @author bruce
 * @date 2017-12-06
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */

@Entity
@Table(name = "bc_vps")
@Data
public class Vps implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /* 下面是自增的native ID方式 */
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "native")
    @Column(length = 6)
    private Integer id;

    @Column(length = 2000)
    private String title;

    @Column(length = 2000)
    private String tags;

    @Column(length = 2000)
    private String brief;

    @Column(length = 255)
    private String date;

    @Column(length = 255)
    private String article;

    @Column(length = 255)
    private String retcode;

    @Column(length = 255)
    private String returl;

    @Column(length = 11)
    private String color;


    @Transient
    private List<Map<String, String>> taglist;


    public void setTags(String tags) {
        this.tags = tags;

        //设置taglist
        List<Map<String, String>> taglist2 = Lists.newArrayList();
        if (StringUtils.isNotEmpty(tags)) {
            String[] tagarr = tags.split(",");
            for (int i = 0; i < tagarr.length; i++) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("tag", tagarr[i]);
                taglist2.add(map);
            }
        }

        setTaglist(taglist2);
    }

}