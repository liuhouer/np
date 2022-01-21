<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimal-ui">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <meta http-equiv="Content-Language" content="zh-CN">

    <meta name="author" content="www.qinco.net">
    <meta name="robots" content="index,follow,archive">
    <link rel="shortcut icon" href="https://northpark.cn/statics/img/favicon.ico">
    <title>Signup | NorthPark</title>
    <meta name="keywords" content="NorthPark,注册">
    <meta name="description"
          content="加入NorthPark">

    <link media="all" type="text/css" rel="stylesheet" href="https://northpark.cn/statics/css/login/owl-login.css">
    <%@ include file="/WEB-INF/views/page/common/common.jsp" %>

</head>

<body>

<div class="navbar navbar-default navbar-fixed-top mainhead-navbox" role="navigation">

    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle mainhead-navbtn" data-toggle="collapse"
                    data-target=".navbar-collapse">
                <span class="sr-only">菜单导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="navbar-brand">
                <a href="/" title="首页" class="btn-xs">
                    <span class="fa fa-home fa-lg padding-t10 padding-l5"></span>
                </a>

                <a href="http://blog.NorthPark.cn/atom.xml" target="_blank" id="icon" title="订阅NorthParkRSS" class="btn-xs">
                    <i class="fa fa-rss-square  fa-lg padding-t10  padding-l5"></i>
                </a>

            </div>
        </div>
        <div class="navbar-collapse collapse mainhead-collapse">
            <ul class="nav mainhead-nav">
                <li><a href="/movies" title="包含最新的影视剧资源"><i class="fa fa-film padding5"></i>影视</a></li>
                <li><a href="/soft/mac" title="丰富的Mac软件资源"><i class="fa fa-apple padding5"></i>软件</a></li>
                <li><a href="/learning" title="学习/课程/书籍/知识"><i class="fa fa-graduation-cap padding5"></i>学习</a></li>
                <li><a href="/love" title="最爱主题图册"><i class="fa fa-picture-o padding5"></i>最爱</a></li>
                <li><a href="/note" title="碎碎念的精神角落"><i class="fa fa-comment padding5"></i>树洞</a> </li>
                <li class="active"><a href="/login" title="已有账号，去登录NorthPark"><i class="fa fa-registered padding5"></i>注册</a></li>
            </ul>
        </div>
    </div>
</div>

<!-- 页面标题 -->
<h1 class="font-elegant">Signup</h1>

<div class="maincontent " style="min-height: 620px;">
    <div class="container">


        <!-- begin -->
        <div id="login">
            <div class="wrapper">
                <div class="login">
                    <form id="signupForm" action="/cm/addUser" method="post" class="container offset1 loginform">
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
                                    <input id="newAccount" type="email" name="email" placeholder="Email" tabindex="1"
                                           autofocus="autofocus" class="form-control input-medium">
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="controls">
                                    <label for="newPassword" class="control-label fa fa-asterisk"></label>
                                    <input id="newPassword" type="password" name="password" placeholder="Password"
                                           tabindex="2" class="form-control input-medium">
                                </div>
                            </div>
                            <input id="redirectURI" name="redirectURI" type="hidden"
                                   value="${redirectURI} ">
                            <div id="showResult" class="control-group center margen-b10">
                            </div>
                        </div>
                        <div class="form-actions">
                            <a href="/login" tabindex="6" class="btn btn-link text-muted">Login</a>
                            <button id="formSubmit" type="button" tabindex="4" class="btn btn-primary">Join</button>
                        </div>
                    </form>
                </div>
            </div>

        </div>
        <!-- end -->


    </div>
</div>

<%@ include file="/WEB-INF/views/page/common/container.jsp" %>
<script  data-cfasync="false" src="https://northpark.cn/statics/js/page/reg2.js"></script>

</body>
</html>