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
<link rel="shortcut icon" href="img/favicon.ico">
<title>NorthPark / 支付同步通知</title>
<meta name="description" content="支付宝页面跳转同步通知页面">
<meta name="keywords" content="NorthPark">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>


</head>

<body style="">
	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>

    	<div class="clearfix mainhead grayback">
		<div class="container">
			<div class="row margin-b20 margin-t20">
				<div class="col-sm-6 col-sm-offset-3 margin-b20 margin-t20">
					<div class="row margin-b20 margin-t20">

						${status },已自动发货,电影下载链接为
						<p>
						<small class="green-text">${curl }</small>
						</p>
						<p>
						请注意确认收货哦~
						</p>
					</div>
				</div>
			</div>
		</div>
</div>

    <div class="clearfix maincontent">
	    <div class="container">
	   
</div>

	    </div>
	

<%@ include file="/WEB-INF/views/page/common/container.jsp"%>

</body></html>    


