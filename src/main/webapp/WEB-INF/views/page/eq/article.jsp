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

<style type="text/css">
    /* 自定义评论  */
	#ds-thread #ds-reset .ds-powered-by{display:none;}
</style>
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
								
								<div id="comment">
									<!-- 多说评论框 start -->
									<div class="ds-thread" data-thread-key="romeo${model.id }" data-title="${model.title} | NorthPark" data-url="/romeo/${model.id }.html"></div>
									<!-- 多说评论框 end -->
								
								</div>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


	

<%@ include file="/WEB-INF/views/page/common/container.jsp"%>

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
