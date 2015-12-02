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
<link rel="shortcut icon" href="img/favicon.png">
<title>布.词故事::</title>
<meta name="description" content="布.词故事::第1页::布.词会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">
<%@ include file="/page/common/common.jsp"%>
</head>

<body style="">

	<%@ include file="/page/common/navigation.jsp"%>
	 
	
<div class="clearfix maincontent" style="background:#f4f3f1">
	<div class="container">
		<div class="mainbody" style="margin-top:80px; ">
			<div class="row">
				
					<c:forEach items="${list }" var="s" varStatus="ss">
						<c:forEach items="${UList }" var="u" varStatus="uu">
								 <c:if test="${u.id==s.userid }">
					<div class="col-sm-6 ">
					<div class="clearfix bg-white margin-t10 margin-b10 padding20">
								<div class="row">
									<div class="col-sm-3">
										<div class="thumbnail border-0 center">
											<a href="/cm/detail/${u.id }" title="${u.username}:我的最爱">
											<c:if test="${u.headpath ==null}"><img src="/img/davatar.jpg" alt="${u.username}"></c:if>
											<c:if test="${u.headpath !=null}"><img 
											 <c:choose>
   												<c:when test="${fn:contains(u.headpath ,'http://') }">src="${u.headpath }"</c:when>
                                  				<c:otherwise>src="bruce/${u.headpath }"</c:otherwise>
                                			</c:choose> 
											
											 alt="${u.username}"></c:if>
											</a>
											<p><small class="gray-text">${u.username}</small></p>
											<div class="clearfix visible-xs"><hr></div>
										</div>
									</div>
									
									<div class="col-sm-9">
										<p><small class="label label-gray">${s.createtime }</small> &nbsp; <a href="/cm/detail/${u.id }" title="${u.username}的最爱">${u.username}</a> 写到：</p>
										<p id="brief_0">
										<c:out value="${s.note }"/>
		
										<button class="clearfix btn btn-gray btn-xs click2show" data-dismiss="#brief_0" data-target="#text_0"> &nbsp; <span class="glyphicon glyphicon-chevron-down"></span> &nbsp; </button>
										</p>
												<div class="clearfix hidden" id="text_0">
													<c:out value="${s.note }"/>
												</div>

									</div>
								</div>
							</div>
					</div>
					</c:if>
					 </c:forEach>
					</c:forEach>
					
						 

									 

			

								 
				  	
						 
					
		  	</div>

		  	<%@ include file="/page/common/fenye.jsp"%>		 
		  	
		  	
		</div>
	</div>
</div>
	 

	<%@ include file="/page/common/container.jsp"%>



<script type="text/javascript">

$(document).ready(function() {

	var ajax_url='/ajax';
	var _aj = {user_id: '50777'};
	_aj['user_agent']='68A697E775AE';
	_aj['timestamp']='1400553738';
	_aj['user_keychain']='CBBDECB98732';

	
	});
</script>


 


</body></html>