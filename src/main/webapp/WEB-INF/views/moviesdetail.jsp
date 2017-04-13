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
<title>${keyword }:影视窝  | NorthPark</title>
<meta name="description" content="NorthPark,文艺,小清新,Mac软件,影视窝,碎碎念,图册,情圣,情商提升">
<meta name="keywords" content="NorthPark是一个很小清新的互动公园。NorthPark包含了丰富的Mac软件资源、最新的影视剧资源、大量提升男生情商的文章、大家吐槽的，喜爱的，心情日记的精神角落、图册互动等版块。它富有交互性和趣味性，文艺范和小清新，并且可以和你的朋友们为某个兴趣互动。">

<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
</head>

<body >

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	 
	
<div class="clearfix maincontent grayback" >
	<div class="container">
		<div class="mainbody" style="margin-top:80px; ">
		
		
		     <div class="view clearfix  ">
                <form class=" form-search " action="/movies/search" method="post" accept-charset="UTF-8">
                  <input id="keyword" placeholder="电影名"    value="${keyword }"	class="input-medium search-query input-lg  border-light-1 bg-lyellow  radius-0" name="keyword" type="text">
                  <input data-activetext="搜索 ››" class=" btn btn-hero " value="搜索" type="submit">
                </form>
              </div>
		
		
			<div class="row">
				
					<c:forEach items="${list }" var="s" varStatus="ss">
					<div class="col-sm-12 ">
					<div class="clearfix bg-white margin-t10 margin-b10 padding20">
								<div class="row">
									<div class="col-sm-4">
										<div class="thumbnail border-0 center">
											<p oid="${s.id }"><small class="green-text"><font size="5"><strong>${s.moviename}</strong></font> </small></p>
											
											
											<%-- <p><small class="red-text">￥${s.price}</small></p> --%>
											
											<c:if test="${s.path!=null && s.path!=''}">
											
											<p><small class="label red-text">下载地址<p><small class="red-text">${s.path}</small></p></small> </p> &nbsp;
											
											</c:if>
											
											<p> 
											 <button class="clearfix btn btn-gray btn-xs click2show" onclick="showtoggle('${s.id}')" > <span class="glyphicon glyphicon-yen">donate</span> &nbsp; </button>
											 
											 </p>
											<p id="donate_${s.id}" style="display: none">
												<img src="/img/donate.png" style="width: 160px;height: 200px" />
											</p>
											<div class="clearfix visible-xs"><hr></div>
										</div>
									</div>
									
									<div class="col-sm-8">
										<a  title="${s.moviename}的简介" href="/movies/search?id=${s.id }">${s.moviename}</a> 简介：</p>
										<p id="brief_${ss.index}">
										
										  ${s.description }
										   <label class="red-text">文章链接</label>：https://northpark.cn/movies/search?id=${s.id }
										</p>

									</div>
								</div>
							</div>
					</div>
					</c:forEach>
					
						 
					
		  	</div>

		  	
		  	
		</div>
	</div>
</div>

	
	<!-- <script>document.forms['alipaysubmit'].submit();</script> -->

    <c:if test="${search !='search'}">
    
		<%@ include file="/WEB-INF/views/page/common/fenye.jsp"%>
    </c:if>
	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
	<script src="/js/page/movies.js"></script>
	
	<script type="text/javascript">
	//禁止图片拉伸
	$(function(){
		$("img").each(function(){
			$(this).css('max-width',400);
			$(this).css('max-width',($(".bg-white").width()));
		})
	})
	</script>



</body></html>