package cn.northpark.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author bruce
 *
 */
@Data
public class LyricsForm {

	@NotEmpty(message = "主题必须填写哦")
	private String title;

	@NotEmpty(message = "爱上日期必须填写")
	private String loveDate;


}

