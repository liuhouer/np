package cn.northpark.model;

import java.util.Date;

public class EnvCfg {
    private Integer lCfgId;

    private String vcCfgName;

    private String vcCfgValue;

    private String vcDesc;

    private String cStatus;

    private Date dMdfTime;

    public Integer getlCfgId() {
        return lCfgId;
    }

    public void setlCfgId(Integer lCfgId) {
        this.lCfgId = lCfgId;
    }

    public String getVcCfgName() {
        return vcCfgName;
    }

    public void setVcCfgName(String vcCfgName) {
        this.vcCfgName = vcCfgName == null ? null : vcCfgName.trim();
    }

    public String getVcCfgValue() {
        return vcCfgValue;
    }

    public void setVcCfgValue(String vcCfgValue) {
        this.vcCfgValue = vcCfgValue == null ? null : vcCfgValue.trim();
    }

    public String getVcDesc() {
        return vcDesc;
    }

    public void setVcDesc(String vcDesc) {
        this.vcDesc = vcDesc == null ? null : vcDesc.trim();
    }

    public String getcStatus() {
        return cStatus;
    }

    public void setcStatus(String cStatus) {
        this.cStatus = cStatus == null ? null : cStatus.trim();
    }

    public Date getdMdfTime() {
        return dMdfTime;
    }

    public void setdMdfTime(Date dMdfTime) {
        this.dMdfTime = dMdfTime;
    }
}