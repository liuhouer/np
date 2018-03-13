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
<meta name="keywords" content="NorthPark,Mac软件,影视窝,碎碎念,最爱主题图册和情商提升兼具文艺范和小清新">
<meta name="description" content="NorthPark是一个很小清新的互动公园。NorthPark包含了丰富的Mac软件资源、影视窝包含最新的影视剧资源、情商提升的技巧和讲解、碎碎念的精神角落、最爱主题图册互动、评论、关注等版块。它富有交互性和趣味性，文艺范和小清新，并且可以和你的朋友们为某个兴趣互动。">

<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
</head>

<body >

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>

<div class="clearfix maincontent grayback" >
	<div class="container mainbody">
			<div class="row">
				
					<c:forEach items="${list }" var="s" varStatus="ss">
					<div class="col-md-12">
					<div class="col-sm-10  col-md-offset-1 ">
					<div class="clearfix bg-white margin-t10 margin-b10 padding20" id="J_white_div">
								<div class="row margin10 post_article">
								
								       <div class="border-0 center">
											<p oid="${s.id }"><h1><small class="green-text"><font size="5"><strong>${s.moviename}</strong></font> </small></h1></p>
											
											
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
									
										<div class="margin20">
										
											<%-- <a  title="${s.moviename}的简介" href="/movies/post-${s.id }.html">${s.moviename}</a> 简介：</p> --%>
											<p id="brief_${ss.index}">
											
											  ${s.description }
											  <%--  <label class="red-text">文章链接</label>：https://northpark.cn/movies/post-${s.id }.html --%>
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
										        <a href="http://7xpfpd.com1.z0.glb.clouddn.com/blog/donate/praise.jpg" class="fancybox" rel="group"><img id="wechat_qr" src="http://7xpfpd.com1.z0.glb.clouddn.com/blog/donate/praise.jpg" alt="Bruce WeChat Pay" style="width: 200px; height:200px;max-width: 100%; display: inline-block"></a>
										        <p>微信打赏</p>
										      </div>
										    
										    
										      <div id="alipay" style="display: inline-block">
										        <a href="http://7xpfpd.com1.z0.glb.clouddn.com/blog/donate/alipay.png" class="fancybox" rel="group"><img id="alipay_qr" src="http://7xpfpd.com1.z0.glb.clouddn.com/blog/donate/alipay.png" alt="Bruce Alipay" style="width: 200px; height:200px;max-width: 100%; display: inline-block"></a>
										        <p>支付宝打赏</p>
										      </div>
										    
										  </div>
										</div>
										<!-- 打赏 -->	
										
										
										<div class="clearfix visible-xs">
											<hr>
									    </div>
										
		  	 
							  	        <!-- 评论 -->
													<div id="comment"  class="clearfix bg-white">
													
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
													   
													   
													</script>
													</div>
													<!-- City版安装代码已完成 -->
														
													</div>
										<!-- 评论 -->
							  	
										
										</div>
										
										
										
										
										

									</div>
									
									
									
										      
								</div>
								
								
								
								
							</div>
							</div>
					</div>
					</c:forEach>
					
						 
		  	
	</div>
</div>

	
	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
    <script type="text/javascript">

   $(function(){
		$("img").each(function(){
			$(this).css('max-width',($(".bg-white").width()*0.618));
		})
		
		
		
		//删除评论多余的代码---来必力
		
		   
	})

 		function handup(id){
	    	 $.ajax({
	             url:"/movies/handup",
	             type:"post",
	             data:{"id":id},
	             success:function(msg){
	                 if(msg=="success"){
	                     art.dialog.tips('置顶成功');
	                 }else{
	                	 art.dialog.tips('error happened.');
	                 }            
	             }
	         });

	    }
	    
	    function hideup(id){
	    	 $.ajax({
	             url:"/movies/hideup",
	             type:"post",
	             data:{"id":id},
	             success:function(msg){
	                 if(msg=="success"){
	                     art.dialog.tips('隐藏成功');
	                     window.location.href = window.location.href;
	                 }else{
	                	 art.dialog.tips('error happened.');
	                 }            
	             }
	         });

	    }
    </script>	



</body></html>