/////////////////////////////////////////////

package cn.northpark.query.condition;

import java.io.Serializable;

public class UserLyricsQueryCondition implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = -7562168568479139486L;


    private String id;


    private String userid;


    private String lyricsid;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getLyricsid() {
        return lyricsid;
    }

    public void setLyricsid(String lyricsid) {
        this.lyricsid = lyricsid;
    }

}