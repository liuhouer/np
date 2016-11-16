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
<link rel="shortcut icon" href="/img/favicon.ico">
<title>NorthPark / 情圣养成日记::第${page}页</title>
<meta name="description" content="情圣养成日记::第${page}页::NorthPark / 记住美好,保留回忆,分享最爱。">
<meta name="keywords" content="NorthPark">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
</head>

<body >

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	 
	
<div class="clearfix maincontent grayback">
	<div class="container">
		    
		<div class="mainbody" id="J_maincontent"  >
		
             <div class="view clearfix  ">
                <form class=" form-search " action="/movies/search" method="post" accept-charset="UTF-8">
                  <input id="keyword" placeholder="约不出来怎么办"    value="${keyword }"	class="input-medium search-query input-lg  border-light-1 bg-lyellow  radius-0" name="keyword" type="text">
                  <input data-activetext="搜索 ››" id="J_search"  class=" btn btn-hero " value="搜索" type="button">
                </form>
              </div> 
			
			<div id="J_progress" class="center padding-t20"></div>

		   
		  	
		  	
		</div>
	</div>
</div>
	<%@ include file="/WEB-INF/views/page/common/fenye.jsp"%>
	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>

<script src="/js/page/eq.js"></script>

</body></html>
