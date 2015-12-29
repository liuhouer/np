package com.bruce.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class QQinfo implements Serializable{
	
	/*"ret": 0,
    "msg": "",
    "is_lost": 0,
    "nickname": "潇湘夜雨",
    "gender": "男",
    "province": "",
    "city": "那不勒斯",
    "year": "1995",
    "figureurl": "http://qzapp.qlogo.cn/qzapp/101204480/2B9A136546F685D8188C5C982DCAD268/30",
    "figureurl_1": "http://qzapp.qlogo.cn/qzapp/101204480/2B9A136546F685D8188C5C982DCAD268/50",
    "figureurl_2": "http://qzapp.qlogo.cn/qzapp/101204480/2B9A136546F685D8188C5C982DCAD268/100",
    "figureurl_qq_1": "http://q.qlogo.cn/qqapp/101204480/2B9A136546F685D8188C5C982DCAD268/40",
    "figureurl_qq_2": "http://q.qlogo.cn/qqapp/101204480/2B9A136546F685D8188C5C982DCAD268/100",
    "is_yellow_vip": "0",
    "vip": "0",
    "yellow_vip_level": "0",
    "level": "0",
    "is_yellow_year_vip": "0"*/
	
	private String msg;
	private String nickname;
	private String gender;
	private String province;
	private String city;
	private String year;
	private String figureurl;
	private String figureurl_1;
	private String figureurl_2;
	private String figureurl_qq_1;
	private String figureurl_qq_2;
	private String is_yellow_vip;
	private String vip;
	private String yellow_vip_level;
	private String level;
	private String is_yellow_year_vip;
	

	private Integer ret;
	private Integer is_lost;
	

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getFigureurl() {
		return figureurl;
	}
	public void setFigureurl(String figureurl) {
		this.figureurl = figureurl;
	}
	public String getFigureurl_1() {
		return figureurl_1;
	}
	public void setFigureurl_1(String figureurl_1) {
		this.figureurl_1 = figureurl_1;
	}
	public String getFigureurl_2() {
		return figureurl_2;
	}
	public void setFigureurl_2(String figureurl_2) {
		this.figureurl_2 = figureurl_2;
	}
	public String getFigureurl_qq_1() {
		return figureurl_qq_1;
	}
	public void setFigureurl_qq_1(String figureurl_qq_1) {
		this.figureurl_qq_1 = figureurl_qq_1;
	}
	public String getFigureurl_qq_2() {
		return figureurl_qq_2;
	}
	public void setFigureurl_qq_2(String figureurl_qq_2) {
		this.figureurl_qq_2 = figureurl_qq_2;
	}
	public String getIs_yellow_vip() {
		return is_yellow_vip;
	}
	public void setIs_yellow_vip(String is_yellow_vip) {
		this.is_yellow_vip = is_yellow_vip;
	}
	public String getVip() {
		return vip;
	}
	public void setVip(String vip) {
		this.vip = vip;
	}
	public String getYellow_vip_level() {
		return yellow_vip_level;
	}
	public void setYellow_vip_level(String yellow_vip_level) {
		this.yellow_vip_level = yellow_vip_level;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getIs_yellow_year_vip() {
		return is_yellow_year_vip;
	}
	public void setIs_yellow_year_vip(String is_yellow_year_vip) {
		this.is_yellow_year_vip = is_yellow_year_vip;
	}
	public Integer getRet() {
		return ret;
	}
	public void setRet(Integer ret) {
		this.ret = ret;
	}
	public Integer getIs_lost() {
		return is_lost;
	}
	public void setIs_lost(Integer is_lost) {
		this.is_lost = is_lost;
	}
	@Override
	public String toString() {
		return "QQinfo [msg=" + msg + ", nickname=" + nickname + ", gender="
				+ gender + ", province=" + province + ", city=" + city
				+ ", year=" + year + ", figureurl=" + figureurl
				+ ", figureurl_1=" + figureurl_1 + ", figureurl_2="
				+ figureurl_2 + ", figureurl_qq_1=" + figureurl_qq_1
				+ ", figureurl_qq_2=" + figureurl_qq_2 + ", is_yellow_vip="
				+ is_yellow_vip + ", vip=" + vip + ", yellow_vip_level="
				+ yellow_vip_level + ", level=" + level
				+ ", is_yellow_year_vip=" + is_yellow_year_vip + ", ret=" + ret
				+ ", is_lost=" + is_lost + "]";
	}

}