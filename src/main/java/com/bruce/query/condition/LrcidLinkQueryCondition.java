/////////////////////////////////////////////

package com.bruce.query.condition;
import java.io.Serializable;

public class LrcidLinkQueryCondition implements Serializable {


	private String id;
  

	private String lyricsid;
  

	private Integer lyricsid_;
  



	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getLyricsid() {
		return lyricsid;	
	}
	
	public void setLyricsid(String lyricsid) {
		this.lyricsid = lyricsid;
	}
	public Integer getLyricsid_() {
		return lyricsid_;	
	}
	
	public void setLyricsid_(Integer lyricsid_) {
		this.lyricsid_ = lyricsid_;
	}

}