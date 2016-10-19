<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>NorthPark</title>
<meta name="description" content="NorthPark::每日优惠券">
<meta name="keywords" content="NorthPark::每日优惠券">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
</head>

<body>

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	 

<div class="clearfix maincontent "  >
		<div class="container" >
		<div class="mainbody" style="margin-top:10em;">
			<div class="align-center  radius-5 padding20 max-width-800 ">
				<div class="col-sm-12" style="height: 880px;">
					<iframe src="${path_mt}" scrolling="yes" width="100%" height="100%"></iframe>
					
					</div>
			</div>
			</div>
		</div>
</div>
	

	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>



</body></html>