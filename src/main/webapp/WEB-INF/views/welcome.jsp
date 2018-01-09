<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link rel="shortcut icon" href="/img/favicon.ico">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
<c:if test="${page==null || page==''}">
<title>最爱主题图册-记住美好,保留回忆,分享最爱。 | NorthPark</title>
</c:if>
<c:if test="${page!=null && page!=''}">
<title>最爱主题图册::第${page}页-记住美好,保留回忆,分享最爱。 | NorthPark</title>
</c:if>
<meta name="keywords" content="NorthPark,Mac软件,影视窝,碎碎念,最爱主题图册和情商提升兼具文艺范和小清新">
<meta name="description" content="NorthPark是一个很小清新的互动公园。NorthPark包含了丰富的Mac软件资源、影视窝包含最新的影视剧资源、情商提升的技巧和讲解、碎碎念的精神角落、最爱主题图册互动、评论、关注等版块。它富有交互性和趣味性，文艺范和小清新，并且可以和你的朋友们为某个兴趣互动。">



</head>

<body >
	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	
	 <!-- 页面标题 -->
		<h1 class="font-elegant">最爱-主题图册</h1>	 	 
	<div class="clearfix maincontent grayback" id="J_maincontent">





			<div class="container grayback" id="J_container">
					<div class="mainbody padding-t20" style="margin-top:70px;">
						<div id="J_progress" class="center padding-t20"></div>
					</div>

				

			</div>


		
	</div>
	<%@ include file="/WEB-INF/views/page/common/fenye.jsp"%>
	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>


<script src="/js/page/welcome.js"></script>
</body>

</html>    
