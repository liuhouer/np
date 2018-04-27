/////////////////////////////////////////////

package cn.northpark.query.condition;

import java.io.Serializable;

/**
 * @author bruce
 * @date 2017-01-05
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public class TagsQueryCondition implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 7387479825801384094L;


    private Integer id;


    private String tag;


    private String tagcode;


    private String tagtype;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagcode() {
        return tagcode;
    }

    public void setTagcode(String tagcode) {
        this.tagcode = tagcode;
    }

    public String getTagtype() {
        return tagtype;
    }

    public void setTagtype(String tagtype) {
        this.tagtype = tagtype;
    }

}