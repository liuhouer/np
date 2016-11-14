<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0030)myself.jsp -->
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
<title>NorthPark / 火星救援</title>
<meta name="description" content="${user.username}生命中的最爱: NorthPark / 记住美好,保留回忆,分享最爱。">
<meta name="keywords" content="NorthPark">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
<link media="all" type="text/css" rel="stylesheet" href="/css/bu.css">


</head>

<body style="">
	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>

    	<div class="clearfix maincontent "  >
		<div class="container" >
		<div class="mainbody" style="margin-top:10em;">
			<div class="align-center  radius-5 padding20 max-width-800 min-width-600">
				<div class="col-sm-6 col-sm-offset-3 margin-b20 margin-t20">
					<div class="row margin-b20 margin-t20">
						<p >
							你所寻找的东西穿越到了火星需要救援。
							</p>	
							<p>
							 <span id="mes" style="cursor: pointer;"><font color="#49c7be">返回首页</font></span> <!-- <span id="mes"><font color="blue">5</font></span> 秒钟后返回首页！ -->
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

<%@ include file="/WEB-INF/views/page/common/container.jsp"%>


<script src="/js/page/error.js"></script> 





</body></html>    
