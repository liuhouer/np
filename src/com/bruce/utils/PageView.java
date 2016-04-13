package com.bruce.utils;

import java.util.List;
import java.util.Map;

public class PageView<T> {
	/** 分页数据 **/
	private List<T> records;
	/** 分页数据  map list结构**/
	private List<Map<String, Object>> mapRecords;
	/** 开始索引**/
	private int startindex;
	/** 结束索引 **/
	private int endindex;
	
	/** 总页数**/
	private int totalpage = 1;
	/** 每页显示记录数**/
	private int maxresult = MyConstant.MAXRESULT;
	/**  当前页**/
	private int currentpage = 1;
	/** 总记录数 **/
	private int totalrecord;
	/** 页码数量 **/
	private int pagecode = MyConstant.PAGECODE;
	
	public int getPagecode() {
		return pagecode;
	}
	

	public void setPagecode(int pagecode) {
		this.pagecode = pagecode;
	}

	public PageView(int maxresult, int currentpage) {
		this.maxresult = maxresult;
		this.currentpage = currentpage;
	}
	
	public PageView() {
		this.maxresult = maxresult;
		this.currentpage = currentpage;
	}
	
	public void setQueryResult(QueryResult<T> qr){
		setTotalrecord(qr.getTotalrecord().intValue());
		setRecords(qr.getResultlist());
	}
	
	public int getTotalrecord() {
		return totalrecord;
	}
	public void setTotalrecord(int totalrecord) {
		this.totalrecord = totalrecord;
		setTotalpage(this.totalrecord%this.maxresult==0? this.totalrecord/this.maxresult : this.totalrecord/this.maxresult+1);
	}
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
	public int getTotalpage() {
		return totalpage;
	}
	
	
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public void setMaxresult(int maxresult) {
		this.maxresult = maxresult;
	}
	


	public int getStartindex() {
		return startindex;
	}


	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}


	public int getEndindex() {
		return endindex;
	}


	public void setEndindex(int endindex) {
		this.endindex = endindex;
	}


	public int getMaxresult() {
		return maxresult;
	}


	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	
	public List<Map<String, Object>> getMapRecords() {
		return mapRecords;
	}


	public void setMapRecords(List<Map<String, Object>> mapRecords) {
		this.mapRecords = mapRecords;
	}
	
}
