<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!-- saved from url=(0030)myself.jsp -->
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
    <title>火星救援 | NorthPark</title>
    <meta name="keywords" content="NorthPark,NorthPark中文网,NorthPark500">
    <meta name="description"
          content="NorthPark出了一些问题。。。。构建中">
    <%@ include file="/WEB-INF/views/page/common/common.jsp" %>
    <link media="all" type="text/css" rel="stylesheet" href="/css/bu.css">


</head>

<body style="">
<%@ include file="/WEB-INF/views/page/common/navigation.jsp" %>

<div class="clearfix maincontent ">
    <div class="container">
        <div class="mainbody" style="margin-top:10em;">
            <div class="align-center  radius-5  max-width-800 min-width-600">
                <div class="col-sm-6 col-sm-offset-3 ">
                    <div class="row ">
                        <p>
                            你所寻找的东西穿越到了火星需要救援。( ＾o)ρ┳┻┳°σ(o^ )
                        </p>
                        <p>
                            <span id="mes" style="cursor: pointer;"><font color="#49c7be"><img src='/img/loading.gif'
                                                                                               style='width:16px;height:16px;'/>返回首页</font></span>
                        </p>
                        <p>
                            <canvas id="c"></canvas>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<%@ include file="/WEB-INF/views/page/common/container.jsp" %>
<script src="/js/page/error.js"></script>


</body>
</html>
