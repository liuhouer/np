package cn.northpark.model;

public class Astro {
    private Integer id;

    private String wxCopUserid;

    private String xzName;

    private String addTime;

    private String type;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWxCopUserid() {
        return wxCopUserid;
    }

    public void setWxCopUserid(String wxCopUserid) {
        this.wxCopUserid = wxCopUserid == null ? null : wxCopUserid.trim();
    }

    public String getXzName() {
        return xzName;
    }

    public void setXzName(String xzName) {
        this.xzName = xzName == null ? null : xzName.trim();
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}