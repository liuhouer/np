package cn.northpark.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author w_zhangyang
 *
 */
public class LyricsForm {
	
	@NotEmpty(message = "主题必须填写哦")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}

