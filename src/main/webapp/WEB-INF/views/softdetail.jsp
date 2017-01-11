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
<title>${article.title} | NorthPark</title>
<meta name="description" content="NorthPark,文艺,小清新,Mac软件,影视窝,碎碎念,图册,情圣,情商提升">
<meta name="keywords" content="NorthPark是一个很小清新的互动公园。NorthPark包含了丰富的Mac软件资源、最新的影视剧资源、大量提升男生情商的文章、大家吐槽的，喜爱的，心情日记的精神角落、图册互动等版块。它富有交互性和趣味性，文艺范和小清新，并且可以和你的朋友们为某个兴趣互动。">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
<style type="text/css">
    /* 自定义评论  */
	#ds-thread #ds-reset .ds-powered-by{display:none;}
</style>
</head>

<body >

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
	 
	
		<div class="clearfix maincontent grayback">
			<div class="container mainbody">
				<div class="row">
					<div class="col-md-12">
						<div class="col-sm-10  col-md-offset-1 ">
							<div class="clearfix bg-white margin-t10 margin-b10 padding20 " id="J_white_div">
								<div class="row margin10 post_article">
									<div class="thumbnail border-0 center">
										<p>
											<small class="green-text">
												<font size="5">
													<strong>${article.title}</strong>
												</font>
											</small>
										</p>
										<div class="clearfix visible-xs">
											<hr>
										</div>
									</div>
									<p>
										发表于：
										<a class="common-a-right" title="${article.postdate}" href="/soft/date/${article.postdate }">
											${article.postdate}
										</a>
										<a class="common-a-right" title="${article.tags}" href="/soft/tag/${article.tagscode }">
											${article.tags}
										</a>
										<a class="common-a" title="${article.os}" href="/soft/${article.os }">
											${article.os}
										</a>
									</p>
									<p id="content_${sarticle.index}">
										${article.content }
									</p>
									<div class="clearfix visible-xs">
											<hr>
									</div>
									<p>
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
									</p>
								</div>
								
								<div id="comment">
								<!-- 多说评论框 start -->
								<div class="ds-thread" data-thread-key="${article.retcode }" data-title="${article.title} | NorthPark" data-url="/soft/${article.retcode }.html"></div>
								<!-- 多说评论框 end -->
								
								</div>
								
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
			$(this).css('max-width',($("#J_white_div").width()));
		})
	})
	</script>

	<!-- 多说公共JS代码 start (一个网页只需插入一次) -->
	<script type="text/javascript">
	var duoshuoQuery = {short_name:"jellyband"};
		(function() {
			var ds = document.createElement('script');
			ds.type = 'text/javascript';ds.async = true;
			ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
			ds.charset = 'UTF-8';
			(document.getElementsByTagName('head')[0] 
			 || document.getElementsByTagName('body')[0]).appendChild(ds);
		})();
		</script>
	<!-- 多说公共JS代码 end -->						
	

</body></html>
