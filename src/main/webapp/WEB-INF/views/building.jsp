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
    <title>星际穿越 | NorthPark</title>
    <meta name="keywords" content="NorthPark中文网,NorthPark,NorthPark404">
    <meta name="description"
          content="NorthPark找不到这个地址。。。。请稍后再试">
    <%@ include file="/WEB-INF/views/page/common/common.jsp" %>

</head>

<body class="">
<%@ include file="/WEB-INF/views/page/common/navigation.jsp" %>

<div class="clearfix maincontent ">
    <div class="container">
        <div class="mainbody" style="margin-top:10em;">
                <div class="col-sm-12  center">
                        

						<img src="/img/404.jpg"></img>



						<p class="center">
							<span id="mes" style="cursor: pointer;"><font
								color="#49c7be"><img src='/img/loading.gif' alt="星际穿越"
									style='width: 16px; height: 16px;' /> 返回首页</font></span>
						</p>

                </div>
        </div>
    </div>
</div>


<%@ include file="/WEB-INF/views/page/common/container.jsp" %>



<script type="text/javascript">

$(function () {
    $("#mes").click(function () {
        window.location.href = "/";
    })
})

</script>
</body>
</html>
