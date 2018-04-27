/////////////////////////////////////////////

package cn.northpark.query.condition;

import java.io.Serializable;

public class LyricsQueryCondition implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = 148196652059839331L;


    private String id;


    private String updatedate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

}