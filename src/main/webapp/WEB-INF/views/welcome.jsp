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
<link rel="shortcut icon" href="img/favicon.ico">
<title>布.词</title>
<meta name="description" content="布.词会让您记住每一件美好的事物，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="布.词,最爱,回忆,生活">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>

</head>

<body class="grayback">
	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	
		 
	<div class="clearfix maincontent grayback">		
		<div class="container grayback">
			<div class="mainbody padding-t20" style="margin-top:70px;">
			 <c:if test="${!empty list }">
			   <c:forEach items="${list }" var="s" varStatus="ss">
			    <c:if test="${(ss.index+1) % 4==0}"> 
			   		  <div class="row">
			    </c:if>  
			   				<div class="col-xs-6 col-sm-3 margin-b20 ">
								<div class="thumbnail radius-0 border-0 margin-b0" >
							<a href="/lyrics/comment/${s.id }.html" title="${s.title }:love<c:if test="${s.yizan eq 'yizan' }">/已赞过</c:if><c:if test="${s.yizan ne 'yizan' }">/未赞，点击下面的小心就可以赞了嗷~</c:if>">
							<img alt=""  
								<c:choose>
								  <c:when test="${fn:contains(s.albumImg ,'http://') }">src="${s.albumImg }"</c:when>
								  <c:otherwise>src="/bruce/${s.albumImg }"</c:otherwise>
								</c:choose> 
							
							 alt="${s.title }"></a>
								
								</div>
							<div class="row margin-t0 iteminfo">
								<div class="col-xs-7 text-left">
														<a href="/lyrics/comment/${s.id }.html" title="${s.title }:love<c:if test="${s.yizan eq 'yizan' }">/已赞过</c:if><c:if test="${s.yizan ne 'yizan' }">/未赞，点击下面的小心就可以赞了嗷~</c:if>">${s.title }...</a>
								</div>
								<div class="col-xs-5 text-right">
								    <c:if test="${s.yizan eq 'yizan' }"><span class="glyphicon glyphicon-heart"></span></c:if>
								    <c:if test="${s.yizan ne 'yizan' }"><span class="glyphicon glyphicon-heart-empty" style="cursor: pointer;" <c:if test="${user.id!=null }">onclick="zan('${s.id}','${user.id }')"</c:if> ></span></c:if>
									 ${s.zan } 
									<span class="hidden-sm hidden-xs"> &nbsp; 
									<span class="glyphicon glyphicon-comment" style="cursor: pointer;" onclick="location.href='/lyrics/comment/${s.id }.html'"></span>  ${s.pl } 						</span>
								</div>
							</div>
							</div>
			   	  <c:if test="${(ss.index+1) % 4==0}">
			   		  </div>
			   	  </c:if> 
			   </c:forEach>
			 </c:if>


				
				
			</div>
		</div>



<%@ include file="/WEB-INF/views/page/common/fenye.jsp"%>
<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
<script type="text/javascript">

$(function(){
	var flag = "${signout}";
	if(flag=='true'){
		if(QC.Login.check()){
			QC.Login.signOut();
		}
	}
})


function zan(lrcid,userid){
   $.ajax({
		url:"/zanAction/zan",
		type:"post",
		data:{"lyricsid":lrcid,"userid":userid},
		success:function(msg){
			if(msg=="success"){
				art.dialog.tips('已赞!');
				window.location.href = window.location.href;
			}			
		}
	});
}



</script>



</body>

</html>    


