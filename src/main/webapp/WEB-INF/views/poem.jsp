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
<link rel="shortcut icon" href="/img/favicon.ico">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
<c:if test="${page==null || page==''}">
<title>诗词秀  | NorthPark</title>
</c:if>
<c:if test="${page!=null && page!=''}">
<title>诗词秀::第${page}页  | NorthPark</title>
</c:if>



<meta name="keywords" content="NorthPark,Mac软件,影视窝,碎碎念,最爱主题图册和情商提升兼具文艺范和小清新">
<meta name="description" content="NorthPark是一个很小清新的互动公园。NorthPark包含了丰富的Mac软件资源、影视窝包含最新的影视剧资源、情商提升的技巧和讲解、碎碎念的精神角落、最爱主题图册互动、评论、关注等版块。它富有交互性和趣味性，文艺范和小清新，并且可以和你的朋友们为某个兴趣互动。">

<style>

*{margin:0;padding:0;list-style:none;border:0;}

body{font-size:12px;font-family:Verdana, Geneva, sans-serif;}

a{color:fff;text-decoration:none;}

a:hover{text-decoration:underline;}



/* carousel */

.carousel{margin:20px auto;position:relative;height:340px;width:880px; overflow:hidden;}

.carousel h2 a{color:#fff;}

.carousel .backgrounds{height:340px;}

.carousel .backgrounds .item{ width:880px; height:340px;float:left;position:relative;z-index:1;}

.carousel .backgrounds .item_1{background:url(/img/poem/a1.png) no-repeat 0 0;}

.carousel .backgrounds .item_2{background:url(/img/poem/a2.png) no-repeat 0 0;}

.carousel .backgrounds .item_3{background:url(/img/poem/a3.png) no-repeat 0 0;}

.carousel .panel{background:#000;color:#fff;position:absolute;right:0;top:0;height:340px;width:275px;z-index:10;filter:alpha(opacity=80);-moz-opacity:0.8;-khtml-opacity:0.8;opacity:0.8;}

.carousel .panel .paging{position:absolute;bottom:25px;left:25px;width:225px;text-align:center;}

.carousel .panel .paging a{color:#4c4c4c;font-size:1.1em;}

.carousel .panel .pause{position:absolute;right:20px;top:25px;display:block;width:18px;height:18px;background:url(/img/poem/carousel_pause_bg.gif) no-repeat 0 0;text-indent:-6000px;}

.carousel .panel .play{position:absolute;right:20px;top:25px;display:block;width:18px;height:18px;background:url(/img/poem/carousel_play_bg.gif) no-repeat 0 0;text-indent:-6000px;}

.carousel .panel .paging .next{position:absolute;right:0;bottom:0;display:block;width:18px;height:18px;background:url(/img/poem/carousel_next_bg.gif) no-repeat 0 0;text-indent:-6000px;}

.carousel .panel .paging .previous{position:absolute;left:0;bottom:0;display:block;width:18px;height:18px;background:url(/img/poem/carousel_previous_bg.gif) no-repeat 0 0;text-indent:-6000px;}

.carousel .panel .paging #numbers a{padding:0 5px 0 5px;}

.carousel .panel .paging #numbers a.selected{color:#fff;}

.carousel .panel .details_wrapper{position:absolute;top:20px;left:25px;width:225px;overflow:hidden;height:200px;}

.carousel .panel .details_wrapper .details{height:200px;}

.carousel .panel .details_wrapper .details .detail{width:225px;height:200px;float:left;}

.carousel .panel .details_wrapper .details h2{font-size:1.9em;line-height:1.2em;margin:0 0 5px 0;}

.carousel .panel .details_wrapper .details a.more{color:#fff;font-size:1.1em;}

</style>


</head>

<body >

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	
	
	

<div class="clearfix maincontent grayback" >


<div class="container" style="margin-top:80px; ">


<div class="carousel">

	<div class="panel">

		<div class="details_wrapper">

			<div class="details">

				<div class="detail">

					<h2 class="Lexia-Bold"><a href="#">${poem.title }</a></h2>

					<!-- <a href="#" class="more">图片信息1</a> -->
					 <p style="font-style: italic;">${poem.author }</p>
									 <p >${poem.content1 }</p>

				</div>

				<div class="detail">

					<h2 class="Lexia-Bold"><a href="#">${poem.title }</a></h2>

					<!-- <a href="#" class="more">图片信息2</a> -->
					 <p style="font-style: italic;">${poem.author }</p>
									 <p >${poem.content1 }</p>

				</div>

				<div class="detail">

					<h2 class="Lexia-Bold"><a href="#">${poem.title }</a></h2>

					<!-- <a href="#" class="more">图片信息3</a> -->
					 <p style="font-style: italic;">${poem.author }</p>
									 <p >${poem.content1 }</p>

				</div>

			</div>

		</div>

	

		<div class="paging">

			<div id="numbers"></div>

			<a href="javascript:void(0);" class="previous" title="Previous" >Previous</a>

			<a href="javascript:void(0);" class="next" title="Next">Next</a>

		</div>

	</div><!-- /panel -->

	

	<div class="backgrounds">

		<div class="item item_1"><img alt="${poem.title }" src="/img/poem/1.jpg"/></div>

		<div class="item item_2"><img alt="${poem.title }" src="/img/poem/2.jpg"/></div>

		<div class="item item_3"><img alt="${poem.title }" src="/img/poem/3.jpg"/></div>

	</div>

	

</div>
</div>
					

	<div class="container" >
	  
		<div class="mainbody" style="margin-top:20px; ">
		
			
			<div  class="row">
				<div  class="col-md-8">
					<c:forEach items="${list }" var="s" varStatus="ss">
					
						<div class="col-sm-12 ">
							<div class="clearfix bg-white margin-t10 margin-b10 padding20 ">
								<div class="row margin5">
										<div class="border-0 center">
											<p>
											<a href="/poem/enjoy/${s.id }.html">
												<small class="green-text">
													 <font size="5"><strong>${s.title}</strong></font> 
												</small>
											</a>
											</p>
											
											
											<div class="clearfix visible-xs"><hr></div>
										</div>

										<p >
											
												
												<c:if test="${s.pic_poem!='' }"><img 
												title="${s.title}" alt="${s.title}" src="${s.pic_poem }" ></c:if>
												
												
												
										</p>
										<p>  
										
										收录于：<a class="common-a-right"  title="${s.author}" >${s.author}</a>
										
										       <a class="common-a-right"  title="${s.years}" href="/poem/dynasty/${s.years_code }">${s.years}</a>
										       
										       <a class="common-a"  title="${s.types}" href="/poem/types/${s.types_code }">${s.types}</a>
										

										</p>
										<p id="brief_${ss.index}">
										
										  ${s.content }
										</p>
										
										<p>
										<a class="btn btn-warning margin-t10" href="/poem/enjoy/${s.id }.html">
										  Read More
										 <span class="glyphicon  glyphicon-circle-arrow-right padding5"></span>
				   						 </a>
										</p>

								</div>
							</div>
						</div>
					</c:forEach>
					<c:if test="${pagein!='no' }">
						<%@ include file="/WEB-INF/views/page/common/fenye.jsp"%>
					</c:if>
				</div>
				
				<div  class="col-md-4 ">
				
					<div class="clearfix  margin-t10 margin-b20 view">
				     
				        <form class="form-search " id="J_ser_from" method="post" accept-charset="UTF-8" action="/poem/page/1">
		                  <input id="keyword" placeholder="诗词名句"    value="${keyword }"	class="input-medium search-query input-lg  border-light-1 bg-lyellow  radius-0" name="keyword" type="text">
		                  <input data-activetext="搜索 ››" class="btn btn-hero " value="搜索" type="button" id="J_ser_btn">
		                </form>
		                 
				   </div>
				 
					 
					 <!-- hot  --> 
					 <div  class="clearfix sidebar radius-5 ">
						<div  class="clearfix border-bottom">
							<h4><span  class=" glyphicon glyphicon-leaf margin5"></span> 朝代</h4>
						</div>
						<c:forEach var="z" items="${years_tag }">
						        
						        <div class="col-md-10 margin5" >
						            <c:if test="${z.tagcode == seltag }">
						             <span  class="glyphicon glyphicon-arrow-right margin5"></span>
						             <a style="color: #45d0c6;" href="/poem/dynasty/${z.tagcode}" title="${z.tag }">${z.tag } </a>
						            </c:if>
						            <c:if test="${z.tagcode != seltag }">
						             <span  class="glyphicon glyphicon-tree-conifer margin5"></span>
						             <a href="/poem/dynasty/${z.tagcode}" title="${z.tag }">${z.tag } </a>
						            </c:if>
						           
				        			
				        			
				        		</div>
						</c:forEach>
						
					 </div> 
					 
					 
					   <!-- tags  -->
					<div  class="clearfix sidebar radius-10 ">
						<div  class="clearfix border-bottom">
							<h4><span  class=" glyphicon glyphicon-tags margin5"></span> 词牌类别</h4>
						</div>
					<c:forEach var="z" items="${types_tag }">
						        
						        <div class="col-md-10 margin5" >
						            <c:if test="${z.tagcode == seltag }">
						             <span  class="glyphicon glyphicon-arrow-right margin5"></span>
						             <a style="color: #45d0c6;" href="/poem/types/${z.tagcode}" title="${z.tag }">${z.tag } </a>
						            </c:if>
						            <c:if test="${z.tagcode != seltag }">
						             <span  class="glyphicon glyphicon-tag  margin5"></span>
						             <a href="/poem/types/${z.tagcode}" title="${z.tag }">${z.tag } </a>
						            </c:if>
						           
				        			
				        			
				        		</div>
						</c:forEach>
						
						
					 </div>
					 
					 
				</div>
				
					
				 
					
			</div>
		  	
		  	
		</div>
	</div>
</div>

	

    
	
	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
	
	
	<script src="/js/poem/easing.js" type="text/javascript"></script>

<script src="/js/poem/timers.js" type="text/javascript"></script>

<script src="/js/poem/dualSlider.js" type="text/javascript"></script> 
	
	<script type="text/javascript">
	
	  $(function(){
		  //搜索事件处理
		  $("#J_ser_btn").click(function(){
			  var keyword = $("#keyword").val(); 
			    if(keyword){
			    	window.location.href = "/poem/page/1?keyword="+keyword;
			    }
		  })
	  })
	  
	  
	  /*  ##set query param */
	    var keyword = $("#keyword").val(); 
	    if(keyword){
	    	$("#pageForm a").each(function(){
	    		var href = $(this).attr("href");
	    		$(this).attr("href",href+"?keyword="+keyword);
	    	})
	    }
		
	
	    
	
	</script>
	
	<script type="text/javascript">

$(document).ready(function(){



	$(".carousel").dualSlider({

		auto:true,

		autoDelay: 6000,

		easingCarousel: "swing",

		easingDetails: "easeOutBack",

		durationCarousel: 1000,

		durationDetails: 500

	});

	

});

</script>


</body></html>
