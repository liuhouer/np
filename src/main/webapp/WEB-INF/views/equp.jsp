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
<title>northpark / 情圣养成日记::第${page}页</title>
<meta name="description" content="情圣养成日记::第${page}页::northpark / 记住美好,保留回忆,分享最爱。">
<meta name="keywords" content="northpark,最爱,回忆,生活">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
</head>

<body >

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	 
	
<div class="clearfix maincontent grayback">
	<div class="container">
		    <div class="view margin-t20"  style="margin-top:100px; " >
                <form class=" form-inline margin-t20"  method="post">
                  <input id="keyword" placeholder="约不出来怎么办"    value="${keyword }"	class="form-control input-lg  border-light-1 bg-lyellow grid98 radius-0" name="keyword" type="text">
                  <input data-activetext="搜索 ››" id="J_search" class="form-control btn btn-hero " value="搜索" type="button">
                </form>
              </div>
		<div class="mainbody" id="J_maincontent"  >
			
			<div id="J_progress" class="center padding-t20"></div>

		   
		  	
		  	
		</div>
	</div>
</div>
	<%@ include file="/WEB-INF/views/page/common/fenye.jsp"%>
	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>

<script src="/js/page/eq.js"></script>

</body></html>
