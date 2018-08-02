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
    <link rel="shortcut icon" href="/img/favicon.ico">
    <title>Signup | NorthPark</title>
    <meta name="keywords" content="NorthPark,注册">
    <meta name="description"
          content="加入NorthPark">

    <link media="all" type="text/css" rel="stylesheet" href="/css/login/owl-login.css">
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
                <a href="http://blog.NorthPark.cn/atom.xml" target="_blank" id="icon" title="订阅NorthParkRSS"><img
                        src="./img/rss.png" width="20" height="20" alt="订阅NorthPark博客RSS"></a>
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

<footer class="mainfoot center ">

    <div class="row">
        <div class="col-md-12">
            <p1>
                2014 © <span class="glyphicon glyphicon-heart"></span> NorthPark. ALL Rights Reserved.
            </p1>
            <p2>
                <a target="_blank" href="http://blog.NorthPark.cn" rel="nofollow" title="NorthPark博客">NorthPark博客</a>
                <a target="_blank" href="/poem/index.html" title="诗词秀">诗词秀</a>
                <a target="_blank" href="/vps" title="优惠vps、vps测评">优惠VPS<span class="badge green-badge">new</span></a>
                <a target="_blank" href="/romeo" title="情圣时刻">情商提升</a>
                <a target="_blank" href="/cm/xbjt" title="原创音乐播放器">小布静听</a>
                <a target="_blank" href="/about.html" title="关于NorthPark" class="aend">关于NorthPark</a>
                <!-- <a target="_blank"  href="/wx/astro" title="星座运势、人性化的美少女塔罗牌消息私人订制"  class="aend" >星座美少女</a> -->

            </p2>
        </div>
    </div>

</footer>

<script src="/js/jquery-1.7.2.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/artDialog/artDialog.js?skin=default"></script>
<script src="/js/artDialog/jquery.artDialog.js?skin=default"></script>
<script src="/js/artDialog/plugins/iframeTools.js"></script>
<script src="/js/page/seltab.js"></script>
<script>
    (function (i, s, o, g, r, a, m) {
        i['GoogleAnalyticsObject'] = r;
        i[r] = i[r] || function () {
            (i[r].q = i[r].q || []).push(arguments)
        }, i[r].l = 1 * new Date();
        a = s.createElement(o),
            m = s.getElementsByTagName(o)[0];
        a.async = 1;
        a.src = g;
        m.parentNode.insertBefore(a, m)
    })(window, document, 'script', 'https://www.google-analytics.com/analytics.js', 'ga');

    ga('create', 'UA-87600630-1', 'auto');
    ga('send', 'pageview');

</script>

<!-- baidu auto push -->
<script>
    (function () {
        var bp = document.createElement('script');
        var curProtocol = window.location.protocol.split(':')[0];
        if (curProtocol === 'https') {
            bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
        }
        else {
            bp.src = 'http://push.zhanzhang.baidu.com/push.js';
        }
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(bp, s);
    })();
</script>

<script src="/js/page/reg2.js"></script>

</body>
</html>