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
<title>诗词秀 | NorthPark</title>
<meta name="description" content="NorthPark,文艺,小清新,Mac软件,影视窝,碎碎念,图册,情圣,情商提升">
<meta name="keywords" content="NorthPark是一个很小清新的互动公园。NorthPark包含了丰富的Mac软件资源、最新的影视剧资源、大量提升男生情商的文章、大家吐槽的，喜爱的，心情日记的精神角落、图册互动等版块。它富有交互性和趣味性，文艺范和小清新，并且可以和你的朋友们为某个兴趣互动。">

<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
 <style type="text/css">
 /*通用样式*/



/*content*/
.content{
    position: relative;
    margin: 40px 0 0 -21px;
    width: 900px; 
    height: 460px;
}
.l-pic-index{
	/*display:none;*/
    position: absolute;
    left: 400px;
    top: 1px;
    z-index:2;
    width:50px;
    height:460px;
    background: url("/img/poem/j1.png") no-repeat right 0;
}
.r-pic-index{
	/*display:none;*/
    position: absolute;
    right: 400px;
    top: 0;
    z-index: 2;
    width:50px;
    *width:82px;
    height:460px;
    background: url("/img/poem/j4.png") no-repeat left 0;
}
.l-bg-index{
    position: absolute;
    top: -3px;
    left: 430px;
    z-index: 1;
    width: 25px;
    height: 459px;
    background: url("/img/poem/j2.png") right 0 no-repeat;
}
.r-bg-index{
    position: absolute;
    top:-4px;
    right: 430px;
    z-index: 1;
    width: 25px;
    height: 459px;
    background: url("/img/poem/j3.png") 0 0 no-repeat;
}
.main-index{
    display: none;
    overflow: hidden;
    zoom:1;
    position: absolute;
    z-index: 5;
    width:530px;
    height:280px;
    left:145px;
    top:90px;
    color: #2e2e2e;
     text-align: right;
}
.intro-text{
    -webkit-writing-mode: vertical-rl; 
    margin: 3px 0 0 44px;
    line-height: 1.8;
    float: right;
    padding-right: 50px;
    padding-top: -50px;
}

.font1{
	font-size: 14px;
    line-height: 1.67;
    font-weight: 400;
    letter-spacing: normal;
    vertical-align: top;
    color: black; 
 }
 </style>
</head>

<body >

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	 
	
<div class="clearfix maincontent grayback" >
	<div class="container">
		<div class="mainbody" style="margin-top:80px; ">
		
		
		     
		
		
			<div  class="row">
				<div  class="col-md-12">
						<div class="content">
							<div class="l-pic-index"></div>
							<div class="r-pic-index"></div>
							<div class="l-bg-index"></div>
							<div class="r-bg-index"></div>
							<div class="main-index">
								<!-- <h1 class="title"><img src="./img/intro-title.png" alt=""></h1> -->
								<p class="intro-text ">
								    ${poem.title } <br>
								    ${poem.author }<br>
									${poem.content1 }
									</p>
								</p>
							</div>
						</div>

					</div>
					
			</div>	
			<div  class="row">	
				<div  class="col-md-4 ">
				
					<div class="clearfix  margin20 view">
				     
				        <form class="form-search " id="J_ser_from" method="post" accept-charset="UTF-8" action="/movies/search">
		                  <input id="keyword" placeholder="诗词名句"    value="${keyword }"	class="input-medium search-query input-lg  border-light-1 bg-lyellow  radius-0" name="keyword" type="text">
		                  <input data-activetext="搜索 ››" class="btn btn-hero " value="搜索" type="submit">
		                </form>
		                 
				   </div>
				 
					 
					 <!-- hot  --> 
					 <div  class="clearfix sidebar radius-5 ">
						<div  class="clearfix border-bottom">
							<h4><span  class=" glyphicon glyphicon-leaf margin5"></span> 热门</h4>
						</div>
						
						
					 </div> 
					 
					 
					   <!-- tags  -->
					<div  class="clearfix sidebar radius-10 ">
						<div  class="clearfix border-bottom">
							<h4><span  class=" glyphicon glyphicon-tags margin5"></span> 标签</h4>
						</div>
		
						
						
					 </div>
					 
					 
				</div>
			</div>
		  	
		  	
		</div>
	</div>
</div>

	

    
	
	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
	
	<script type="text/javascript">
	//禁止图片拉伸
	$(function(){
		$("img").each(function(){
			$(this).css('max-width',($(".bg-white").width()));
		})
	})
	</script>
	
	<script type="text/javascript">
	  /*  ##set query param */
	    var keyword = $("#keyword").val(); 
	    if(keyword){
	    	$("#pageForm a").each(function(){
	    		var href = $(this).attr("href");
	    		$(this).attr("href",href+"?keyword="+keyword);
	    	})
	    }
		
	
	
	</script>
	
	
	
	<script>
	 $(document).ready(function(){
	        //卷轴展开效果
	        $(".l-pic-index").animate({'left':'96px','top':'-4px'},1300);
	        $(".r-pic-index").animate({'right':'-23px','top':'-5px'},1450);
	        $(".l-bg-index").animate({'width':'433px','left':'73px'},1500);
	        $(".r-bg-index").animate({'width':'433px','right':'-38px'},1500,function(){
	            $(".main-index").fadeIn(800);
	        });
	    });
	</script>
	


</body></html>
