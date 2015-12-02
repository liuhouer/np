package com.bruce.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 *
 */
public class EmailUtils {
	private static Logger log =LoggerFactory.getLogger(EmailUtils.class);
	public static final EmailUtils emailUtil = new EmailUtils();
	
	
	/**
	 * 找回密码邮件认证
	 * 
	 * @param toEmail
	 * @param usrId
	 * @param authCode
	 */
	public static void changePwd(String toEmail, String usrId, String authCode,HttpServletRequest request) {
		try {

			String host = "smtp.qq.com";
			String myEmail = "bruceleeof1991@qq.com";
			String myName = "小布";
			String myPassword = Base64Util.JIEMI("eWFuZzUyMXhpYW8xMzE0MDAwMDAw");
			// 接收者邮箱
			String to = toEmail;
			String subject = "找回密码";
			String mailConent = "找回密码";

			HtmlEmail email = new HtmlEmail();
			email.setAuthentication(myEmail, myPassword);
			email.setHostName(host);
			email.addTo(to, "亲");
			email.setFrom(myEmail);
			email.setSubject(subject);
			String dm =  URLUtil.getDomain(request);
			// 注意，发送内容时，后面这段会让中文正常显示，否则乱码
			email.setCharset("utf-8");
			email.setHtmlMsg("<html><body><p style=\"margin-left: 30px;\"><font size=\"5\" color=\"rgb(216,206,178)\" >忘记密码？</font></p>"
					+ "<p style=\"background-color:rgb(163,210,202);margin-left: 30px;\">" + "【布@词】在"
					+ TimeUtils.nowTime()
					+ "收到了您的 帐号重置密码的请求。<br/><br/>"
					+ "如果要重置密码，请单击下面的链接： <br/><br/> "
					+ "<a target=\"_blank\" href=\"http://"
					+ dm
					+ "/cm/reset?userid="
					+ usrId
					+ "&auth_code="
					+ authCode
					+ "\"alt=\"c1ad9466-f4f4-49be-9ae8-34cb4412df7c86b293db-5b88-4435-a2e9-ef1fb8a6bfec\">"
					+ dm
					+ "/reset/setp2/"
					+ usrId
					+ "</a><br/><br/>"
					+ "请注意，这是一次性链接。<br/>  "
					+ "点击链接以后，密码将被修改为【 <font color=\"red\">"+authCode+"</font>  】,请牢记喔~尽快到个人信息页面修改<br/>  "
					+ "如果不想重置密码，请忽略此邮件，您的密码不会改变。如有任何疑问或顾虑，请联系布.词。<br/><br/>" + "小布<br/><br/>" + "</p>" + "</body></html>"); /* 邮件内容 */
			// 添加附件对象
			// email.attach(attachment);
			// 发送
			email.send();
			System.err.println("邮件发送成功");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("邮件发送失败");
		}

	}

	public boolean sendAttachmentMail(String host, String from, String username, String password, String to, String subject,
			String mailConent) throws Exception {
		// 创建附件对象
		EmailAttachment attachment = new EmailAttachment();
		/* 附件的地址 */
		attachment.setPath("D://lrc//3934-1399562620.jpg!sq.jpg");
		// 设定为附件
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		/* 附件的描述 */
		attachment.setDescription("图片啊");
		/* 附件的名称，必须和文件名一致 */
		attachment.setName("image.jpg");
		/* new一个HtmlEmail发送对象 */
		HtmlEmail email = new HtmlEmail();
		email.setAuthentication(username, password);
		email.setHostName(host);
		email.addTo(to, from);
		email.setFrom(from);
		email.setSubject(subject);
		// 注意，发送内容时，后面这段会让中文正常显示，否则乱码
		email.setCharset("GB2312");
		email.setHtmlMsg("<html><body>请点击下面代码找回密码<br/><a href=\"http://192.168.0.132:8181/yunlu/admin/changePwd.action?userid=c1ad9466-f4f4-49be-9ae8-34cb4412df7c&authcode=c1ad9466-f4f4-49be-9ae8-34cb4412df7c\" alt=\"c1ad9466-f4f4-49be-9ae8-34cb4412df7c86b293db-5b88-4435-a2e9-ef1fb8a6bfec\">c1ad9466-f4f4-49be-9ae8-34cb4412df7c?&86b293db-5b88-4435-a2e9-ef1fb8a6bfec</a></body></html>"); /* 邮件内容 */
		// 添加附件对象
		email.attach(attachment);
		// 发送
		email.send();
		return true;
	}
  
	/**
	 * 发送 课程管理邀请邮件
	 * 
	 * @param toEmail
	 *            被邀请人邮箱
	 * @param toName
	 *            被邀请人姓名
	 * @param fromName
	 *            邀请人姓名
	 * @param crsName
	 *            课程名称
	 * @param emailMap
	 *            邮件配置信息
	 * @return Boolean
	 */
	public static boolean sendCrsInvite(String toEmail, String toName, String fromName,String crsName,Map<String,Object> emailMap,HttpServletRequest request) {
		String subject = "课程管理邀请"; /* 主题 */
		String dm = (String) request.getSession().getAttribute("dm");
		/* 邮件内容:支持HTML语法 */
		String emailContent = "尊敬的用户&nbsp;<span style='color:blue'>" + toName + "</span>，&nbsp;您好：<br/>"
				+ "<p>&nbsp;&nbsp;&nbsp; <span style='color:blue'>" + fromName + "</span>&nbsp;"
				+ "邀请您管理课程: <a href='http://"+ dm+"/teacher' style='color:blue'>" + crsName + "</a><br/>"
				+ "&nbsp;&nbsp;&nbsp;如您接受邀请，点击下方链接或者将其复制并粘贴浏览器地址栏进行访问：<br/>"
				+ dm+"/teacher <br/><br/>"
				+ "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;---云路科技</p>";	
		HtmlEmail email = new HtmlEmail();
		email.setHostName(String.valueOf(emailMap.get("smtpHost")));	/* 设置发信的smtp服务器 */
		email.setSubject(subject); 		/* 设置邮件主题 */
		email.setAuthentication(String.valueOf(emailMap.get("fromEmail")), String.valueOf(emailMap.get("password")));/* SMTP服务器认证,设置帐号、密码 */
		email.setCharset("utf-8");	/* 设置邮件字符集 */
		try {
			email.addTo(toEmail, toName);	/* 设置收件人帐号和收件人 */
			String yl_username = String.valueOf(emailMap.get("nikeName"));
			email.setFrom(String.valueOf(emailMap.get("fromEmail")),yl_username);	/* 设置发信的邮件帐号和发信人 */
			email.setHtmlMsg(emailContent);	/* 设置邮件正文，此方法这里的样式可以显示出来 */
			email.send();
			return true;
		} catch (Exception  e) {
			System.err.println("向邮箱("+toEmail+")发送邮件失败！");
			e.printStackTrace();
			return false;
		}
	}

	
	/**
	 * 正则验证邮箱格式
	 * 
	 * @param email
	 *            邮箱
	 * @return Boolean
	 */
	public static boolean isEmail(String email){
		String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		return email.matches(regex);
	}

        
                 
	/**
	 * 发送前往维护公司页面的连接
	 * 
	 * @param toEmail
	 * @param corId
	 * @param fullName
	 */
	public static void toCorpanyMainEmail(HttpServletRequest request,String toEmail, int corId, String fullName) {
		try {
			String host = "smtp.qq.com";
			String myEmail = "waiter@yunlu.info";
			String myPassword = MD5Utils.decodePwd("ACE-4E80-8WXVubHUyMDEqA1AF250F-5");
			String to = toEmail;
			String subject = "前往维护公司页面";
			
			HtmlEmail email = new HtmlEmail();
			email.setAuthentication(myEmail, myPassword);
			email.setHostName(host);
			email.addTo(to, "亲爱的用户");
			email.setFrom(myEmail);
			email.setSubject(subject);
			email.setCharset("utf-8");
			String dm = (String) request.getSession().getAttribute("dm");
			String href =  dm
					+ "/corporation/toCorBaseInfo?corId="
					+ corId
					+ "&fullName="
					+ fullName;
			if(!href.startsWith("htt")){
				href = "http://"+href;
			}
			email.setHtmlMsg("<html><body><p style=\"margin-left: 30px;\"><font size=\"5\" color=\"rgb(216,206,178)\" >前往维护公司页面</font></p>"
					+ "<p style=\"background-color:rgb(163,210,202);margin-left: 30px;\">"
					+ "云路在"
					+ TimeUtils.nowTime()
					+ "收到了您的 帐号认证的请求。<br/><br/>"
					+ "如果要认证该邮箱，请单击下面的链接，或者将其复制并粘贴到浏览器中： <br/><br/> "
					+ "<a href=\""+href+"\" >"+href
					+ "</a><br/><br/>"
					+ "请注意，这是一次性链接。<br/>  "
					+ "如果这不是您的邮箱进行的操作，请忽略此邮件。<br/><br/>"
					+ "云路团队<br/><br/>" + "</p>" + "</body></html>");

			email.send();
			log.debug("邮件发送成功");

		} catch (Exception e) {
			log.debug("邮件发送失败" + e);
		}
	}
        
	/**
	 * 邮箱认证
	 * 
	 * @param toEmail
	 * @param userid
	 * @param authcode
	 */
	public static void authEmail(HttpServletRequest request,String toEmail, String userid, String authcode,String firstFlag) {
		try {
			String host = "smtp.qq.com";
			String myEmail = "waiter@yunlu.info";
			String myPassword = MD5Utils.decodePwd("ACE-4E80-8WXVubHUyMDEqA1AF250F-5");
			String to = toEmail;
			String subject = "云路认证";
			
			HtmlEmail email = new HtmlEmail();
			email.setAuthentication(myEmail, myPassword);
			email.setHostName(host);
			email.addTo(to, "亲爱的用户");
			email.setFrom(myEmail);
			email.setSubject(subject);
			email.setCharset("utf-8");
			String dm = (String) request.getSession().getAttribute("dm");
			String href =  dm
					+ "/user/authEmail?userid="
					+ userid
					+ "&firstFlag="
					+ firstFlag
					+ "&auth_code="
					+ authcode;
			if(!href.startsWith("htt")){
				href = "http://"+href;
			}
			email.setHtmlMsg("<html><body><p style=\"margin-left: 30px;\"><font size=\"5\" color=\"rgb(216,206,178)\" >邮箱认证？</font></p>"
					+ "<p style=\"background-color:rgb(163,210,202);margin-left: 30px;\">"
					+ "云路在"
					+ TimeUtils.nowTime()
					+ "收到了您的 帐号认证的请求。<br/><br/>"
					+ "如果要认证该邮箱，请单击下面的链接，或者将其复制并粘贴到浏览器中： <br/><br/> "
					+ "<a href=\""+href+"\" >"+href
					+ "</a><br/><br/>"
					+ "请注意，这是一次性链接。<br/>  "
					+ "如果这不是您的邮箱进行的操作，请忽略此邮件。<br/><br/>"
					+ "云路团队<br/><br/>" + "</p>" + "</body></html>");

			email.send();
			log.debug("邮件发送成功");

		} catch (Exception e) {
			log.debug("邮件发送失败" + e);
		}
	}

	/**
	 * 邮箱认证
	 * 
	 * @param toEmail
	 * @param conpany
	 * @param jobtype
	 * @param title
	 * @param face_time
	 * @param address
	 * @param face_detail
	 */
	public static void faceEmail(String toEmail, String conpany, String jobtype, String title, String face_time, String address,
			String face_detail) {
		try {

			EmailUtils as = new EmailUtils();
			String host = "smtp.qq.com";
			String myEmail = "waiter@yunlu.info";
			String myName = "云路科技";
			String myPassword = MD5Utils.decodePwd("ACE-4E80-8WXVubHUyMDEqA1AF250F-5");
			// 接收者邮箱
			String to = toEmail;
			String subject = "岗位面试邀请提醒";
			String mailConent = "岗位面试邀请提醒";

			HtmlEmail email = new HtmlEmail();
			email.setAuthentication(myEmail, myPassword);
			email.setHostName(host);
			email.addTo(to, "亲爱的用户");
			email.setFrom(myEmail);
			email.setSubject(subject);
			// 注意，发送内容时，后面这段会让中文正常显示，否则乱码
			email.setCharset("utf-8");

			email.setHtmlMsg("<html><body><p style=\"margin-left: 30px;\"><font size=\"5\" color=\"rgb(216,206,178)\" >岗位面试邀请</font></p>"
					+ "<p style=\"background-color:rgb(163,210,202);margin-left: 30px;\">"
					+ "亲爱的云路用户：<br/><br/>"
					+ "您收到了一份面试邀请<br/><br/> "
					+ conpany
					+ "&nbsp;的"
					+ jobtype
					+ "的面试邀请<br/><br/>"
					+ "主题:"
					+ title
					+ "<br/><br/>"
					+ "时间:"
					+ face_time
					+ "<br/><br/>"
					+ "地点:"
					+ address
					+ "<br/><br/>"
					+ "邀请详情："
					+ face_detail
					+ "<br/><br/>"
					+ "查看详情："
					+ "请登录云路在线课堂-个人中心-岗位申请记录，查看岗位回复详情。<br/><br/>"
					+ "如果这不是您的邮箱进行的操作，请忽略此邮件。<br/><br/>"
					+ "云路团队<br/><br/>"
					+ "</p>"
					+ "</body></html>"); /* 邮件内容 */
			// 添加附件对象
			// email.attach(attachment);
			// 发送
			email.send();
			System.err.println("邮件发送成功");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("邮件发送失败");
		}

	}

	
	/**
	 * 邮箱绑定
	 * 
	 * @param toEmail
	 * @param userid
	 * @param authcode
	 */
	public static void emailBindApproval(String toEmail, String userid, String authcode,HttpServletRequest request) {
		try {

			EmailUtils as = new EmailUtils();
			String host = "smtp.qq.com";
			String myEmail = "waiter@yunlu.info";
			String myName = "云路科技";
			String myPassword = MD5Utils.decodePwd("ACE-4E80-8WXVubHUyMDEqA1AF250F-5");
			// 接收者邮箱
			String to = toEmail;
			String subject = "云路认证";
			String mailConent = "云路认证";

			HtmlEmail email = new HtmlEmail();
			email.setAuthentication(myEmail, myPassword);
			email.setHostName(host);
			email.addTo(to, "亲爱的用户");
			email.setFrom(myEmail);
			email.setSubject(subject);
			email.setCharset("utf-8");
			email.setHtmlMsg("<html><body>请点击下面代码认证通过<br/><a href=\"http://localhost:8080/YLPLATNEW/userController/emailBindApproval?userid="
					+ userid
					+ "&auth_code="
					+ authcode
					+ "\" alt=\"c1ad9466-f4f4-49be-9ae8-34cb4412df7c86b293db-5b88-4435-a2e9-ef1fb8a6bfec\">"
					+ UUIDUtils.getUUID() + UUIDUtils.getUUID() + "</a></body></html>"); /* 邮件内容 */
			// 添加附件对象
			// email.attach(attachment);
			// 发送
			email.send();
		} catch (Exception e) {
		}
	}
	
	/**
	 * 发送邮件： 面试邀请
	 * 
	 * @param emailMap
	 *            邮件配置信息
	 * @param toEmail
	 *            被邀请人邮箱
	 * @param toName
	 *            被邀请人姓名
	 * @param emailContent
	 *            邮件内容
	 * @return Boolean
	 */
	public static boolean sendInterviewInvite(Map<String,Object> emailMap, String toEmail, String toName, String emailContent) {
		String subject = "面试邀请"; // 主题 
		HtmlEmail email = new HtmlEmail();
		email.setHostName(String.valueOf(emailMap.get("smtp")));	// 设置发信的smtp服务器 
		email.setSubject(subject); 		// 设置邮件主题 
		email.setAuthentication(String.valueOf(emailMap.get("fromEmail")), String.valueOf(emailMap.get("password")));// SMTP服务器认证,设置帐号、密码
		email.setCharset("utf-8");	// 设置邮件字符集 
		try {
			email.addTo(toEmail, toName);	// 设置收件人帐号和收件人
			String yl_username = String.valueOf(emailMap.get("nikeName"));
			email.setFrom(String.valueOf(emailMap.get("fromEmail")),yl_username);	// 设置发信的邮件帐号和发信人
			email.setHtmlMsg(emailContent);	// 设置邮件正文，此方法这里的样式可以显示出来 
			email.send();
			return true;
		} catch (Exception  e) {
			log.error("发送面试邀请邮件失败！邮件地址："+ toEmail, e);
			return false;
		}
	}
	
	public static void main(String[] args,HttpServletRequest request ) {
		emailUtil.changePwd("qhdsoft@126.com", "11", "22",  request);
	}
}