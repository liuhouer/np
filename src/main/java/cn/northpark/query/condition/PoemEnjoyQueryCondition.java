/////////////////////////////////////////////

package cn.northpark.query.condition;

import java.io.Serializable;

/**
 * @author bruce
 * @date 2017-03-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 */
public class PoemEnjoyQueryCondition implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = -4784987433746049531L;


    private Integer id;


    private Integer poem_id;


    private String title;


    private String comment;


    private String translation;


    private String enjoying;


    private String author_info;


    private String tag;


    private String tagcode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoem_id() {
        return poem_id;
    }

    public void setPoem_id(Integer poem_id) {
        this.poem_id = poem_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getEnjoying() {
        return enjoying;
    }

    public void setEnjoying(String enjoying) {
        this.enjoying = enjoying;
    }

    public String getAuthor_info() {
        return author_info;
    }

    public void setAuthor_info(String author_info) {
        this.author_info = author_info;
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

}