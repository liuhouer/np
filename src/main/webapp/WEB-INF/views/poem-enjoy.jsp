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
<title>${poem_enjoy.title }-${poem_enjoy.author }:诗词秀 | NorthPark</title>
<meta name="keywords" content="NorthPark,Mac软件,影视窝,碎碎念,最爱主题图册和情商提升兼具文艺范和小清新">
<meta name="description" content="NorthPark是一个很小清新的互动公园。NorthPark包含了丰富的Mac软件资源、影视窝包含最新的影视剧资源、情商提升的技巧和讲解、碎碎念的精神角落、最爱主题图册互动、评论、关注等版块。它富有交互性和趣味性，文艺范和小清新，并且可以和你的朋友们为某个兴趣互动。">

<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
 
</head>

<body >

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	
	
		  

<div class="clearfix maincontent grayback" >

					

	<div class="container" style="z-index: 6">
	  
		<div class="mainbody" style="margin-top:80px; ">
		
		     
		
		
			<div  class="row">
				<div  class="col-md-12">
				
					
					
					
					       <div class="col-sm-8  col-md-offset-2 clearfix bg-white margin-t10 margin-b10 padding20 touming center">
								<div class="row ">
									 <!-- 文字区不需要请连css部分代码一并删除 -->
									 <p >${poem_enjoy.title }</p>
									 <p style="font-style: italic;">${poem_enjoy.author }</p>
									 <p >${poem_enjoy.content1 }</p>
									 <hr>
									 <p >${poem_enjoy.enjoys }</p>	   
											
											

										

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
			$(this).css('max-width',($(".bg-white").width()*0.618));
		})
	})
	</script>
	


</body></html>
