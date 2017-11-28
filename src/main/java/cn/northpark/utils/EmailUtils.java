package cn.northpark.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

/**
 * @author Administrator
 *
 */
public class EmailUtils {
	private static final Logger LOGGER = Logger
	            .getLogger(EmailUtils.class);
	public static final EmailUtils emailUtil = new EmailUtils();
	
	/**
	 * 日志分析auto发送
	 * 
	 * @param toEmail
	 * @param usrId
	 * @param authCode
	 */
	public static void analyseLog(String toEmail,String cont) {
		try {

			String host = "smtp.163.com";
			String myEmail = "qhdsoftware@163.com";
			String myPassword = Base64Util.JIEMI("MTM0ODM3MjQwNTEwMDAwMDA=");
			// 接收者邮箱
			String to = toEmail;
			String subject = "日志分析";

			HtmlEmail email = new HtmlEmail();
			email.setAuthentication(myEmail, myPassword);
			email.setHostName(host);
			email.addTo(to, "亲");
			email.setFrom(myEmail);
			email.setSubject(subject);
			// 注意，发送内容时，后面这段会让中文正常显示，否则乱码
			email.setCharset("utf-8");
			email.setHtmlMsg(cont); /* 邮件内容 */
			// 添加附件对象
			// email.attach(attachment);
			// 发送
			email.send();
			LOGGER.info("邮件发送成功");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("EmailUtils------->", e);;
			LOGGER.info("邮件发送失败");
		}

	}
	
	
	/**
	 * 多谢注册northpark
	 * 
	 * @param toEmail
	 * @param usrId
	 * @param authCode
	 */
	public static void ThanksReg(String toEmail) {
		try {

			//smtp.qq.com || smtp.163.com
			String host = "smtp.163.com";
			String myEmail = "qhdsoftware@163.com";
			String myPassword = "bruce134";
			// 接收者邮箱
			String to = toEmail;
			String subject = "欢迎加入Northpark";
			
			//开启ssl加密
//			Properties prop = new Properties();
//			// 设置SMTP发送服务器的名字：163的如下："smtp.163.com"
//			prop.setProperty("mail.smtp.host", host);
//			// 设置SMTP发送服务器的端口
//			prop.setProperty("mail.smtp.port", "25");
//			// 设置是否需要认证
//			prop.setProperty("mail.smtp.auth", "true");
//			// 开启TLS加密方式
//			prop.setProperty("mail.smtp.starttls.enable", "true");
//			// 添加信任的服务器
//			prop.setProperty("mail.smtp.ssl.trust", host);
//			// 进行认证并获取需要的session
//			DefaultAuthenticator defaultAuthenticator = 
//			        new DefaultAuthenticator(myName, myPassword);
//			Session session = Session.getInstance(prop,defaultAuthenticator);

			HtmlEmail email = new HtmlEmail();
//			email.setMailSession(session);
			email.setAuthentication(myEmail, myPassword);
			email.setHostName(host);
			email.addTo(to, toEmail);           //对方 邮件+对方名字
			email.setFrom(myEmail,"northpark官方");// 我方 邮件+我方显示名字 
			email.setSubject(subject);// 标题  
			// 注意，发送内容时，后面这段会让中文正常显示，否则乱码
			email.setCharset("utf-8");
			//email.setContent(aObject, aContentType);
			email.setHtmlMsg("<html><body><style type=\"text/css\">.aboutWrapper{background-image:url(http://o8a5h1k2v.bkt.clouddn.com/16-7-28/64876897.jpg);background-repeat:no-repeat;background-position:center top}.reservationsWrapper{margin-top:70px}.aboutWrapper{background-color:#ebebeb;padding-bottom:40px;position:relative;margin-bottom:350px}.aboutWrapper:before{content:' ';display:block;position:absolute;bottom:-200px;background-image:url(http://o8a5h1k2v.bkt.clouddn.com/16-7-28/34631260.jpg);max-width:100%;left:0;height:291px;width:100%}</style>"
					+ "<div style=\"width: 83.33333333%;text-align:center;background: #f5f5f5;\"><div class=\"aboutWrapper reservationsWrapper\"><div class=\"clearfix margin-b10 center\" style=\"padding-top:30px;\">"
					+ "<h2> Welcome! </h2>"
					+ "<br><br>Welcome join northpark! "
					+ "<br/><br/>"
					+ TimeUtils.nowTime()+"<br/>"+"<br/>"
					+"如有任何疑问或顾虑，请联系northpark。<br/><br/>" + "小布<br/><br/>"+"欢迎来我的博客"+"<a href=\"http://blog.northpark.cn\">果冻时刻</a>"+"<br/><br/>" 
					+ "</div></div></div>"
					
					+ "</body></html>"); /* 邮件内容 */
			// 添加附件对象
			// email.attach(attachment);
			// 发送
			email.send();
			LOGGER.info("邮件发送成功");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("邮件发送失败");
			LOGGER.error("EmailUtils------->", e);;
		}

	}
	
	/**
	 * 找回密码邮件认证
	 * 
	 * @param toEmail
	 * @param usrId
	 * @param authCode
	 */
	public  void changePwd(String toEmail, String usrId, String authCode,HttpServletRequest request) {
		try {

			//smtp.qq.com || smtp.163.com
			String host = "smtp.qq.com";
			String myEmail = "654714226@qq.com";
			String myPassword = Base64Util.JIEMI("emhhbmd5YW5nMjI2QDAwMDAwMA==");
			// 接收者邮箱
			String to = toEmail;
			String subject = "~~~~(>_<)~~~~找回northpark的密码";

			HtmlEmail email = new HtmlEmail();
			email.setAuthentication(myEmail, myPassword);
			email.setHostName(host);
			email.addTo(to, "toEmail");           //对方 邮件+对方名字
			email.setFrom(myEmail,"northpark官方");// 我方 邮件+我方显示名字 
			email.setSubject(subject);// 标题  
			String dm =  "northpark.cn";//URLUtil.getDomain(request);
			// 注意，发送内容时，后面这段会让中文正常显示，否则乱码
			email.setCharset("utf-8");
			email.setHtmlMsg("<html><body><style type=\"text/css\">.aboutWrapper{background-image:url(http://o8a5h1k2v.bkt.clouddn.com/16-7-28/64876897.jpg);background-repeat:no-repeat;background-position:center top}.reservationsWrapper{margin-top:70px}.aboutWrapper{background-color:#ebebeb;padding-bottom:40px;position:relative;margin-bottom:350px}.aboutWrapper:before{content:' ';display:block;position:absolute;bottom:-150px;background-image:url(http://o8a5h1k2v.bkt.clouddn.com/16-7-28/34631260.jpg);max-width:100%;left:0;height:291px;width:100%}</style>"
					+ "<div style=\"width: 83.33333333%;text-align:center;background: #f5f5f5;\"><div class=\"aboutWrapper reservationsWrapper\"><div class=\"clearfix margin-b10 center\" style=\"padding-top:10px;\">"
					+ "<div style=\"padding-top:10%;\">"
					+ "<h2>Northpark</h2>"
					+ "<br/><br/>"
					+ "【northpark官方】在"
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
					+ "如果不想重置密码，请忽略此邮件，您的密码不会改变。如有任何疑问或顾虑，请联系northpark。<br/><br/>" + "小布<br/><br/>"+"欢迎来我的博客"+"<a href=\"http://blog.northpark.cn\">果冻时刻</a>"+"<br/><br/>" 
					+ "</div></div></div></div>"
					+ "</body></html>"); /* 邮件内容 */
			// 添加附件对象
			// email.attach(attachment);
			// 发送
			email.send();
			LOGGER.info("邮件发送成功");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("邮件发送失败");
			LOGGER.error("EmailUtils------->", e);;
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


	
	public static void main(String[] args ) {
		
		List<String> list  = new ArrayList<String>();
		list.add("qhdsoft@yeah.net");
		list.add("870300572@qq.com");
		list.add("2572684952@qq.com");
		list.add("goooob@126.com");
		list.add("843664866@qq.com");
		list.add("heartlead@gmail.com");
		list.add("kevinlin1997@yahoo.com.tw");
		list.add("1358726405@qq.com");
		list.add("18374819636@163.com");
		list.add("40367288@qq.com");
		list.add("651001768@qq.com");
		list.add("elsa7788@outlook.com");
		list.add("mengda1027@qq.com");
		list.add("49611084@qq.com");
		list.add("knightli@foxmail.com");
		list.add("973741142@qq.com");
		list.add("232475782@qq.com");
		
		
		for (int i = 0; i < list.size(); i++) {
			
			EmailUtils.ThanksReg(list.get(i));
		}
	}
}