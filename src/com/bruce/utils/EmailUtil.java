package com.bruce.utils;

import org.apache.commons.mail.*;

/**
 * 使用apache mail开源项目发送邮件
 * 
 * @author bruce
 */
public class EmailUtil {
	// 程序主方法
	public static void main(String[] args) throws Exception {
		EmailUtil as = new EmailUtil();
		String host = "smtp.163.com";
		String from = "qhdlhe@163.com";
		String username = "qhdlhe@163.com";
		String password = "13483724051";
		// 接收者邮箱
		String to = "1007136434@qq.com";
		String subject = "你大爷";
		String mailConent = "我是你大爷";
		// 调用发送附件邮件方法
		as.sendAttachmentMail(host, from, username, password, to, subject,
				mailConent);
	}

	public boolean sendAttachmentMail(String host, String from,
			String username, String password, String to, String subject,
			String mailConent) throws Exception {
		// 创建附件对象
		// EmailAttachment attachment = new EmailAttachment();
		// /*附件的地址*/
		// attachment.setPath("E://commons-email-1.0.rar");
		// //设定为附件
		// attachment.setDisposition(EmailAttachment.ATTACHMENT);
		// /*附件的描述*/
		// attachment.setDescription("jPortMap项目设计附件文档");
		// /*附件的名称，必须和文件名一致*/
		// attachment.setName("Eclipse中文教程.pdf");
		/* new一个HtmlEmail发送对象 */
		HtmlEmail email = new HtmlEmail();
		email.setAuthentication(username, password);
		email.setHostName(host);
		email.addTo(to, from);
		email.setFrom(from);
		email.setSubject(subject);
		// 注意，发送内容时，后面这段会让中文正常显示，否则乱码
		email.setCharset("GB2312");
		email.setHtmlMsg("<html>我是你大爷，哈哈，测试成功</html>"); /* 邮件内容 */
		// 添加附件对象
		// email.attach(attachment);
		// 发送
		email.send();
		System.out.println("带符件的邮件发送成功！");
		return true;
	}
}