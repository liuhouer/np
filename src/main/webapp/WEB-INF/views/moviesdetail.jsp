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
                <form class=" form-search " action="/movies/search" id="J_ser_from" method="post" accept-charset="UTF-8">
                  <input id="keyword" placeholder="电影名"    value="${keyword }"	class="input-medium search-query input-lg  border-light-1 bg-lyellow  radius-0" name="keyword" type="text">
                  <input data-activetext="搜索 ››" class=" btn btn-hero " value="搜索" type="submit" id="J_ser_btn">
                </form>
              </div>
		
		
			<div class="row">
				
					<c:forEach items="${list }" var="s" varStatus="ss">
					<div class="col-sm-12 ">
					<div class="clearfix bg-white margin-t10 margin-b10 padding20">
								<div class="row">
								
								       <div class="thumbnail border-0 center">
											<p oid="${s.id }"><small class="green-text"><font size="5"><strong>${s.moviename}</strong></font> </small></p>
											
											
											<%-- <p><small class="red-text">￥${s.price}</small></p> --%>
											
											<c:if test="${s.path!=null && s.path!=''}">
											
											<p><small class="label red-text">下载地址<p><small class="red-text">${s.path}</small></p></small> </p> &nbsp;
											
											</c:if>
											
											<c:if test="${user!=null }">
										       	 <c:if test="${user.email == '654714226@qq.com' || user.email == 'qhdsoft@126.com' || user.email == 'woaideni@qq.com'}">
										       	 <span  class=" glyphicon glyphicon-arrow-up margin10"></span>
										       	  <a class="common-a-right"  title="置顶" href="" onclick="handup('${s.id}')">置顶</a>
										       	 <span  class=" glyphicon glyphicon-eye-close margin10"></span>
										       	  <a class="common-a-right"  title="隐藏" href="" onclick="hideup('${s.id}')">大尺度隐藏</a>
										       	 </c:if>
										       </c:if>
											

											<div class="clearfix visible-xs"><hr></div>
											
											
											
										</div>
									
										<div class="col-sm-8  col-md-offset-2 ">
										
											<a  title="${s.moviename}的简介" href="/movies/search?id=${s.id }">${s.moviename}</a> 简介：</p>
											<p id="brief_${ss.index}">
											
											  ${s.description }
											  <%--  <label class="red-text">文章链接</label>：https://northpark.cn/movies/search?id=${s.id } --%>
											</p>
											
											<div class="clearfix visible-xs">
											<hr>
									    </div>
									
      
        								<!-- 打赏 -->	
										<div style="padding: 10px 0; margin: 20px auto; width: 90%; text-align: center">
										  <div class="margin10">生活不止苟且,还有我喜爱的海岸.</div>
										  <button id="rewardButton" ,="" disable="enable" onclick="var qr = document.getElementById('QR'); if (qr.style.display === 'none') {qr.style.display='block';} else {qr.style.display='none'}" style="cursor: pointer; border: 0; outline: 0; border-radius: 100%; padding: 0; margin: 0; letter-spacing: normal; text-transform: none; text-indent: 0px; text-shadow: none">
										    <span onmouseover="this.style.color='rgb(236,96,0)';this.style.background='rgb(204,204,204)'" onmouseout="this.style.color='#fff';this.style.background='rgb(236,96,0)'" style="display: inline-block; width: 70px; height: 70px; border-radius: 100%; color: rgb(255, 255, 255); font-style: normal; font-variant: normal; font-weight: 400; font-stretch: normal; font-size: 35px; line-height: 75px; font-family: microsofty; background: rgb(236, 96, 0);">赏</span>
										  </button>
										  <div id="QR" style="display: none;">
										    
										      <div id="wechat" style="display: inline-block">
										        <a href="http://7xpfpd.com1.z0.glb.clouddn.com/blog/donate/wxpay.jpg" class="fancybox" rel="group"><img id="wechat_qr" src="http://7xpfpd.com1.z0.glb.clouddn.com/blog/donate/wxpay.jpg" alt="Bruce WeChat Pay" style="width: 200px; max-width: 100%; display: inline-block"></a>
										        <p>微信打赏</p>
										      </div>
										    
										    
										      <div id="alipay" style="display: inline-block">
										        <a href="http://7xpfpd.com1.z0.glb.clouddn.com/blog/donate/alipay.png" class="fancybox" rel="group"><img id="alipay_qr" src="http://7xpfpd.com1.z0.glb.clouddn.com/blog/donate/alipay.png" alt="Bruce Alipay" style="width: 200px; max-width: 100%; display: inline-block"></a>
										        <p>支付宝打赏</p>
										      </div>
										    
										  </div>
										</div>
										<!-- 打赏 -->	
										
										
										<div class="clearfix visible-xs">
											<hr>
									    </div>
										
										<c:if test="${searchbyid =='searchbyid'}">
		  	 
							  	        <!-- 评论 -->
													<div id="comment"  class="clearfix bg-white">
													<%-- <!-- 多说评论框 start -->
													<div class="ds-thread" data-thread-key="${article.retcode }" data-title="${article.title} | NorthPark" data-url="/soft/${article.retcode }.html"></div>
													<!-- 多说评论框 end --> --%>
													
													<!-- 来必力City版安装代码 -->
													<div id="lv-container" data-id="city" data-uid="MTAyMC8yNzgzNy80NDEz">
													<script type="text/javascript">
													   (function(d, s) {
													       var j, e = d.getElementsByTagName(s)[0];
													
													       if (typeof LivereTower === 'function') { return; }
													
													       j = d.createElement(s);
													       j.src = 'https://cdn-city.livere.com/js/embed.dist.js';
													       j.async = true;
													
													       e.parentNode.insertBefore(j, e);
													   })(document, 'script');
													   
													   
													 //删除评论多余的代码
														
													   $("#wrapper").find("div[id='footer']").remove();
													</script>
													</div>
													<!-- City版安装代码已完成 -->
														
													</div>
							<!-- 评论 -->
							  	
							  	</c:if>
										
										</div>
										
										
										
										
										

									</div>
									
									
									
										      
								</div>
								
								
								
								
							</div>
					</div>
					</c:forEach>
					
						 
					

		  	
		  	
		  	
		  	
		  	
		  	
		</div>
	</div>
</div>

	
	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
	<script src="/js/page/movies.js"></script>
	



</body></html>