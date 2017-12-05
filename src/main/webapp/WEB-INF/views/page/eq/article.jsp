<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0030)myself.jsp -->
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
<title>${model.title} | NorthPark </title>
<meta name="description" content="${model.title} | NorthPark">
<meta name="keywords" content="${model.title} | NorthPark">

<%@ include file="/WEB-INF/views/page/common/common.jsp"%>

</head>

<body >
	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	
	<div class="clearfix maincontent grayback">
			<div class="container mainbody">
				<div class="row">
					<div class="col-md-12">
						<div class="col-sm-8  col-md-offset-2 ">
							<div class="clearfix bg-white margin-t10 margin-b10 padding20 " id="J_white_div">
								<div class="row margin10 post_article">
									<div class="thumbnail border-0 center">
										<p>
											<small class="green-text">
												<font size="5">
													${model.title}
												</font>
											</small>
										</p>
										
										<p>
										<a class="common-a" title="${article.postdate}" >
											${model.date }
										</a>
									</p>
									
											<hr>
									</div>
									<p class="margin-t10">
										    <a title="${model.title}">
												<img src="${model.img }" >
											</a>
									</p>
									
									<p id="content_">
											
										${model.article }
									</p>
									<div class="clearfix visible-xs">
											<hr>
									</div>
								</div>
								
								
								 <!-- 打赏 -->
										<div>
      
        
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
										
										      
										</div>
										
								 <!-- 打赏 -->
								 
								 
								<!-- 评论 -->
								<div id="comment">
								
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
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


	

<%@ include file="/WEB-INF/views/page/common/container.jsp"%>


</body></html>    
