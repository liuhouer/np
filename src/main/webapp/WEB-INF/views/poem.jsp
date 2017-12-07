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


 
</head>

<body >

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	
	
	

<div class="clearfix maincontent grayback" >

					

	<div class="container" >
	  
		<div class="mainbody" style="margin-top:80px; ">
		
		     
		
		
			<div  class="row">
				<div  class="col-md-12">
				
						  
					
					       <div class="col-sm-8  col-md-offset-2 clearfix bg-white margin-t10 margin-b10 padding20 touming center">
								<div class="row ">
									 <!-- 文字区不需要请连css部分代码一并删除 -->
									 <p >${poem.title }</p>
									 <p style="font-style: italic;">${poem.author }</p>
									 <p >${poem.content1 }</p>
										   
											
											

										

								</div>
							</div>

				</div>
					
			</div>	
			
			
			<div  class="row">
				<div  class="col-md-8">
					<c:forEach items="${list }" var="s" varStatus="ss">
					
						<div class="col-sm-12 ">
							<div class="clearfix bg-white margin-t10 margin-b10 padding20 ">
								<div class="row margin5">
										<div class="thumbnail border-0 center">
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
				
					<div class="clearfix  margin20 view">
				     
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
	
	<script type="text/javascript">
	//禁止图片拉伸
	$(function(){
		$("img").each(function(){
			$(this).css('max-width',($(".bg-white").width()));
		})
	})
	</script>
	
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
	
	
	
	


</body></html>
