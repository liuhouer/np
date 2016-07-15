/////////////////////////////////////////////

package cn.northpark.query.condition;
import java.io.Serializable;

public class LyricsZanQueryCondition implements Serializable {


	private String id;
  

	private String lyricsid;
  

	private String userid;
  



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
	public String getUserid() {
		return userid;	
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}

}