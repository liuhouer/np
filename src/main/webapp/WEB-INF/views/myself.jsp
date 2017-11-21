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
<title>${user.username}的档案 | NorthPark </title>
<meta name="keywords" content="NorthPark,Mac软件,影视窝,碎碎念,最爱主题图册和情商提升兼具文艺范和小清新">
<meta name="description" content="NorthPark是一个很小清新的互动公园。NorthPark包含了丰富的Mac软件资源、影视窝包含最新的影视剧资源、情商提升的技巧和讲解、碎碎念的精神角落、最爱主题图册互动、评论、关注等版块。它富有交互性和趣味性，文艺范和小清新，并且可以和你的朋友们为某个兴趣互动。">

<%@ include file="/WEB-INF/views/page/common/common.jsp"%>


</head>

<body style="">
     <form action="" method="post" id="f2">
       <input name="userid" value="${user.id }" type="hidden">
     </form>
	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>

    	<%@ include file="/WEB-INF/views/page/common/centre.jsp"%>

    <div class="clearfix maincontent">
	    <div class="container">
	      <div class="mainbody padding10" style="margin-top:2em;">
	 
		    <div class="clearfix margin-b20">
	         <ul class="nav nav-tabs">
		        <li class="active"><a href="/cm/pcentral">最爱</a></li>
		        <li><a href="/note/findAll">碎碎念</a></li>
		         <li ><a  href="/cm/myfans" >Fans</a></li>
			</ul>
			</div>		
			
			<c:forEach items="${Lovelist }" var="s" varStatus="ss">
			<div class="row">
			    <div class="col-md-2">
				<h3 class="label label-gray ">${s.updatedate }：</h3>
			    </div>
			    <div class="col-md-10">
									<div class="row">
										<div class="col-xs-4 col-sm-2 center"
											<c:if test="${MyInfo.id!=null }">onmouseover="addSpan('delspan${ss.index }')" onmouseout="rmSpan('delspan${ss.index }')"</c:if>>
											<c:if test="${MyInfo.id!=null }">
												<span id="delspan${ss.index }" 
													onclick="removes('${s.id}','${s.userlyricsid }')"></span>
											</c:if>
											<a  
												title="${s.title }" class="thumbnail border-0"> <img
												src="/bruce/${s.albumImg }" alt="${s.title }">
												<p>${s.title }</p>
											</a>
										</div>
									</div>
								</div>
		     </div>
	 
	        <hr>
			</c:forEach>
		
</div>
<br>
<br>

</div>

	    </div>
	

<%@ include file="/WEB-INF/views/page/common/container.jsp"%>


    




<script src="/js/page/myself.js"></script>

 

</body></html>    
