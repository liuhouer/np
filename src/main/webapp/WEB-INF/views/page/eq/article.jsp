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
<title>NorthPark / 文章</title>
<meta name="description" content="${user.username}生命中的最爱: NorthPark / 记住美好,保留回忆,分享最爱。">
<meta name="keywords" content="NorthPark">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>

<style type="text/css">
.aboutWrapper {
    background-image: url(http://o8a5h1k2v.bkt.clouddn.com/16-7-28/64876897.jpg);
    background-repeat: no-repeat;
    background-position: center top;
}

.reservationsWrapper {
    margin-top: 70px;
}

.aboutWrapper {
    background-color: #ebebeb;
    padding-bottom: 40px;
    position: relative;
    margin-bottom: 350px;
}

.aboutWrapper:before {
    content: ' ';
    display: block;
    position: absolute;
    bottom: -100px;
    background-image: url(http://o8a5h1k2v.bkt.clouddn.com/16-7-28/34631260.jpg);
    max-width: 100%;
    left: 0;
    height: 291px;
    width: 100%;
}

</style>

</head>

<body >
	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>


	<div class="clearfix maincontent grayback">
	<div class="container">
		<div class="mainbody" id="J_maincontent"  style="margin-top:150px; ">
			
			<div class="col-sm-10 col-xs-10 col-sm-offset-1 col-xs-offset-1 center" >
							
							<div class="aboutWrapper reservationsWrapper">
							  <div class="row-fluid client_info_holder animationStart section_featured_texts">
							    <div class="clearfix margin-b10 center" >
								<div class="row">
									
									<div class="col-sm-8 col-xs-10 col-xs-offset-1 col-sm-offset-2 ">
										<div class=" border-0 margin-t10">
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
	</div>
</div>

   			 
	

<%@ include file="/WEB-INF/views/page/common/container.jsp"%>

</body></html>    
