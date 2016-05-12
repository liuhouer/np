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
<title>布.词</title>
<meta name="description" content="布.词 | 空间 ${MyInfo.username}生命中的最爱: 布.词会让您记住每一件美好的事物，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>

</head>

<body style="">
	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>

    	<%@ include file="/WEB-INF/views/page/common/centrespace.jsp"%>

    <div class="clearfix maincontent">
	    <div class="container">
	      <div class="mainbody padding10" style="margin-top:2em;">
	 
		    <div class="clearfix margin-b20">
	         <ul class="nav nav-tabs">
		        <li class="active"><a 
		        <c:if test="${MyInfo.tail_slug==null || MyInfo.tail_slug==''}">
				href="/cm/detail/${MyInfo.id}"
				</c:if>
				<c:if test="${MyInfo.tail_slug!=null }">
				href="/people/${MyInfo.tail_slug}"
				</c:if>
		        
				>布.图</a></li>
		        <li><a href="/note/viewNotes/${MyInfo.id}">碎碎念</a></li>
		        <li><a href="/cm/fans/${MyInfo.id}">Fans</a></li>

			</ul>
			</div>
				<c:forEach items="${Lovelist }" var="s" varStatus="ss">
							<div class="row">
								<div class="col-md-2">
									<h3 class="label label-gray">${s.updatedate }：</h3>
								</div>
								<div class="col-md-10">
									<div class="row">
										<div class="col-xs-4 col-sm-2 center">
											<a  href="/lyrics/toView/${s.id}"
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


<script src="/js/page/space.js"></script>

 

</body></html>    
