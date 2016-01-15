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
<title>布.词故事::第${page}页</title>
<meta name="description" content="布.词故事::第1页::布.词会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
</head>

<body style="">

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	 
	
<div class="clearfix maincontent" style="background:#f4f3f1">
	<div class="container">
		<div class="mainbody" style="margin-top:80px; ">
			<div class="row">
				
					<c:forEach items="${list }" var="s" varStatus="ss">
					<div class="col-sm-6 ">
					<div class="clearfix bg-white margin-t10 margin-b10 padding20">
								<div class="row">
									<div class="col-sm-3">
										<div class="thumbnail border-0 center">
											<a 
											
											<c:if test="${s.tail_slug==null ||s.tail_slug==''}">
											href="/cm/detail/${s.userid}"
											</c:if>
											<c:if test="${s.tail_slug!=null }">
											href="/people/${s.tail_slug}"
											</c:if>
											
											title="${s.get('username')}:我的最爱">
											<c:if test="${s.get('headpath') ==null||s.get('headpath') ==''||s.get('headpath').length()==0}"><img src="/img/davatar.jpg" alt="${s.get('username')}"></c:if>
											<c:if test="${s.get('headpath') !=null && s.get('headpath').length()>0}"><img 
											 <c:choose>
   												<c:when test="${fn:contains(s.headpath ,'http://') }">src="${s.headpath }"</c:when>
                                  				<c:otherwise>src="${bcdomain}/bruce/${s.headpath }"</c:otherwise>
                                			</c:choose> 
											
											 alt="${s.username}"></c:if>
											</a>
											<p><small class="gray-text">${s.username}</small></p>
											<div class="clearfix visible-xs"><hr></div>
										</div>
									</div>
									
									<div class="col-sm-9">
										<p><small class="label label-gray">${s.createtime }</small> &nbsp; <a 
										
											<c:if test="${s.tail_slug==null ||s.tail_slug==''}">
											href="/cm/detail/${s.userid}"
											</c:if>
											<c:if test="${s.tail_slug!=null }">
											href="/people/${s.tail_slug}"
											</c:if>
										
										
										
										
										title="${s.username}的最爱">${s.username}</a> 写到：</p>
										<p id="brief_${ss.index}">
										
										${s.brief }
										<c:if test="${s.brief!=s.note }">
											<button class="clearfix btn btn-gray btn-xs click2show"  data-dismiss="#brief_${ss.index}" data-target="#text_${ss.index}"> &nbsp; <span class="glyphicon glyphicon-chevron-down"></span> &nbsp; </button>
										</c:if>
										</p>
												<div class="clearfix hidden" id="text_${ss.index}">
													${s.note }
												</div>

									</div>
								</div>
							</div>
					</div>
					</c:forEach>
					
						 

									 

			

								 
				  	
						 
					
		  	</div>

		  	<%@ include file="/WEB-INF/views/page/common/fenye.jsp"%>		 
		  	
		  	
		</div>
	</div>
</div>

	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
	<script type="text/javascript">
	$("body").on('click', '.click2show', function() {
			$(this).hide();
	});
	
	</script>



</body></html>
