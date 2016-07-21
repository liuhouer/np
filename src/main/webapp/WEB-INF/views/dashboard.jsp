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
<link media="all" type="text/css" rel="stylesheet" href="/css/slider.css">


</head>

<body class="grayback">
	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	
		 
	<div class="clearfix maincontent grayback" id="J_maincontent">

			<!-- 轮播区域 -->
			<div id="slider">
			<div class="flexslider">
				<ul class="slides">
					<li class=""
						style="width: 100%; float: left; margin-right: -100%; position: relative; display: block; z-index: 1; opacity: 0;">
						<div class="slider-caption">
							<h1>最爱</h1>
							<p>
								什么时候爱上一首曲子，什么时候恋上一张贴画，什么时候迷上一种习惯 <br>
								<br>一件件美好的事物，勾勒出您最真实的生命轨迹。
							</p>
							<a href="single-post.html">More Details</a>
						</div> <img src="/img/index/slide1.jpg" alt="" draggable="false">
					</li>
					<li
						style="width: 100%; float: left; margin-right: -100%; position: relative; display: block; z-index: 1; opacity: 0;"
						class="">
						<div class="slider-caption">
							<h1>碎碎念</h1>
							<p>
								Nulla id iaculis ligula. Vivamus mattis quam eget urna tincidunt
								consequat. Nullam <br>
								<br>consectetur tempor neque vitae iaculis. Aliquam erat
								volutpat.
							</p>
							<a href="single-post.html">More Details</a>
						</div> <img src="/img/index/slide2.jpg" alt="" draggable="false">
					</li>
					<li
						style="width: 100%; float: left; margin-right: -100%; position: relative; display: block; z-index: 2; opacity: 1;"
						class="flex-active-slide">
						<div class="slider-caption">
							<h1>情圣日记</h1>
							<p>
								Maecenas fermentum est ut elementum vulputate. Ut vel consequat
								urna. Ut aliquet <br>
								<br>ornare massa, quis dapibus quam condimentum id.
							</p>
							<a href="single-post.html">Get Ready</a>
						</div> <img src="/img/index/slide3.jpg" alt="" draggable="false">
					</li>
					<li
						style="width: 100%; float: left; margin-right: -100%; position: relative; display: block; z-index: 2; opacity: 1;"
						class="flex-active-slide">
						<div class="slider-caption">
							<h1>热映电影</h1>
							<p>
								Maecenas fermentum est ut elementum vulputate. Ut vel consequat
								urna. Ut aliquet <br>
								<br>ornare massa, quis dapibus quam condimentum id.
							</p>
							<a href="single-post.html">Get Ready</a>
						</div> <img src="/img/index/slide3.jpg" alt="" draggable="false">
					</li>
				</ul>
				<ol class="flex-control-nav flex-control-paging">
					<li><a class="">1</a></li>
					<li><a class="">2</a></li>
					<li><a class="flex-active">3</a></li>
					<li><a class="">4</a></li>
				</ol>
				<ul class="flex-direction-nav">
					<li><a class="flex-prev" href="#"></a></li>
					<li><a class="flex-next" href="#"></a></li>
				</ul>
			</div>
		</div>

			<!-- ================================================================================== -->
					<div class="container">
						<div class="clearfix center logbox gray-text">
						   <h2>
							最爱		
						   </h2>
						</div>
			
						<div class="row sloganbox">
							<div class="col-xs-8 col-xs-offset-2 center">
								
								<p>什么时候爱上一首曲子，什么时候恋上一张贴画，什么时候迷上一种习惯...</p>
								<p>一件件美好的事物，勾勒出您最真实的生命轨迹。</p>
														<a href="/signup" class="btn btn-hero btn-lg radius-2 margin-t10 no-decoration">Join Northpark ››</a>
												</div>
						</div>
					</div>
					<!-- 最爱 -->
			       <div class="container grayback" id="J_container_love">
						
						<div class="row">
							 <c:if test="${!empty lovelist }">
							   <c:forEach items="${lovelist }" var="s" varStatus="ss">
				   				<div class="col-xs-12 col-sm-4 margin-b20 ">
							   			<div class="blog-post">
			                                <div class="blog-thumb">
			                                    <img class="imgbreath"
			                                    <c:choose>
														  <c:when test="${fn:contains(s.albumImg ,'http://') }">
														  src="${s.albumImg }"
														  </c:when>
														  <c:otherwise>
														  src="/bruce/${s.albumImg }"
														  </c:otherwise>
												</c:choose> 
			                                    
			                                    alt="${s.title }">
			                                </div>
			                                <div class="blog-content">
			                                    <div class="content-show">
			                                        <h4><a href="/lyrics/comment/${s.id }.html">${s.title }</a></h4>
			                                        <span>29 Sep 2084</span>
			                                    </div>
			                                    <div class="content-hide" style="display: none;">
			                                        <p>${s.album }<br>${s.artist }</p>
			                                    </div>
			                                </div>
			                            </div>
										<%-- <div
											class="card project-card project-card-small project-card-clickable">
											<a href="/lyrics/comment/${s.id }.html" data-g-label="/lyrics/comment/${s.id }.html"
												class="project-card-link"><div
													class="stage project-card-small-stage">
													<div class="stage-image imgbreath"
														<c:choose>
														  <c:when test="${fn:contains(s.albumImg ,'http://') }">
														  style="background-image: url(${s.albumImg })"
														  </c:when>
														  <c:otherwise>
														  style="background-image: url(http://northpark.cn/bruce/${s.albumImg })"
														  </c:otherwise>
														</c:choose> 
																
														
														alt="${s.title }">
														
														
													</div>
													<div class="stage-content-bottom">
														<p class="project-card-small-title">${s.album }</p>
														<p class="project-card-small-organization">— ${s.artist }</p>
													</div>
												</div>
												
											</a>	
												<div class="card-content project-card-small-content">
													<p>
													<c:if test="${s.yizan eq 'yizan' }"><span class="glyphicon glyphicon-heart"></span>${s.zan }</c:if>
											    	<c:if test="${s.yizan ne 'yizan' }">
											    	<span class="glyphicon glyphicon-heart-empty" style="cursor: pointer;" 
											    	<c:if test="${user.id!=null }">onclick="zan('${s.id}','${user.id }')"</c:if> ></span>${s.zan }</c:if>
											    	
											    	
												    
												<span class="hidden-sm hidden-xs"> &nbsp; 
												<span class="glyphicon glyphicon-comment" style="cursor: pointer;" onclick="location.href='/lyrics/comment/${s.id }.html'"></span>  ${s.pl } </span>
												</p>
												</div>
												<!-- <div class="card-footer">
													<div class="card-footer-button-flat">LEARN MORE</div>
												</div> -->
											
											
											
											
											
											
										</div> --%>
										
								</div>
						   </c:forEach>
						 </c:if>
				   	 </div>

				<!-- ================================================================================== -->

					</div>
					
						<div class="container">
						<div class="clearfix center logbox gray-text">
						   <h2>
							碎碎念
						   </h2>
						</div>
			
						<div class="row sloganbox">
							<div class="col-xs-8 col-xs-offset-2 center">
								
								<p>什么时候爱上一首曲子，什么时候恋上一张贴画，什么时候迷上一种习惯...</p>
								<p>一件件美好的事物，勾勒出您最真实的生命轨迹。</p>
							</div>
						</div>
					</div>
					<!-- 碎碎念 -->
					<div class="container grayback" id="J_container_note">
						<div class="row">
					<c:forEach items="${notelist }" var="s" varStatus="ss">
					<div class="col-sm-6 ">
					<div class="clearfix bg-white margin-t10 margin-b10 padding20" >
								<div class="row">
									<div class="col-sm-3">
										<div class="thumbnail border-0 center ">
										   <div class="avatar">
											<a 
											
											<c:if test="${s.tail_slug==null ||s.tail_slug==''}">
											href="/cm/detail/${s.userid}"
											</c:if>
											<c:if test="${s.tail_slug!=null }">
											href="/people/${s.tail_slug}"
											</c:if>
											
											title="${s.get('username')}:我的最爱">
											<c:if test="${s.get('headpath') ==null||s.get('headpath') ==''||s.get('headpath').length()==0}">
												<span class=" imgbreath ${s.headspanclass }" alt="${s.get('username')}">${s.headspan }</span>
											</c:if>
											<c:if test="${s.get('headpath') !=null && s.get('headpath').length()>0}">
											    <img class="imgbreath"
											 <c:choose>
   												<c:when test="${fn:contains(s.headpath ,'http://') }">src="${s.headpath }"</c:when>
                                  				<c:otherwise>src="/bruce/${s.headpath }"</c:otherwise>
                                			</c:choose> 
											
											 alt="${s.username}">
											 </c:if>
											</a>
											<p><small class="gray-text">${s.username}</small></p>
											<div class="clearfix visible-xs"><hr></div>
										 </div>	
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


				

					</div>
					
					
					<!-- ================================================================================== -->
					
						<div class="container">
						<div class="clearfix center logbox gray-text">
						   <h2>
							情圣日记
						   </h2>		
						</div>
			
						<div class="row sloganbox">
							<div class="col-xs-8 col-xs-offset-2 center">
								
								<p>什么时候爱上一首曲子，什么时候恋上一张贴画，什么时候迷上一种习惯...</p>
								<p>一件件美好的事物，勾勒出您最真实的生命轨迹。</p>
												</div>
						</div>
					</div>
					
					<!-- 情圣日记 -->
					<div class="container grayback" id="J_container_romeo">
						<div class="row">
				
					<c:forEach items="${eqlist }" var="s" varStatus="ss">
					<div class="col-sm-4 " >
					<div class="clearfix bg-white margin-t10 margin-b10 padding20" >
								<div class="row">
									<div class="col-sm-4">
										<div class="thumbnail border-0 center">
											<a title="${s.title}">
											<c:if test="${s.img==null ||s.img==''}">
												<img src="/img/davatar.jpg" class="imgbreath">
											</c:if>
											<c:if test="${s.img!=null }">
												<img src="${s.img }" class="imgbreath">
											</c:if>
											</a>
											<p><label class="bold-text">${s.title}</label></p>
											
											<div class="clearfix visible-xs"><hr></div>
										</div>
									</div>
									
									<div class="col-sm-8">
										<p><small class="label label-gray">${s.date }</small> &nbsp; <br><br>
										
										
										<a  title="${s.title}">${s.title}</a> ：</p>
										<p id="brief_${ss.index}">
										
										${s.brief }
										<c:if test="${s.brief!=s.article }">
											<button class="clearfix btn btn-gray btn-xs click2show"  data-dismiss="#brief_${ss.index}" data-target="#text_${ss.index}"> &nbsp; <span class="glyphicon glyphicon-chevron-down"></span> &nbsp; </button>
										</c:if>
										</p>
												<div class="clearfix hidden" id="text_${ss.index}" >
													${s.article }
												</div>

									</div>
								</div>
							</div>
					</div>
					</c:forEach>
					
						 
						 
					
		  	</div>

				

					</div>
					
					<!-- ================================================================================== -->


		
	</div>
	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
	 <script src="js/plugins.js"></script>
     <script src="js/main.js"></script>
	

</body>

</html>    
