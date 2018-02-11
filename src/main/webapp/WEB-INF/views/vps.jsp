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
<title>优惠VPS，国外服务器，国外主机，测评及优惠码  | NorthPark</title>
</c:if>
<c:if test="${page!=null && page!=''}">
<title>优惠VPS，国外服务器，国外主机，测评及优惠码 ::第${page}页  | NorthPark</title>
</c:if>

<meta name="keywords" content="NorthPark,国外主机,国外VPS,国外服务器,VPS服务器,VPS主机,便宜VPS,美国VPS,美国主机,美国服务器,主机空间,主机推荐,VPS推荐,服务器推荐,虚拟主机,便宜服务器">
<meta name="description" content="NorthPark-VPS板块包含了最新的国内外vps优惠信息~对于vps评测和建站非常具有参考价值">
</head>

<body >

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	 
	
				<!-- 页面标题 -->
   				 <h1 class="font-elegant">优惠VPS、VPS测评</h1>	 
<div class="clearfix maincontent grayback" >
	<div class="container">
		<div class="mainbody" style="margin-top:100px; ">
		
			<div  class="row">
				<div  class="col-md-8">
				
				
					<div class="col-sm-12">
				     
				        <form class="form-search " id="J_ser_from" method="post" accept-charset="UTF-8" action="/vps/mac/page/1">
		                  <input id="keyword" placeholder="你享要的优惠啦~"    value="${keyword }"	class="width100 input-medium search-query input-lg  border-light-1 bg-lyellow  radius-0" name="keyword" type="text">
		                  <input data-activetext="搜索 ››" class="btn btn-hero " value="搜索" type="button" id="J_ser_btn">
		                </form>
		                 
					</div>
					<c:forEach items="${list }" var="s" varStatus="ss">
					
						<div class="col-sm-12">
							<div class="clearfix bg-white margin-t10 margin-b10 padding20 ">
								<div class="row margin5">
										<div class="border-0 center">
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
										
										发表于：<span class="common-a-right"  title="${s.date}" >${s.date}</span>
										
											 <c:forEach items="${s.taglist }" var="t" varStatus="tt">
											 
										    	 <strong><a class="common-a-right"  title="${t.tag}" href="/vps/tag/${t.tag}">${t.tag}</a></strong>
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
			$(this).css('max-width',($(".bg-white").width()*0.618));
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
