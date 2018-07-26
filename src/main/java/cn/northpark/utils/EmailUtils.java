package cn.northpark.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.HtmlEmail;
import org.apache.log4j.Logger;

/**
 * @author Administrator
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
    public static void analyseLog(String toEmail, String cont) {
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
            LOGGER.error("EmailUtils------->", e);
            ;
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
            String myPassword = Base64Util.JIEMI("emhhbmd5YW5nMjI2MDAwMDAw");
            // 接收者邮箱
            String to = toEmail;
            String subject = "欢迎加入Northpark";


            HtmlEmail email = new HtmlEmail();
            email.setAuthentication(myEmail, myPassword);
            email.setHostName(host);
            email.addTo(to, toEmail);           //对方 邮件+对方名字
            email.setFrom(myEmail, "northpark官方");// 我方 邮件+我方显示名字
            email.setSubject(subject);// 标题
            // 注意，发送内容时，后面这段会让中文正常显示，否则乱码
            email.setCharset("utf-8");
            //email.setContent(aObject, aContentType);
            email.setHtmlMsg("<html><body><style type=\"text/css\">.aboutWrapper{background-image:url(http://o8a5h1k2v.bkt.clouddn.com/16-7-28/64876897.jpg);background-repeat:no-repeat;background-position:center top}.reservationsWrapper{margin-top:70px}.aboutWrapper{background-color:#ebebeb;padding-bottom:40px;position:relative;margin-bottom:350px}.aboutWrapper:before{content:' ';display:block;position:absolute;bottom:-200px;background-image:url(http://o8a5h1k2v.bkt.clouddn.com/16-7-28/34631260.jpg);max-width:100%;left:0;height:291px;width:100%}</style>"
                    + "<div style=\"width: 83.33333333%;text-align:center;background: #f5f5f5;\"><div class=\"aboutWrapper reservationsWrapper\"><div class=\"clearfix margin-b10 center\" style=\"padding-top:30px;\">"
                    + "<h2> Welcome! </h2>"
                    + "<br><br>Welcome join northpark! "
                    + "<br/><br/>"
                    + "sending time:" + TimeUtils.nowTime() + "<br/>" + "<br/>"
                    + "如有任何疑问或顾虑，请联系northpark。<br/><br/>" + "小布<br/><br/>" + "欢迎来我的博客" + "<a href=\"http://blog.northpark.cn\">果冻时刻</a>" + "<br/><br/>"
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
            LOGGER.error("EmailUtils------->", e);
            ;
        }

    }

    /**
     * 找回密码邮件认证
     *
     * @param toEmail
     * @param usrId
     * @param authCode
     */
    public void changePwd(String toEmail, String usrId, String authCode, HttpServletRequest request) {
        try {

            //smtp.qq.com || smtp.163.com
            String host = "smtp.163.com";
            String myEmail = "qhdsoftware@163.com";
            String myPassword = Base64Util.JIEMI("emhhbmd5YW5nMjI2MDAwMDAw");
            // 接收者邮箱
            String to = toEmail;
            String subject = "~~~~(>_<)~~~~找回northpark的密码";

            HtmlEmail email = new HtmlEmail();
            email.setAuthentication(myEmail, myPassword);
            email.setHostName(host);
            email.addTo(to, toEmail);           //对方 邮件+对方名字
            email.setFrom(myEmail, "northpark官方");// 我方 邮件+我方显示名字
            email.setSubject(subject);// 标题
            String dm = "northpark.cn";//URLUtil.getDomain(request);
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
                    + "点击链接以后，密码将被修改为【 <font color=\"red\">" + authCode + "</font>  】,请牢记喔~尽快到个人信息页面修改<br/>  "
                    + "如果不想重置密码，请忽略此邮件，您的密码不会改变。如有任何疑问或顾虑，请联系northpark。<br/><br/>" + "小布<br/><br/>" + "欢迎来我的博客" + "<a href=\"http://blog.northpark.cn\">NorthPark博客</a>" + "<br/><br/>"
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
            LOGGER.error("EmailUtils------->", e);
            ;
        }

    }


    /**
     * 正则验证邮箱格式
     *
     * @param email 邮箱
     * @return Boolean
     */
    public static boolean isEmail(String email) {
        String regex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        return email.matches(regex);
    }


    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
//        list.add("119207145@qq.com");
//        list.add("102757503@qq.com");
//        list.add("1989866937@qq.com");
//        list.add("404250426@qq.com");
//        list.add("1297115491@qq.com");
//        list.add("optrips@gmail.com");
//        list.add("1143991340@qq.com");
//        list.add("2985075841@qq.com");
//        list.add("1401518328@qq.com");
        
        list.add("peiweiyin8866@163.com");
//           list.add("sven777@qq.com");
//           list.add("agnesluilui@gmail.com");
//           list.add("johnathan426830@gmail.com");
        
//          list.add("qazp950337@gmail.com");
//          list.add("1939451983@qq.com");
//          list.add("94534080@qq.com");
//          list.add("1126733645@qq.com");
//          list.add("2574183193@qq.com");
//          list.add("trboyspace@126.com");
//          list.add("a10429003@gmail.com");
//          list.add("928287517@qq.com");

        
//        list.add("GalwayGao@gmail.com");
//        list.add("j6m06vu@gmail.com");
//        list.add("ishigakimakino@gmail.com");
//        list.add("13681286770@163.com");
//        list.add("1349430856@qq.com");
//        list.add("k2mr@foxmail.com");
//        list.add("aold619@yeah.net");
//        list.add("ed1997chang@gmail.com");
//		list.add("2572684952@qq.com");          );
//		list.add("goooob@126.com");
//		list.add("843664866@qq.com");
//		list.add("heartlead@gmail.com");
//		list.add("kevinlin1997@yahoo.com.tw");
//		list.add("1358726405@qq.com");
//		list.add("18374819636@163.com");
//		list.add("40367288@qq.com");
//		list.add("651001768@qq.com");
//		list.add("elsa7788@outlook.com");
//		list.add("mengda1027@qq.com");
//		list.add("49611084@qq.com");
//		list.add("knightli@foxmail.com");
//		list.add("973741142@qq.com");
//		list.add("232475782@qq.com");


        for (int i = 0; i < list.size(); i++) {

            EmailUtils.ThanksReg(list.get(i));
        }
    }
}