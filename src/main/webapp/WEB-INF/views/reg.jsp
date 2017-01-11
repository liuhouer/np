<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<meta name="description" content="NorthPark,文艺,小清新,Mac软件,影视窝,碎碎念,图册,情圣,情商提升">
<meta name="keywords" content="NorthPark是一个很小清新的互动公园。NorthPark包含了丰富的Mac软件资源、最新的影视剧资源、大量提升男生情商的文章、大家吐槽的，喜爱的，心情日记的精神角落、图册互动等版块。它富有交互性和趣味性，文艺范和小清新，并且可以和你的朋友们为某个兴趣互动。">



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
				<li><a href="/love" title="一张图片，爱满满的">最爱</a></li>
				<li><a href="note/list" title="一段歌词，一段回忆">碎碎念</a></li>
				<li><a href="/romeo" title="情圣养成日记">情圣日记</a></li>
				<li class="active"><a href="/login" title="已有账号，去登录NorthPark">注册</a></li>
			</ul>
		</div>
	</div>
</div>

    
    <div class="clearfix maincontent">
	    <div class="container">
	    	

        <div class="mainbody" style="margin-top:5em;">

                    
            <div class="align-center bg-white radius-5 padding10 max-width-400 min-width-300">

              <form method="POST" action="/cm/addUser" accept-charset="UTF-8" role="form" id="signupForm" style="color:#444;" class="form margin-t20">
                <div class="clearfix">
                  <h4>加入 NorthPark</h4>
                  <hr>
                </div>
                
                <div class="form-group ">
                  <label for="newAccount" class="control-label">Email：</label> 
 				  <input id="newAccount" placeholder="example@gmail.com" 
 				  class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0" name="email" type="text">
 				</div>

                <div class="form-group ">
                  <label for="newPassword" class="control-label">设置密码：</label>
                  <input id="newPassword" placeholder="密码"  class="form-control  input-lg  border-light-1 bg-lyellow  grid98 radius-0" name="password" type="password" value="">                                    
                </div>
                
                <label  class="control-label"><c:if test="${reged eq 'reged' }"><font color="red">账号已注册</font> </c:if>   </label>     
                <div class="form-group">
                    <input id="formSubmit" data-activetext="加入 ››" class="btn btn-hero btn-xlg margin-t10 grid50" type="submit" value="加入" disabled="disabled"> 
                </div>
                
                <div class="form-group">
							 <a class="pull-left" href="/login"><span
								class="glyphicon glyphicon-hand-right"></span> 已有账号，去登陆</a>	
				</div>	
              </form>
            </div>
            <br><br>
            
          </div>
 

	    </div>
	</div>

<%@ include file="/WEB-INF/views/page/common/container.jsp"%>


<script src="/js/page/reg.js"></script>

 

</body></html>