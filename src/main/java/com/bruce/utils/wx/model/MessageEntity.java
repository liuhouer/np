package com.bruce.utils.wx.model;


public class MessageEntity {

	private String messageid;//微信返回的messageid.每个公众号messageid唯一
	private long creatime;
	private String fromuser;
	private String touser;
	private String messagetype;
	private String content;
	private String uniquecontent;
	public String getMessageid() {
		return messageid;
	}
	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}
	public long getCreatime() {
		return creatime;
	}
	public void setCreatime(long creatime) {
		this.creatime = creatime;
	}
	public String getFromuser() {
		return fromuser;
	}
	public void setFromuser(String fromuser) {
		this.fromuser = fromuser;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getMessagetype() {
		return messagetype;
	}
	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUniquecontent() {
		return uniquecontent;
	}
	public void setUniquecontent(String uniquecontent) {
		this.uniquecontent = uniquecontent;
	}
	
	
}
