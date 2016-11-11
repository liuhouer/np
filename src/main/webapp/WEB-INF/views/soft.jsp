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
		
		
		     <div >
		        <form class="form-inline padding20">
                  <input id="keyword" placeholder="Mac软件板块上线啦~"    value="${keyword }"	class="form-control input-lg  border-light-1 bg-lyellow grid98 radius-0" name="keyword" type="text">
                  <input id="bdcsMain" data-activetext="搜索 ››" class="form-control btn btn-hero " value="搜索" type="submit">
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
										
										     <a class="common-a-right"  title="${s.tags}" href="/soft/tag/${s.tags }">${s.tags}</a>
										     
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
					<div  class="clearfix sidebar radius-5 ">
						<div  class="clearfix border-bottom">
							<h4><span  class=" glyphicon glyphicon-th-large "></span> 随便看看</h4>
						</div>
						<c:forEach var="z" items="${soft_tags }">
						        
						        <div class="col-md-2 avatar">
					        		
					        		 <span class="text-j">J</span>
								
				        	
				        		</div>
						        <div class="col-md-10" >
						           
				        			<a href="/people/jiushiguangj" title="旧时光景的最爱">${z.tags } (${z.num })</a>
				        		</div>
						</c:forEach>
		
						<p  class="white-line margin0"></p>
					 </div>
				</div>
			</div>
		  	
		  	
		</div>
	</div>
</div>

	

    
	
	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
	
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
