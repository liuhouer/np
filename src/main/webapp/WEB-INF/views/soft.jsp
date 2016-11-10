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
<link rel="shortcut icon" href="img/favicon.ico">
<title>NorthPark / Mac软件</title>
<meta name="description" content="NorthPark-Mac软件">
<meta name="keywords" content="NorthPark-Mac软件">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
</head>

<body >

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	 
	
<div class="clearfix maincontent grayback" >
	<div class="container">
		<div class="mainbody" style="margin-top:80px; ">
		
		
		     <div class="view ">
                <form class=" form-inline margin20" action="/movies/search" method="post">
                  <input id="keyword" placeholder="Mac软件板块上线啦~"    value="${keyword }"	class="form-control input-lg  border-light-1 bg-lyellow grid98 radius-0" name="keyword" type="text">
                  <input data-activetext="搜索 ››" class="form-control btn btn-hero " value="搜索" type="submit">
                </form>
              </div>
		
		
			<div  class="row">
				<div  class="col-md-8">
					<c:forEach items="${list }" var="s" varStatus="ss">
					
						<div class="col-sm-12 ">
							<div class="clearfix bg-white margin-t10 margin-b10 padding20 ">
								<div class="row margin5">
										<div class="thumbnail border-0 center">
											<p><small class="green-text"><font size="5">${s.title}</font></small></p>
											
											
											<div class="clearfix visible-xs"><hr></div>
										</div>
									
										<p>  
										
										发表于：<a class="common-a-right"  title="${s.postdate}" href="${s.postdate }">${s.postdate}</a>
										
										       <a class="common-a"  title="${s.tags}" href="${s.tags }">${s.tags}</a>
										</p>
										<p id="brief_${ss.index}">
										
										  ${s.brief }
										</p>
										
										<p>
										<a class="btn btn-warning margin-t10" href="/soft/">
										  Read More
										 <span class="glyphicon  glyphicon-circle-arrow-right padding5"></span>
				   						 </a>
										</p>

								</div>
							</div>
						</div>
					</c:forEach>
				<%@ include file="/WEB-INF/views/page/common/fenye.jsp"%>
				</div>
				<div  class="col-md-4">
				</div>
			</div>
		  	
		  	
		</div>
	</div>
</div>

	

    
	
	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
	
	
	<script src="/js/page/movies.js"></script>



</body></html>