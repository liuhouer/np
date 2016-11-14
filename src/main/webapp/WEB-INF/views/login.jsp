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
<link rel="shortcut icon" href="/img/favicon.ico">
<title>NorthPark / Login</title>
<meta name="description" content="登录NorthPark：NorthPark / 记住美好,保留回忆,分享最爱。">
<meta name="keywords" content="登录,NorthPark,最爱,回忆,生活">

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
			  <a href="http://blog.NorthPark.cn/atom.xml" target="_blank" id="icon" title="订阅NorthParkRSS"><img src="/img/rss.png" width="20" height="20" alt="订阅NorthParkRSS"></a>
			</div>
		</div>
		<div class="navbar-collapse collapse mainhead-collapse">
			<ul class="nav mainhead-nav">
				<li><a href="/love" title="一张图片，爱满满的">最爱</a></li>
				<li><a href="/note/list" title="一段歌词，一段回忆">碎碎念</a></li>
				<li><a href="/romeo" title="情圣养成日记">情圣日记</a></li>
				<li class="active"><a href="/signup" title="去注册">登陆</a></li>
			</ul>
		</div>
	</div>
</div>
    
    <div class="clearfix maincontent ">
	    <div class="container">
	    	

        <div class="mainbody" style="margin-top:5em;">

            <div class="align-center bg-white radius-5 padding10 max-width-400 min-width-300">

              <form method="POST" action="/cm/login" accept-charset="UTF-8" role="form" id="loginForm" style="color:#444;" class="form margin-t20">
                <div class="clearfix">
                  <h4>登录 NorthPark</h4>
                  <hr>
                </div>

						<div class="form-group ">
							<label for="loginAccount" class="control-label">您的Email：</label>
							<input id="loginAccount" placeholder="example@gmail.com"
								class="form-control  input-lg  border-light-1 bg-lyellow   radius-0"
								name="email" type="text">
						</div>

						<div class="form-group ">
							<label for="loginPassword" class="control-label">您的密码：</label> 
							<a class="pull-right" href="/cm/forget"><span
								class="glyphicon glyphicon-question-sign"></span> 忘记密码了</a> 
							<input id="loginPassword"
								class="form-control  input-lg  border-light-1 bg-lyellow    radius-0"
								name="password" type="password" value="">
						</div>

						<!-- <div class="checkbox">
							<input name="loginRemember" type="checkbox" value="yes">
							在这台电脑上记住我的登录
						</div> -->
						<input id="redirectURI" name="redirectURI" type="hidden"
							value="${redirectURI} ">
						<div class="form-group">
							<input id="formSubmit" data-activetext="登录 ››"
								class="btn btn-success btn-lg margin-t10 grid50" type="button"
								value="登录" disabled="disabled">
						</div>
						<div id="showResult" class="form-group">
						</div>
						<div class="form-group">
							 <a class="pull-left" href="/signup"><span
								class="glyphicon glyphicon-hand-right"></span> 还没账号，去注册</a>	
						</div>		
						<!-- <span id="qqLoginBtn"></span>
						<script type="text/javascript">
							QC.Login({
								btnId : "qqLoginBtn" //插入按钮的节点id
							});
						</script> -->
					</form>
            </div>
            <br><br>
          </div>
 

	    </div>
	</div>

<%@ include file="/WEB-INF/views/page/common/container.jsp"%>

<script src="/js/page/login.js"></script>

</body></html>