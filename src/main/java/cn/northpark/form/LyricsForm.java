package cn.northpark.form;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * @author w_zhangyang
 *
 */
@Data
public class LyricsForm {
	
	@NotEmpty(message = "主题必须填写哦")
	private String title;


}

