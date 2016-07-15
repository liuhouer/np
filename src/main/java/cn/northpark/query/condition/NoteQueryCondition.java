/////////////////////////////////////////////

package cn.northpark.query.condition;
import java.io.Serializable;

public class NoteQueryCondition implements Serializable {


	private String id;
  

	private String note;
  

	private String opened;
  

	private String createtime;
  

	private String userid;
  



	public String getId() {
		return id;	
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getNote() {
		return note;	
	}
	
	public void setNote(String note) {
		this.note = note;
	}
	public String getOpened() {
		return opened;	
	}
	
	public void setOpened(String opened) {
		this.opened = opened;
	}
	public String getCreatetime() {
		return createtime;	
	}
	
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUserid() {
		return userid;	
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}

}