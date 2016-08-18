<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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
<link rel="shortcut icon" href="img/favicon.ico">
<title>northpark / Login</title>
<meta name="description" content="登录northpark：northpark / 记住美好,保留回忆,分享最爱。">
<meta name="keywords" content="登录,northpark,最爱,回忆,生活">

<link media="all" type="text/css" rel="stylesheet" href="/css/login/owl-login.css">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>


</head>

<body >

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
			  <a href="http://blog.northpark.cn/atom.xml" target="_blank" id="icon" title="订阅northparkRSS"><img src="/img/rss.png" width="20" height="20" alt="订阅northparkRSS"></a>
			</div>
		</div>
		<div class="navbar-collapse collapse mainhead-collapse">
			<ul class="nav mainhead-nav">
				<li><a href="/love" title="一张图片，爱满满的">爱的随笔</a></li>
				<li><a href="/note/list" title="一段歌词，一段回忆">碎碎念</a></li>
				<li><a href="/romeo" title="情圣养成日记">情圣日记</a></li>
				<li class="active"><a href="/signup" title="去注册">登陆</a></li>
			</ul>
		</div>
	</div>
</div>
    
    <div class="clearfix maincontent ">
	    <div class="container">
	    	
          <!-- begin -->
			<div id="login">
			    <div class="wrapper">
			        <div class="login">
			            <form id="loginForm"  action="cm/login" method="post" class="container offset1 loginform">
			                <div id="owl-login">
			                    <div class="hand"></div>
			                    <div class="hand hand-r"></div>
			                    <div class="arms">
			                        <div class="arm"></div>
			                        <div class="arm arm-r"></div>
			                    </div>
			                </div>
			                <div class="pad">
			                    <div class="control-group">
			                        <div class="controls">
			                            <label for="email" class="control-label fa fa-envelope"></label>
			                            <input id="email" type="email" name="email" placeholder="Email" tabindex="1" autofocus="autofocus" class="form-control input-medium">
			                        </div>
			                    </div>
			                    <div class="control-group">
			                        <div class="controls">
			                            <label for="password" class="control-label fa fa-asterisk"></label>
			                            <input id="password" type="password" name="password" placeholder="Password" tabindex="2" class="form-control input-medium">
			                        </div>
			                    </div>
				                <input id="redirectURI" name="redirectURI" type="hidden"
								value="${redirectURI} ">
				                <div id="showResult" class="control-group center margen-b10">
								</div>
			                </div>
			                <div class="form-actions">
			                <a href="/cm/forget" target="_blank" tabindex="5" class="btn pull-left btn-link text-muted">Forgot password?</a>
			                <a href="/signup" tabindex="6" class="btn btn-link text-muted">Sign Up</a>
			                    <button id="formSubmit" type="button" tabindex="4" class="btn btn-primary">Login</button>
			                </div>
			            </form>
			        </div>
			    </div>
			   
			</div>
			<!-- end -->
 

	    </div>
	</div>

<%@ include file="/WEB-INF/views/page/common/container.jsp"%>

<script src="/js/page/login2.js"></script>

</body></html>