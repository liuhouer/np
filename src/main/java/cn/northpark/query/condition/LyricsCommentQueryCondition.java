/////////////////////////////////////////////

package cn.northpark.query.condition;
import java.io.Serializable;

public class LyricsCommentQueryCondition implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 792822500028637535L;


	private String id;
  

	private String lyricsid;
  

	private String userid;
  

	private String comment;
  

	private String create_time;
  



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
	public String getComment() {
		return comment;	
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCreate_time() {
		return create_time;	
	}
	
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

}