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
<title>northpark / 文章</title>
<meta name="description" content="${user.username}生命中的最爱: northpark / 记住美好,保留回忆,分享最爱。">
<meta name="keywords" content="northpark,最爱,回忆,生活">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>


</head>

<body >
	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>


	<div class="clearfix maincontent grayback">
	<div class="container">
		<div class="mainbody" id="J_maincontent"  style="margin-top:150px; ">
			
			<div class="col-sm-8 col-xs-offset-2 center" >
							
							
							<div class="clearfix bg-white margin-10 margin-b10 padding20" >
								<div class="row">
									
									<div class="col-sm-8 col-xs-offset-2  ">
										<div class="thumbnail border-0 ">
											<a title="${model.title}">
												<img src="${model.img }" >
											</a>
											<p><label class="bold-text">${model.title}</label></p>
											
											<div class="clearfix visible-xs"><hr></div>
											<p><small class="label label-gray">${model.date }</small> &nbsp; <br><br>
											
											
											<p >
											
											${model.article }
											</p>
										</div>

									</div>
								</div>
							</div>
					</div>

		   
		  	
		  	
		</div>
	</div>
</div>

   			 
	

<%@ include file="/WEB-INF/views/page/common/container.jsp"%>

</body></html>    
