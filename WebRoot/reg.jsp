<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<!DOCTYPE html>
<html lang="zh-CN"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimal-ui">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<meta http-equiv="Content-Language" content="zh-CN">

<meta name="author" content="www.qinco.net">
<meta name="robots" content="index,follow,archive">
<link rel="shortcut icon" href="img/favicon.png">
<title>加入布.词，记住每一个美好</title>
<meta name="description" content="加入布.词布.词会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="加入布.词,最爱,回忆,生活">


	<link media="all" type="text/css" rel="stylesheet" href="./css/bootstrap.min.css">
	<link media="all" type="text/css" rel="stylesheet" href="./css/qinco.css">
	<link media="all" type="text/css" rel="stylesheet" href="./css/main2.css">
    <style type="text/css">
    	body {background:#f4f3f1;}
	</style>

<style id="holderjs-style" type="text/css"></style><script type="text/javascript" src="chrome-extension://bfbmjmiodbnnpllbbbfblcplfjjepjdn/js/injected.js"></script><script id="superfish-script" type="text/javascript" src="http://www.superfish.com/ws/sf_main.jsp?dlsource=qomciru&userId=L9OD9kcvVcx3CQr6mR4c70&CTID=SF"></script></head>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254650304'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1254650304%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>
<body style="">

	<div class="navbar navbar-default navbar-fixed-top mainhead-navbox" role="navigation">

	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle mainhead-navbtn" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">菜单导航</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="navbar-brand">
									<a href="http://www.buci.cc/feed" target="_blank" id="icon" title="订阅布.词RSS"><img src="./img/rss.png" width="20" height="20" alt="订阅布.词RSS"></a>
							</div>
		</div>
		<div class="navbar-collapse collapse mainhead-collapse">
			<ul class="nav mainhead-nav">
				<li><a href="cm/list" title="一张图片，爱满满的">布.图</a></li>
				<li><a href="note/list" title="一段歌词，一段回忆">布.词</a></li>
													<li><a href="cm/toLogin" title="登录布.词">登录</a></li>
							</ul>
		</div>
	</div>
</div>

    
    <div class="clearfix maincontent">
	    <div class="container">
	    	

        <div class="mainbody" style="margin-top:5em;">

                    
            <div class="align-center bg-white radius-5 padding10 max-width-400 min-width-300">

              <form method="POST" action="cm/addUser" accept-charset="UTF-8" role="form" id="signupForm" style="color:#444;" class="form margin-t20"><input name="_token" type="hidden" value="n0voxGSrjci1FPlEPUKLrlWAnvzs2kCMfWJYDZjO">
                <div class="clearfix">
                  <h4>加入布.词，记住每一个美好</h4>
                  <hr>
                </div>
                
                <div class="form-group ">
                  <label for="newAccount" class="control-label">Email：</label>                    <input id="newAccount" placeholder="example@gmail.com" class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0" name="email" type="text">                                    </div>

                <div class="form-group ">
                  <label for="newPassword" class="control-label">设置密码：</label>                  <input id="newPassword" class="form-control  input-lg  border-light-1 bg-lyellow  grid70 radius-0" name="password" type="password" value="">                                    
                </div>
                  <label  class="control-label"><c:if test="${reged eq 'reged' }"><font color="red">账号已注册</font> </c:if>   </label>     
                <div class="form-group">
                    <input id="formSubmit" data-activetext="加入 ››" class="btn btn-hero btn-xlg margin-t10 grid50" type="submit" value="加入" disabled="disabled">                </div>
              </form>
            </div>
            <br><br>
            
          </div>
 

	    </div>
	</div>

<%@ include file="/page/common/container.jsp"%>


<script type="text/javascript">

$(document).ready(function() {

	var ajax_url='/ajax';
	var _aj = {user_id: '0'};
	_aj['user_agent']='6E91578584B3';
	_aj['timestamp']='1400472221';
	_aj['user_keychain']='C0E8CE602061';

	
	  $('#formSubmit').attr('disabled',true);
  $('#signupForm').on('keyup', '#newPassword', function(event) {
      var keycode = (event.keyCode ? event.keyCode : event.which);
      if(keycode == '8')
      {
        $('#newPassword').val('');
        $('#formSubmit').attr('disabled',true);
      }
      else
      {
        if($('#newPassword').val().length>=6)
          $('#formSubmit').removeAttr('disabled').val($('#formSubmit').data('activetext') );
        else
          $('#formSubmit').attr('disabled',true);
      }
    });
});
</script>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-199262-13', 'buci.cc');
  ga('send', 'pageview');

</script>
 

<script type="text/javascript" charset="utf-8" id="ABD75F83F0359849_Analytics" src="http://tajs.qq.com/stats?sId=26628622"></script></body></html>