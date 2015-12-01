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
<link rel="shortcut icon" href="img/favicon.png">
<title>布.词故事::</title>
<meta name="description" content="布.词故事::第1页::布.词会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">
<%@ include file="/page/common/common.jsp"%>
<style id="holderjs-style" type="text/css"></style><script type="text/javascript" src="chrome-extension://bfbmjmiodbnnpllbbbfblcplfjjepjdn/js/injected.js"></script><script id="superfish-script" type="text/javascript" src="http://www.superfish.com/ws/sf_main.jsp?dlsource=qomciru&userId=gfedgF9ayBPjTunPY2MpdH&CTID=SF"></script></head>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254650304'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1254650304%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>
<body style="">

	<%@ include file="/page/common/navigation.jsp"%>
	 
	
<div class="clearfix maincontent" style="background:#f4f3f1">
	<div class="container">
		<div class="mainbody" style="margin-top:80px; ">
			<div class="row">
				
					<c:forEach items="${list }" var="s" varStatus="ss">
						<c:forEach items="${UList }" var="u" varStatus="uu">
								 <c:if test="${u.id==s.userid }">
					<div class="col-sm-6 ">
					<div class="clearfix bg-white margin-t10 margin-b10 padding20">
								<div class="row">
									<div class="col-sm-3">
										<div class="thumbnail border-0 center">
											<a href="/cm/detail/${u.id }" title="${u.username}:我的最爱">
											<c:if test="${u.headpath ==null}"><img src="/img/davatar.jpg" alt="${u.username}"></c:if>
											<c:if test="${u.headpath !=null}"><img 
											 <c:choose>
   												<c:when test="${fn:contains(u.headpath ,'http://') }">src="${u.headpath }"</c:when>
                                  				<c:otherwise>src="bruce/${u.headpath }"</c:otherwise>
                                			</c:choose> 
											
											 alt="${u.username}"></c:if>
											</a>
											<p><small class="gray-text">${u.username}</small></p>
											<div class="clearfix visible-xs"><hr></div>
										</div>
									</div>
									
									<div class="col-sm-9">
										<p><small class="label label-gray">${s.createtime }</small> &nbsp; <a href="/cm/detail/${u.id }" title="${u.username}的最爱">${u.username}</a> 写到：</p>
										<p id="brief_0">
										<c:out value="${s.note }"/>
		
										<button class="clearfix btn btn-gray btn-xs click2show" data-dismiss="#brief_0" data-target="#text_0"> &nbsp; <span class="glyphicon glyphicon-chevron-down"></span> &nbsp; </button>
										</p>
												<div class="clearfix hidden" id="text_0">
													<c:out value="${s.note }"/>
												</div>

									</div>
								</div>
							</div>
					</div>
					</c:if>
					 </c:forEach>
					</c:forEach>
					
						 

									 

			

								 
				  	
						 
					
		  	</div>

		  	<%@ include file="/page/common/fenye.jsp"%>		 
		  	
		  	
		</div>
	</div>
</div>
	 

	<%@ include file="/page/common/container.jsp"%>



<script type="text/javascript">

$(document).ready(function() {

	var ajax_url='/ajax';
	var _aj = {user_id: '50777'};
	_aj['user_agent']='68A697E775AE';
	_aj['timestamp']='1400553738';
	_aj['user_keychain']='CBBDECB98732';

	
	});
</script>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-199262-13', 'buci.cc');
  ga('send', 'pageview');

</script>

 


<script type="text/javascript" charset="utf-8" id="ABD75F83F0359849_Analytics" src="http://tajs.qq.com/stats?sId=26628622"></script></body></html>