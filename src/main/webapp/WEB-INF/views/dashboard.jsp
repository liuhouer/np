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
<title>northpark</title>
<meta name="description" content="northpark / 记住美好,保留回忆,分享最爱。">
<meta name="keywords" content="northpark,northpark,最爱,回忆,生活">
<!-- /**
 *
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 *
 * ━━━━━━感觉萌萌哒━━━━━━
 */ -->

<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
<link rel="stylesheet" href="/css/font-awesome.css">
<link media="all" type="text/css" rel="stylesheet" href="/css/flexslider.css">
<link type="text/css" rel="stylesheet" href="/css/jquery.mmenu.all.css" />




<style type="text/css">


</style>
</head>

<body >
	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	
	
		 
	<div class="clearfix maincontent grayback" id="J_maincontent">

			<!-- 轮播区域 引入轮播模块代码 -->
			<%@ include file="/WEB-INF/views/page/dash/dash-slide.jsp"%>


			<!-- ================================================================================== -->
					<div class="container">
						<div class="clearfix center logbox gray-text">
						   <h2>
							最爱		
						   </h2>
						</div>
						<hr class="smallhr">
						<div class="row sloganbox">
							<div class="col-xs-8 col-xs-offset-2 center">
								
								<p>什么时候爱上一首曲子，什么时候恋上一张贴画，什么时候迷上一种习惯...</p>
								<p>一件件美好的事物，勾勒出您最真实的生命轨迹。</p>
														<a href="/signup" class="btn btn-hero btn-lg radius-2 margin-t10 no-decoration">Join Northpark ››</a>
												</div>
						</div>
					</div>
					
					<!-- 最爱 模块代码-->
			       <div class="container grayback" id="J_container_love">
						
						
				   	 <div class="row center margin20">
						<a href="/love" class="btn btn-gray btn-lg radius-2 margin-t10 no-decoration">发现更多 ››</a>
					</div>

				<!-- ================================================================================== -->

					</div>
					
						<div class="container">
						<div class="clearfix center logbox gray-text">
						   <h2>
							碎碎念
						   </h2>
						</div>
						
						<hr class="smallhr">
			
						<div class="row sloganbox">
							<div class="col-xs-8 col-xs-offset-2 center">
								
								<p>扯淡、吐槽、心情、树洞……想说啥就说啥！...</p>
								<p>NorthPark就是你的树洞。</p>
							</div>
						</div>
					</div>
					<!-- 碎碎念 模块代码-->
					<div class="container grayback" id="J_container_note">

					</div>
					
					
					<!-- ================================================================================== -->
					
						<div class="container">
						<div class="clearfix center logbox gray-text">
						   <h2>
							情圣日记
						   </h2>		
						</div>
						<hr class="smallhr">
			
						<div class="row sloganbox">
							<div class="col-xs-8 col-xs-offset-2 center" style="font-family: 'Dorsa-Regular';">
								
								<p>Maecenas fermentum est ut elementum vulputate. Ut vel consequat
								urna. Ut aliquet 
								</p>
								<p>ornare massa, quis dapibus quam condimentum id.</p>
							</div>
						</div>
			
					</div>
					
					<!-- 情圣日记 模块代码-->
				<div class="container grayback" id="J_container_romeo">
								
				</div>



			<!-- ================================================================================== -->
					
						<div class="container">
						<div class="clearfix center logbox gray-text">
						   <h2>
							热映电影
						   </h2>		
						</div>
						<hr class="smallhr">
			
						<div class="row sloganbox">
							<div class="col-xs-8 col-xs-offset-2 center" style="font-family: 'Dorsa-Regular';">
								
								<p>最新热映电影、BT、云盘、火热资源
								</p>
								<p>每周更新.</p>
							</div>
						</div>
			
					</div>

              <!-- 电影 模块代码-->
              <div class="container grayback" id="J_container_movies">
				
			 </div>				

		<!-- ================================================================================== -->


		
	</div>
	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
	 <script src="/js/plugins.js"></script>
     <script src="/js/main.js"></script>
     <script type="text/javascript" src="/js/jquery.mmenu.min.all.js"></script>
<script type="text/javascript" src="/js/jquery.flexslider.js"></script>
<script type="text/javascript" src="/js/o-script.js"></script>
</body>

</html>    