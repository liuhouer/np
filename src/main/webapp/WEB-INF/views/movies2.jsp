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
<title>影视窝  | NorthPark</title>
<meta name="description" content="NorthPark,文艺,小清新,Mac软件,影视窝,碎碎念,图册,情圣,情商提升">
<meta name="keywords" content="NorthPark是一个很小清新的互动公园。NorthPark包含了丰富的Mac软件资源、最新的影视剧资源、大量提升男生情商的文章、大家吐槽的，喜爱的，心情日记的精神角落、图册互动等版块。它富有交互性和趣味性，文艺范和小清新，并且可以和你的朋友们为某个兴趣互动。">

<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
</head>

<body >

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	 
	
<div class="clearfix maincontent grayback" >
	<div class="container">
		<div class="mainbody" style="margin-top:80px; ">
		
		<div class="clearfix  margin20 view">
				     
				        <form class="form-search " id="J_ser_from" method="post" accept-charset="UTF-8" action="/movies/search">
		                  <input id="keyword" placeholder="电影名"    value="${keyword }"	class="input-medium search-query input-lg  border-light-1 bg-lyellow  radius-0" name="keyword" type="text">
		                  <input data-activetext="搜索 ››" class="btn btn-hero " value="搜索" type="submit">
		                </form>
		                 
		</div>
		     
		
		
			<div  class="row">
				<div  class="col-md-8">
					<c:forEach items="${list }" var="s" varStatus="ss">
					
						<div class="col-sm-12">
							<div class="clearfix bg-white margin-t10 margin-b10 padding20 ">
								<div class="row margin5">
										<div class="thumbnail border-0 center">
											<p>
											<a href="/movies/search?id=${s.id }">
												<small class="green-text">
													<font size="5"><strong>${s.moviename}</strong></font> 
												</small>
											</a>
											</p>
											
											
											<div class="clearfix visible-xs"><hr></div>
										</div>
									
										<p>  
										
										发表于：<span  class=" glyphicon glyphicon-time margin10"></span><a class="common-a-right"  title="${s.addtime}" href="/movies/date/${s.addtime}">${s.addtime}</a>
										
										       <span  class=" glyphicon glyphicon-tags margin10"></span>
										       
										       <c:forEach items="${s.taglist }" var="y" varStatus="yy">
										         <a class="common-a-right"  title="${y.tag}" href="/movies/tag/${y.tagcode }">${y.tag}</a>
										       </c:forEach>
										       
										      
										     
										</p>
										<p id="brief_${ss.index}">
										
										  ${s.description }
										</p>
										
										<c:if test="${s.path!=null && s.path!=''}">
											<p><small class="label red-text">下载地址
													<p><small class="red-text">${s.path}</small></p>
											    </small> 
											 </p> &nbsp;
											
										</c:if>
								</div>
							</div>
						</div>
					</c:forEach>
					<c:if test="${pagein!='no' }">
						<%@ include file="/WEB-INF/views/page/common/fenye.jsp"%>
					</c:if>
				</div>
				<div  class="col-md-4 ">
				
					
				 
					 
					 <!-- hot  --> 
					 <div  class="clearfix sidebar radius-5 ">
						<div  class="clearfix border-bottom">
							<h4><span  class=" glyphicon glyphicon-leaf margin5"></span> 热门</h4>
						</div>
						<c:forEach var="z" items="${movies_hot_list }">
						        
						        <div class="col-md-12 margin-t10" >
						              <div class="col-xs-2 avatar">
					        		
					        			<span class="text-${ z.color }">${ fn:toUpperCase(fn:substring(z.moviename ,0,1))   }</span>
								
				        			  </div>
				        			   <div class="col-xs-10">
					        		
					        			 <a style="font-size: 14px;line-height: 32px;color: #888" href="/movies/search?id=${z.id }" title="${z.moviename }">${z.moviename } </a>
								
				        			 </div>
						             
				        			
				        		</div>
						</c:forEach>
		
						
						
					 </div> 
					 
					 
					   <!-- tags  -->
					<div  class="clearfix sidebar radius-10 ">
						<div  class="clearfix border-bottom">
							<h4><span  class=" glyphicon glyphicon-tags margin5"></span> 标签</h4>
						</div>
						<c:forEach var="z" items="${movies_tags }">
						        
						        <div class="col-md-10 margin5" >
						            <c:if test="${z.tagcode == seltag }">
						             <span  class="glyphicon glyphicon-arrow-right margin5"></span>
						             <a style="color: #45d0c6;" href="/movies/tag/${z.tagcode }" title="${z.tag }">${z.tag } </a>
						            </c:if>
						            <c:if test="${z.tagcode != seltag }">
						             <span  class="glyphicon glyphicon-tag margin5"></span>
						             <a href="/movies/tag/${z.tagcode }" title="${z.tag }">${z.tag } </a>
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
