package cn.northpark.utils;

import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;

import com.google.common.collect.Lists;

import cn.northpark.utils.encrypt.EnDecryptUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jeyy
 * 
 */
@Slf4j
public class EmailUtils {
	
	private EmailUtils() {
		
	}
	
	
	private volatile static EmailUtils instance = null;
	
	/**
	 *  双重同步锁模式【volatile出坑】
	 	在对象声明时使用volatile关键字修饰，阻止CPU的指令重排。
	 */
	public static EmailUtils getInstance() {
		if(instance == null) {
			synchronized (EmailUtils.class) {
				if(instance == null) {
					instance = new EmailUtils();
				}
			}
		}
		return instance;
	}
	

    /**
     * 多谢注册northpark
     *
     * @param toEmail
     * @param usrId
     * @param authCode
     */
    public  void ThanksReg(String toEmail) {
        try {

            //smtp.qq.com || smtp.163.com
            String host = "smtp.163.com";
            String myEmail = "qhdsoftware@163.com";
            String myPassword = EnDecryptUtils.diyDecrypt("emhhbmd5YW5nMjI2MDAwMDAw");
            // 接收者邮箱
            String to = toEmail;
            String subject = "欢迎加入Northpark";


            HtmlEmail email = new HtmlEmail();
            email.setHostName(host);
            email.setAuthenticator(new DefaultAuthenticator(myEmail, myPassword));
            email.setSSLOnConnect(true);
            email.setSmtpPort(465);
            email.setFrom(myEmail, "northpark官方");// 我方 邮件+我方显示名字
            email.setSubject(subject);// 标题
            email.addTo(to, toEmail);           //对方 邮件+对方名字
            // 注意，发送内容时，后面这段会让中文正常显示，否则乱码
            email.setCharset("utf-8");
            //email.setContent(aObject, aContentType);
            email.setHtmlMsg(
            		  "<html><body>"
            		+ "<style type=\"text/css\">"
            		+ ".aboutWrapper{background-image:url(http://o8a5h1k2v.bkt.clouddn.com/16-7-28/64876897.jpg);background-repeat:no-repeat;left:0;width:100%;max-width:100%;padding-bottom:40px;position:relative;margin-bottom:350px;background-color:#ebebeb}"
            		+ ".reservationsWrapper{margin-top:70px}"
            		//+ ".aboutWrapper{background-color:#ebebeb;padding-bottom:40px;position:relative;margin-bottom:350px}"
            		+ ".aboutWrapper:before{content:' ';display:block;position:absolute;bottom:-200px;background-image:url(http://o8a5h1k2v.bkt.clouddn.com/16-7-28/34631260.jpg);max-width:100%;left:0;height:291px;width:100%}"
            		+ "</style>"
            		+ "<div style=\"width: 83.33333333%;text-align:center;background: #f5f5f5;\"><div class=\"aboutWrapper reservationsWrapper\"><div class=\"clearfix margin-b10 center\" style=\"padding-top:30px;padding-bottom:100px;\">"
            		+ "<h2> Welcome! </h2>"
            		+ "<br><br>Welcome join northpark! "
            		+ "<br/><br/>"
            		+ "sending time:" + TimeUtils.nowTime() + "<br/>"  
            		+ "<br>如有任何疑问或顾虑，请联系northpark。<br/>"  
            		 + "<br><a href=\"http://blog.northpark.cn\" style=\"text-decoration:none;color:#fff;text-shadow:none;background-color:#45d0c6;border:none;margin-top:10px;-webkit-border-radius:2px;-moz-border-radius:2px;border-radius:2px;padding:10px 16px;font-size:18px;line-height:1.33;border-radius:6px;display:inline-block;margin-bottom:0;font-weight:400;text-align:center;vertical-align:middle;cursor:pointer;background-image:none;border:1px solid transparent;white-space:nowrap;padding:6px 12px;font-size:14px;line-height:1.42857143;border-radius:4px;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none;\">欢迎来NorthPark博客做客</a><br/>"
            		+ "</div></div></div>"

                    + "</body></html>"); /* 邮件内容 */
            // 添加附件对象
            // email.attach(attachment);
            // 发送
            email.send();
            log.info("邮件发送成功");
        } catch (Exception e) {
            // TODO: handle exception
            log.error("邮件发送失败");
            log.error("EmailUtils------->", e);
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
    public void changePwd(String toEmail, String usrId, String authCode) {
        try {

            //smtp.qq.com || smtp.163.com
            String host = "smtp.163.com";
            String myEmail = "qhdsoftware@163.com";
            String myPassword = EnDecryptUtils.diyDecrypt("emhhbmd5YW5nMjI2MDAwMDAw");
            // 接收者邮箱
            String to = toEmail;
            String subject = "~~~~(>_<)~~~~找回northpark的密码";


            HtmlEmail email = new HtmlEmail();
            email.setHostName(host);
            email.setAuthenticator(new DefaultAuthenticator(myEmail, myPassword));
            email.setSSLOnConnect(true);
            email.setSmtpPort(465);
            email.setFrom(myEmail, "northpark官方");// 我方 邮件+我方显示名字
            email.setSubject(subject);// 标题
            email.addTo(to, toEmail);           //对方 邮件+对方名字
            // 注意，发送内容时，后面这段会让中文正常显示，否则乱码
            email.setCharset("utf-8");
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
                    + "<a target=\"_blank\" href=\"https://"
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
            log.info("邮件发送成功");
        } catch (Exception e) {
            // TODO: handle exception
            log.error("邮件发送失败");
            log.error("EmailUtils------->", e);
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

    	List<String> list = Lists.newArrayList();
    	//        list.add("119207145@qq.com");
    	//        list.add("102757503@qq.com");
    	//        list.add("1989866937@qq.com");
    	//        list.add("404250426@qq.com");
    	//        list.add("1297115491@qq.com");
    	//        list.add("optrips@gmail.com");
    	//        list.add("1143991340@qq.com");
    	//        list.add("2985075841@qq.com");
    	//        list.add("1401518328@qq.com");
    	
    	//        list.add("2319113876@qq.com");
    	//        list.add("1275566257@qq.com");
    	//        list.add("maoxiaoyan@biosan.cn");
//                 list.add("hyy_10@126.com");
    	//        list.add("i@iliji.cn");
    	//        list.add("duanxiaokang@me.com");
    	//        list.add("enrike_rokr@hotmail.com");
    	//        list.add("onb28076@nbzmr.com");
    	//        list.add("hujinrong002@gmail.com");
    	//        list.add("mysee@163.com");
    	//        list.add("sa921044@gmail.com");
    	//        list.add("ericey@163.com");
    	//        list.add("m15800749064@163.com");
    	//        list.add("461779062@qq.com");
    	//        list.add("308332275@qq.com");
    	//        list.add("zhangruuly@gmail.com");
    	//        list.add("xp1678@hotmail.com");
    	//        list.add("alexdu828@163.com");
    	//        list.add("2050852573@qq.com");
    	//        list.add("eas11@126.com");
    	//        list.add("lumia096498@live.com");
    	//        list.add("774177432@qq.com");
    	//        list.add("zhengyuexi0303@163.com");
        //        list.add("digi.cn@gmail.com");
    	//        list.add("1598522@qq.com");
    	//        list.add("1973512544@qq.com");
    	//        list.add("adersun@yahoo.com.tw");
    	//        list.add("yatouyizhizai1107@gmail.com");
    	//        list.add("372134690@qq.com");
    	//        list.add("ei698704@gmail.com");
    	//        list.add("wsnrztl@163.com");
    	//        list.add("ls29i44@126.com");
    	//        list.add("missyou_gao@qq.com");
    	//        list.add("todovoe@126.com");
    	//        list.add("809402612@qq.com");
    	//        list.add("2423785202@qq.com");
    	//        list.add("2039374894@qq.com");
		//    	  list.add("kun.peng.chn@gmail.com");
		//    	  list.add("ifung150@gmail.com");
		//    	  list.add("zoe839923@yahoo.com.tw");
    	//		  list.add("xuhanee@163.com");
    	//		  list.add("leonardle@163.com");
    	//		  list.add("paladinwyh@gmail.com");
    	//		  list.add("383476671@qq.com");
    	//		  list.add("822279198@qq.com");
        //    	  list.add("helleritterlc@gmail.com");
        //    	  list.add("1535781975@qq.com");
    	//    	  list.add("chidawei91@gmail.com");
    	//    	  list.add("losnel@163.com");
    	//    	  list.add("1014191671@qq.com");
    	//        list.add("6258232@qq.com");
    	//        list.add("cjh24hjc@gmail.com");
    	//        list.add("4080549@qq.com");
    	//        list.add("602203340@qq.com");
    	//        list.add("xuxianggo@126.com");
    	//        list.add("63191534@qq.com");
    	//        list.add("1607450455@qq.com");
    	//        list.add("22521237@qq.com");
        //		  list.add("1260941756@qq.com");
    	//        list.add("864494871@qq.com");
    	//        list.add("dangtong1101@163.com");
        // 	      list.add("1523971965@qq.com");
        // 	      list.add("lading81734@gmail.com");
    	//        list.add("mondhou@qq.com");
    	//        list.add("522571294@qq.com");    	
    	//    	  list.add("1065122440@qq.com");
    	//		  list.add("ipaint@qq.com");
        //        list.add("dalao.orz@gmail.com");
    	//        list.add("wei014127@hotmail.com"); 
    	//        list.add("121942010@qq.com"); 
    	//        list.add("jameshunter7641@gmail.com"); 
		//    	  list.add("tobeymarshall@qq.com");
		//    	  list.add("goodyuhan123@163.com");
		//    	  list.add("694863929@qq.com");
    	//        list.add("1041093778@sina.com");
    	//        list.add("micro@iberhk.com");
    	//        list.add("ttn8834@gmail.com");
    	//        list.add("xcyofapple@gmail.com");
    	//        list.add("527843212@qq.com");
    	//        list.add("kbecky29@gmail.com");
    	//        list.add("2247395896@qq.com");
    	//        list.add("yang32w@mtholyoke.edu");
    	//        list.add("peiweiyin8866@163.com");
    	//        list.add("sven777@qq.com");
    	//        list.add("agnesluilui@gmail.com");
    	//        list.add("johnathan426830@gmail.com");

    	//        list.add("qazp950337@gmail.com");
    	//        list.add("1939451983@qq.com");
    	//        list.add("94534080@qq.com");
    	//        list.add("1126733645@qq.com");
    	//        list.add("2574183193@qq.com");
    	//        list.add("trboyspace@126.com");
    	//        list.add("a10429003@gmail.com");
    	//        list.add("928287517@qq.com");


    	//        list.add("GalwayGao@gmail.com");
    	//        list.add("j6m06vu@gmail.com");
    	//        list.add("ishigakimakino@gmail.com");
    	//        list.add("13681286770@163.com");
    	//        list.add("1349430856@qq.com");
    	//        list.add("k2mr@foxmail.com");
    	//        list.add("aold619@yeah.net");
    	//        list.add("ed1997chang@gmail.com");
    	//		  list.add("2572684952@qq.com");          );
    	//		  list.add("goooob@126.com");
    	//		  list.add("843664866@qq.com");
    	//		  list.add("heartlead@gmail.com");
    	//		  list.add("kevinlin1997@yahoo.com.tw");
    	//		  list.add("1358726405@qq.com");
    	//		  list.add("18374819636@163.com");
    	//		  list.add("40367288@qq.com");
    	//		  list.add("651001768@qq.com");
    	//		  list.add("elsa7788@outlook.com");
    	//		  list.add("mengda1027@qq.com");
    	//		  list.add("49611084@qq.com");
    	//		  list.add("knightli@foxmail.com");
    	//		  list.add("973741142@qq.com");
    	//		  list.add("232475782@qq.com");

    	//        list.add("r48866@gmail.com");
//    	list.add("r48866@gmail.com");    	
//    	list.add("807876064@qq.com");
//    	list.add("370429633@qq.com");
//    	list.add("pollux.liu@gmail.com");
//    	list.add("609426404@qq.com");
//    	list.add("860541352@qq.com");
//    	list.add("924930177@qq.com");
//    	list.add("370524420@qq.com");
//    	list.add("1436832217@qq.com");
//    	list.add("1933467704@qq.com");
//    	list.add("18006357629@163.com");
//    	list.add("291855752@qq.com");
//    	list.add("a13956193378@outlook.com");
//    	list.add("liarmirror@qq.com");
//    	list.add("1964028093@qq.com");
//    	list.add("609222321@qq.com");
//    	list.add("171759794@qq.com");
//    	list.add("cowxu1207@hotmail.com");
//    	list.add("1073832321@qq.com");
//    	list.add("3864491@qq.com");
//    	list.add("204995183@qq.com");
//    	list.add("18599997486@163.com");
//    	list.add("cvzxcv2@q.com");
//    	list.add("zaojunfan@126.com");
//    	list.add("654812205@qq.com");
//    	list.add("1322851217@qq.com");
//    	list.add("zhaolx2121@126.com");
//    	list.add("kanetoku@gmail.com");
//    	list.add("liaisky@163.com");
//    	list.add("2528032002@qq.com");
//    	list.add("pologer@126.com");
//    	list.add("matthewlok18c@gmail.com");
//    	list.add("24919010@qq.com");
//    	list.add("172238529@qq.com");
//    	list.add("2324309528@qq.com");
//    	list.add("th241@163.com");
//    	list.add("494209219@qq.com");
//    	list.add("mofaliren@gmail.com");
//    	list.add("769932247@qq.com");
//    	list.add("812542106@qq.com");
//    	list.add("myleonardo@qq.com");
//    	list.add("jixing475@gmail.com");
//    	list.add("89281944@163.com");
//    	list.add("776362265@qq.com");
//    	list.add("24959248@qq.com");
//    	list.add("312910298@sohu.com");
//    	list.add("lijiadong14@gail.com");
//    	list.add("270753031@qq.com");
//    	list.add("qlkdsalkl@shit.com");  
    	
    	
//    	list.add("lightinfection@sina.com");
//    	list.add("carolding1013@163.com");
//    	list.add("hhhzy113@gmail.com");
//    	list.add("fnftfe@163.com");
//    	list.add("1353131605@qq.com");
//    	list.add("511068174@qq.com");
//    	list.add("yzfd2013@qq.com");
//    	list.add("382215239@qq.com");
//    	list.add("hjkl345672655@yahoo.com.tw");
//    	list.add("253019169@qq.com");
//    	list.add("616012777@qq.com");
//    	list.add("475124896@qq.com");
    	
    	
    	for (int i = 0; i < list.size(); i++) {
    		EmailUtils.getInstance().ThanksReg(list.get(i));
    	}
    }
}