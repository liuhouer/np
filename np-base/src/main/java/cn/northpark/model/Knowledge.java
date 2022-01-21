package cn.northpark.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author bruce
 * @date 2021-10-25
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 
 */

@Entity
@Table(name = "bc_knowledge")
@Data
public class Knowledge implements Serializable{

/**
 *
 * serialVersionUID
 */
private static final long serialVersionUID = 1L;

	/* 下面是自增的native ID方式 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator") 
	@GenericGenerator(name = "paymentableGenerator", strategy = "native") 
	@Column(length = 6)	
	private Integer id;

	@Column(length = 255)
	private String ret_code;

	@Column(length = 255)
	private String title;

	@Column(length = 255)
	private String brief_img;

	@Column(length = 1000)
	private String brief;

	@Column(length = 1000)
	private String content;

	@Column(length = 255)
	private String post_date;

	@Column(length = 11)
	private Integer price;

	@Column(length = 255)
	private String tags;

	@Column(length = 255)
	private String tags_code;

	@Column(length = 255)
	private String ret_url;

	@Column(length = 255)
	private String path;

	@Column(length = 255)
	private String link_url;

	@Column(length = 20)
	private long view_times;

	@Column(length = 5)
	private String color;

	@Column(length = 11)
	private Integer hot_index;

	@Column(length = 10)
	private String displayed;

	@Transient
	private List<Map<String, String>> tag_list;

	@Override
	public String toString() {
		return "Knowledge{" +
				"id=" + id +
				", ret_code='" + ret_code + '\'' +
				", title='" + title + '\'' +
				", brief_img='" + brief_img + '\'' +
				", brief='" + brief + '\'' +
				", content='" + content + '\'' +
				", post_date='" + post_date + '\'' +
				", price=" + price +
				", tags='" + tags + '\'' +
				", tags_code='" + tags_code + '\'' +
				", ret_url='" + ret_url + '\'' +
				", path='" + path + '\'' +
				", link_url='" + link_url + '\'' +
				", view_times=" + view_times +
				", color='" + color + '\'' +
				", hot_index=" + hot_index +
				", displayed='" + displayed + '\'' +
				", tag_list=" + tag_list +
				'}';
	}
}