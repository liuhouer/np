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
<title>Mac软件 | NorthPark</title>
<meta name="description" content="Mac软件|NorthPark">
<meta name="keywords" content="Mac软件|NorthPark">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
</head>

<body >

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	 
	
<div class="clearfix maincontent grayback" >
	<div class="container">
		<div class="mainbody" style="margin-top:80px; ">
		
		<div class="clearfix  margin20 view">
				     
				        <form class="form-search " id="J_ser_from" method="post" accept-charset="UTF-8" action="/soft/mac/page0">
		                  <input id="keyword" placeholder="Mac软件板块上线啦~"    value="${keyword }"	class="input-medium search-query input-lg  border-light-1 bg-lyellow  radius-0" name="keyword" type="text">
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
											<a href="/soft/${s.retcode }.html">
												<small class="green-text">
													<font size="5">${s.title}</font>
												</small>
											</a>
											</p>
											
											
											<div class="clearfix visible-xs"><hr></div>
										</div>
									
										<p>  
										
										发表于：<a class="common-a-right"  title="${s.postdate}" href="/soft/date/${s.postdate }">${s.postdate}</a>
										
										     <a class="common-a-right"  title="${s.tags}" href="/soft/tag/${s.tagscode }">${s.tags}</a>
										     
										     <a class="common-a-right"  title="${s.os}" href="/soft/${s.os }">${s.os}</a>
										     
										     <a href="/soft/${s.retcode }.html#comment" class="ds-thread-count common-a" data-thread-key="${s.retcode }" data-count-type="comments"></a>
										</p>
										<p id="brief_${ss.index}">
										
										  ${s.brief }
										</p>
										
										<p>
										<a class="btn btn-warning margin-t10" href="/soft/${s.retcode }.html">
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
				
					
				 
					 
					 <!-- hot  --> 
					 <div  class="clearfix sidebar radius-5 ">
						<div  class="clearfix border-bottom">
							<h4><span  class=" glyphicon glyphicon-leaf margin5"></span> 热门</h4>
						</div>
						<c:forEach var="z" items="${hot_list }">
						        
						        <div class="col-md-12 margin-t10" >
						              <div class="col-xs-2 avatar">
					        		
					        			<span class="text-${ fn:toLowerCase(fn:substring( z.title ,0,1)) }">${ fn:toUpperCase(fn:substring(z.title ,0,1))   }</span>
								
				        			  </div>
				        			   <div class="col-xs-10">
					        		
					        			 <a style="font-size: 14px;line-height: 32px;color: #888" href="/soft/${z.retcode }.html" title="${z.title }">${z.title } </a>
								
				        			 </div>
						             
				        			
				        		</div>
						</c:forEach>
		
						
						
					 </div> 
					 
					 
					   <!-- tags  -->
					<div  class="clearfix sidebar radius-10 ">
						<div  class="clearfix border-bottom">
							<h4><span  class=" glyphicon glyphicon-tags margin5"></span> 标签</h4>
						</div>
						<c:forEach var="z" items="${soft_tags }">
						        
						        <div class="col-md-10 margin5" >
						            <c:if test="${z.tagscode == seltag }">
						             <span  class="glyphicon glyphicon-arrow-right margin5"></span>
						             <a style="color: #45d0c6;" href="/soft/tag/${z.tagscode }" title="${z.tags }">${z.tags } </a>
						            </c:if>
						            <c:if test="${z.tagscode != seltag }">
						             <span  class="glyphicon glyphicon-tag margin5"></span>
						             <a href="/soft/tag/${z.tagscode }" title="${z.tags }">${z.tags } </a>
						            </c:if>
						           
				        			
				        			<a style="color: #45d0c6;" href="/soft/tag/${z.tagscode }" title="">(${z.num }) </a>
				        			
				        		</div>
						</c:forEach>
		
						
						
					 </div>
					 
					  <!-- month  --> 
					 <div  class="clearfix sidebar radius-10 ">
						<div  class="clearfix border-bottom">
							<h4><span  class=" glyphicon glyphicon-time margin5"></span> 月份</h4>
						</div>
						<c:forEach var="z" items="${date_list }">
						        
						        <div class="col-md-10 margin5" >
						            <c:if test="${z.month == selmonth }">
						             <span  class="glyphicon glyphicon-arrow-right margin5"></span>
						             <a style="color: #45d0c6;" href="/soft/month/${z.month}" title="${z.month }">${z.month } </a>
						            </c:if>
						            <c:if test="${z.month != selmonth }">
						             <span  class="glyphicon glyphicon-tree-conifer margin5"></span>
						             <a href="/soft/month/${z.month}" title="${z.month }">${z.month } </a>
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
	  /*  ##set query param */
	    var keyword = $("#keyword").val(); 
	    if(keyword){
	    	$("#pageForm a").each(function(){
	    		var href = $(this).attr("href");
	    		$(this).attr("href",href+"?keyword="+keyword);
	    	})
	    }
		
	
	
	</script>
	
	<!-- 多说js加载开始，一个页面只需要加载一次 -->
	<script type="text/javascript">
	var duoshuoQuery = {short_name:"jellyband"};
	(function() {
	    var ds = document.createElement('script');
	    ds.type = 'text/javascript';ds.async = true;
	    ds.src = 'http://static.duoshuo.com/embed.js';
	    ds.charset = 'UTF-8';
	    (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(ds);
	})();
	</script>
	<!-- 多说js加载结束，一个页面只需要加载一次 -->


</body></html>