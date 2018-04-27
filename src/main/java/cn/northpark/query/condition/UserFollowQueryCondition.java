/////////////////////////////////////////////

package cn.northpark.query.condition;

import java.io.Serializable;

public class UserFollowQueryCondition implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = -3118574764260128924L;


    private String id;


    private String author_id;


    private String follow_id;


    private String create_time;


    private Integer status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getFollow_id() {
        return follow_id;
    }

    public void setFollow_id(String follow_id) {
        this.follow_id = follow_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}