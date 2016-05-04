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
<title>布.词 | 情圣养成日记::第${page}页</title>
<meta name="description" content="情圣养成日记::第${page}页::布.词会让您记住每一件美好的事物，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
</head>

<body class="grayback">

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	 
	
<div class="clearfix maincontent grayback">
	<div class="container">
		<div class="mainbody" style="margin-top:80px; ">
			<div class="row">
				
					<c:forEach items="${list }" var="s" varStatus="ss">
					<div class="col-sm-12 ">
					<div class="clearfix bg-white margin-t10 margin-b10 padding20">
								<div class="row">
									<div class="col-sm-4">
										<div class="thumbnail border-0 center">
											<a title="${s.title}">
											<c:if test="${s.img==null ||s.img==''}">
												<img src="/img/davatar.jpg">
											</c:if>
											<c:if test="${s.img!=null }">
												<img src="${s.img }">
											</c:if>
											</a>
											<p><label class="bold-text">${s.title}</label></p>
											
											<div class="clearfix visible-xs"><hr></div>
										</div>
									</div>
									
									<div class="col-sm-8">
										<p><small class="label label-gray">${s.date }</small> &nbsp; 
										
										
										<a  title="${s.title}">${s.title}</a> ：</p>
										<p id="brief_${ss.index}">
										
										${s.brief }
										<c:if test="${s.brief!=s.article }">
											<button class="clearfix btn btn-gray btn-xs click2show"  data-dismiss="#brief_${ss.index}" data-target="#text_${ss.index}"> &nbsp; <span class="glyphicon glyphicon-chevron-down"></span> &nbsp; </button>
										</c:if>
										</p>
												<div class="clearfix hidden" id="text_${ss.index}">
													${s.article }
												</div>

									</div>
								</div>
							</div>
					</div>
					</c:forEach>
					
						 

									 

			

								 
				  	
						 
					
		  	</div>

		   
		  	
		  	
		</div>
	</div>
</div>
	<%@ include file="/WEB-INF/views/page/common/fenye.jsp"%>
	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
	<script type="text/javascript">
	$("body").on('click', '.click2show', function() {
			$(this).hide();
	});
	
	</script>



</body></html>
