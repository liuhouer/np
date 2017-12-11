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
<title>享优惠  | NorthPark</title>
</c:if>
<c:if test="${page!=null && page!=''}">
<title>享优惠 ::第${page}页  | NorthPark</title>
</c:if>

<meta name="keywords" content="NorthPark,Mac软件,影视窝,碎碎念,最爱主题图册和情商提升兼具文艺范和小清新">
<meta name="description" content="NorthPark是一个很小清新的互动公园。NorthPark包含了丰富的Mac软件资源、影视窝包含最新的影视剧资源、情商提升的技巧和讲解、碎碎念的精神角落、最爱主题图册互动、评论、关注等版块。它富有交互性和趣味性，文艺范和小清新，并且可以和你的朋友们为某个兴趣互动。">
</head>

<body >

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	 
	
<div class="clearfix maincontent grayback" >
	<div class="container">
		<div class="mainbody" style="margin-top:80px; ">
		
		<div class="clearfix  margin20 view">
				     
				        <form class="form-search " id="J_ser_from" method="post" accept-charset="UTF-8" action="/vps/mac/page/1">
		                  <input id="keyword" placeholder="你享要的优惠啦~"    value="${keyword }"	class="input-medium search-query input-lg  border-light-1 bg-lyellow  radius-0" name="keyword" type="text">
		                  <input data-activetext="搜索 ››" class="btn btn-hero " value="搜索" type="button" id="J_ser_btn">
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
											<a href="/vps/post-${s.id }.html">
												<small class="green-text">
													 <font size="5"><strong>${s.title}</strong></font> 
												</small>
											</a>
											</p>
											
											
											<div class="clearfix visible-xs"><hr></div>
										</div>
									
										<p>  
										
										发表于：<a class="common-a-right"  title="${s.date}" >${s.date}</a>
										
											<c:forEach items="${s.taglist }" var="t" varStatus="tt">
											 
										     <a class="common-a-right"  title="${t.tag}" href="/vps/tag/${t.tag}">${t.tag}</a>
											 </c:forEach>
										     
										     
										</p>
										<p id="brief_${ss.index}">
										
										  ${s.brief }
										</p>
										
										<p>
										<a class="btn btn-warning margin-t10" href="/vps/post-${s.id }.html">
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
				
					 
					 
					   <!-- tags  -->
					<div  class="clearfix sidebar radius-10 ">
						<div  class="clearfix border-bottom">
							<h4><span  class=" glyphicon glyphicon-tags margin5"></span> 标签云</h4>
						</div>
						<c:forEach var="z" items="${tagCloud }">
						        
						        <div class="col-md-10 margin5 avatar" >
						            <c:if test="${z.tagscloud == seltag }">
						              <span class="text-${ fn:substring(z.color ,0,1)  }  glyphicon glyphicon-flag margin5">${ fn:toUpperCase(fn:substring(z.tagscloud ,0,1))   }</span>
								
						             <a style="color: #45d0c6;" href="/vps/tag/${z.tagscloud }" title="${z.tagscloud }">${z.tagscloud } </a>
						            </c:if>
						            <c:if test="${z.tagscloud != seltag }">
						              <span class="text-${ fn:substring(z.color ,0,1)  } margin5">${ fn:toUpperCase(fn:substring(z.tagscloud ,0,1))   }</span>
								
						             <a href="/vps/tag/${z.tagscloud }" title="${z.tagscloud }">${z.tagscloud } </a>
						            </c:if>
						           
				        			
				        			<a style="color: #45d0c6;" href="/vps/tag/${z.tagscloud }" title="">(${z.nums }) </a>
				        			
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
		
		//搜索
		$("#J_ser_btn").click(function(){
			$("#J_ser_btn").attr('disabled',true);
			if($("#keyword").val() && $("#keyword").val()!="${keyword }"){
				
				window.location.href = "/vps/page/1?keyword="+$("#keyword").val();
			}
			setTimeout("$('#J_ser_btn').removeAttr('disabled')",5000); //设置5秒后提交按钮 显示  
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
