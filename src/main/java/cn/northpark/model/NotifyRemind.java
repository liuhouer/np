package cn.northpark.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author bruce
 * @date 2021-11-02
 * @email zhangyang226@gmail.com
 * @site http://blog.northpark.cn | http://northpark.cn | orginazation https://github.com/jellyband
 * 通知提醒
 */
@Entity
@Table(name = "bc_notify_remind")
@Data
public class NotifyRemind implements Serializable{

    private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "paymentableGenerator") 
	@GenericGenerator(name = "paymentableGenerator", strategy = "native") 
	@Column(length = 6)	
	private Integer id;

	/**
	 * 通知提醒类型编号
	 *  1类：在某文章界面评论被回复【通知-被回复人】【通知-站长】
	 *  2类：最爱图册被点赞通知【通知-图册创建者】
	 *  3类：树洞界面的留言被回复【通知-留言创建者】
	 *  4类：xx关注了yy，【通知-被关注者yy】
	 */
	@Column(length = 1)
	private Integer remindID;

	/**
	 * 操作者的ID，三个0代表是系统发送的
	 */
	@Column(length = 32)
	private String senderID;




	/**
	 * 操作者用户名
	 */
	@Column(length = 32)
	private String senderName;

	/**
	 * 操作者的动作，如：捐款、更新、评论、收藏
	 * 【1：评论,2：收藏（爱上），3：关注】
	 */
	@Column(length = 32)
	private String senderAction;

	/**
	 * 目标对象ID
	 */
	@Column(length = 32)
	private String objectID;

	/**
	 * 目标对象内容或简介，比如：文章标题
	 */
	@Column(length = 255)
	private String object;

	/**
	 * 被操作对象类型，如：人、文章、活动、视频等[1：人，2：文章]
	 */
	@Column(length = 32)
	private String objectType;

	/**
	 * 关联资源链接
	 */
	@Column(length = 1000)
	private String objectLinks;

	/**
	 * 消息接收者-可能是对象的所有者或订阅者【目前仅设置为人的通知，订阅先不做，因为此字段为消息接收者的userid】
	 */
	@Column(length = 32)
	private String recipientID;

	/**
	 * 消息内容，由提醒模版生成，需要提前定义
	 */
	@Column(length = 32)
	private String message;

	/**
	 * 创建时间
	 */
	@Column(length = 32)
	private Date createdAt;

	/**
	 * 是否阅读，默认未读，【0：未读，1：已读】
	 */
	@Column(length = 1)
	private String status;

	/**
	 * 阅读时间
	 */
	@Column(length = 1)
	private Date readAt;


}