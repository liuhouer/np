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
<title>Signup | NorthPark</title>
<meta name="keywords" content="NorthPark,Mac软件,影视窝,碎碎念,最爱主题图册和情商提升兼具文艺范和小清新">
<meta name="description" content="NorthPark是一个很小清新的互动公园。NorthPark包含了丰富的Mac软件资源、影视窝包含最新的影视剧资源、情商提升的技巧和讲解、碎碎念的精神角落、最爱主题图册互动、评论、关注等版块。它富有交互性和趣味性，文艺范和小清新，并且可以和你的朋友们为某个兴趣互动。">

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
				<a href="http://blog.NorthPark.cn/atom.xml" target="_blank" id="icon" title="订阅NorthParkRSS"><img src="./img/rss.png" width="20" height="20" alt="订阅NorthPark博客RSS"></a>
			</div>
		</div>
		<div class="navbar-collapse collapse mainhead-collapse">
			<ul class="nav mainhead-nav">
				<li><a href="/movies" title="包含最新的影视剧资源">影视窝</a></li>
				<li><a href="/soft/mac" title="丰富的Mac软件资源">Mac软件</a></li>
				<li><a href="/love" title="最爱主题图册">最爱</a></li>
				<li><a href="/note/list" title="碎碎念的精神角落">碎碎念</a></li>
				<li class="active"><a href="/login" title="已有账号，去登录NorthPark">注册</a></li>
			</ul>
		</div>
	</div>
</div>
    
    <!-- 页面标题 -->
	<h1  class="font-elegant">Signup</h1>
	
    <div class="clearfix maincontent ">
	    <div class="container">
	    	

          
          <!-- begin -->
			<div id="login">
			    <div class="wrapper">
			        <div class="login">
			            <form id="signupForm"  action="/cm/addUser" method="post" class="container offset1 loginform">
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
			                            <label for="newAccount" class="control-label fa fa-envelope"></label>
			                            <input id="newAccount" type="email" name="email" placeholder="Email" tabindex="1" autofocus="autofocus" class="form-control input-medium">
			                        </div>
			                    </div>
			                    <div class="control-group">
			                        <div class="controls">
			                            <label for="newPassword" class="control-label fa fa-asterisk"></label>
			                            <input id="newPassword" type="password" name="password" placeholder="Password" tabindex="2" class="form-control input-medium">
			                        </div>
			                    </div>
				                <input id="redirectURI" name="redirectURI" type="hidden"
								value="${redirectURI} ">
				                <div id="showResult" class="control-group center margen-b10">
									 <c:if test="${reged eq 'reged' }"><font color="red">账号已注册</font> </c:if>
								</div>
			                </div>
			                <div class="form-actions">
			                <a href="/login" tabindex="6" class="btn btn-link text-muted">Login</a>
			                    <button id="formSubmit" type="submit" tabindex="4" class="btn btn-primary">Join</button>
			                </div>
			            </form>
			        </div>
			    </div>
			   
			</div>
			<!-- end -->
 

	    </div>
	</div>

<%@ include file="/WEB-INF/views/page/common/container.jsp"%>

<script src="/js/page/reg2.js"></script>

</body></html>