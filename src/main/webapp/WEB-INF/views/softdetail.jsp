<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimal-ui">
<meta name="apple-mobile-web-app-status-bar-style" content="black">

<meta http-equiv="Content-Language" content="zh-CN">

<meta name="author" content="www.qinco.net">
<meta name="robots" content="index,follow,archive">
<link rel="shortcut icon"
	href="https://northpark.cn/statics/img/favicon.ico">
<title>${article.title}|NorthPark</title>
<meta name="keywords"
	content="${article.title},${article.tags},NorthPark">
<meta name="description" content="${description}">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>

</head>

<body>

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>


	<div class="clearfix maincontent grayback">
		<div class="container mainbody">
			<div class="row">
				<div class="col-md-12">
					<div class="col-sm-10  col-md-offset-1 ">
						<div class="clearfix bg-white margin-t10 margin-b10 padding20 "
							id="J_white_div">
							<div class="row margin10 post_article">
								<div class="border-0 center">
									<p>
										<small class="green-text">
											<h1>
												<strong>${article.title}</strong>
											</h1>
										</small>
									</p>



									<div class="clearfix visible-xs">
										<hr>
									</div>
								</div>
								<p>
									发表于： <strong><a class="common-a-right"
										title="${article.postdate}"
										href="/soft/date/${article.postdate }">
											${article.postdate} </a></strong> <strong><a
										class="common-a-right" title="${article.tags}"
										href="/soft/tag/${article.tagscode }"> ${article.tags} </a></strong> <strong><a
										class="common-a-right" title="${article.os}"
										href="/soft/${article.os }"> ${article.os} </a></strong>

										<c:if test="${user!=null }">
											<c:if
													test="${user.email == '654714226@qq.com' || user.email == 'qhdsoft@126.com' || user.email == 'woaideni@qq.com'}">

												<span class=" glyphicon glyphicon-arrow-up margin10"></span>
												<a class="common-a-right" title="置顶" href=""
												   onclick="handup('${model.id}')">置顶</a>
												<span class=" glyphicon glyphicon-eye-close margin10"></span>
												<a class="common-a-right" title="隐藏" href=""
												   onclick="hideup('${model.id}')">大尺度隐藏</a>
												<span class="glyphicon glyphicon-pencil margin10"></span>
												<a class="common-a" title="编辑"
												   href="/soft/edit/${article.id}">快速编辑</a>
											</c:if>
										</c:if>
								</p>
								<p id="content_${article.id}">${article.content }</p>

<%--								<p style="color: #e071a4;">--%>
<%--									蓝奏云下载地址如果打不开解决办法：<br/>--%>

<%--									lanzous.com <br/>--%>

<%--									URL改为 lanzoux.com 或者 lanzoui.com就可以了<br/>--%>
<%--								</p>--%>

								<p class="col-md-12">
								<div class="dashed center col-md-10 padding-b20">

									<c:if test="${user==null }">
										<p class="center red">
											本文隐藏内容 <a target="_blank" class="flatbtn green-text"
												id="J_login_see"><i class="fa fa-sign-in padding5" aria-hidden="true"></i>登录</a>
											后才可以浏览
										</p>
									</c:if>
									<c:if test="${user!=null }">
										<p id="J_show_path">${article.path }</p>
									</c:if>

									<small class="text-color-G">资源失效、缺失、错误 </small> <span
										class="text-color-J padding-l10">不要慌 </span>
									<button class="text-color-B margin-l10" id="J_feedback_btn">
										<span class="glyphicon glyphicon-hand-right"></span>戳我，一键反馈，有事找站长
									</button>
								</div>
								</p>
								<div class="clearfix visible-xs">
									<hr>
								</div>

								<!-- 打赏 -->
								<div class="col-md-10">


									<div
										style="padding: 10px 0; margin: 20px auto; width: 90%; text-align: center">
										<div class="margin10">生活不止苟且,还有我喜爱的海岸.</div>
										<button id="rewardButton" ,="" disable="enable"
											onclick="var qr = document.getElementById('QR'); if (qr.style.display === 'none') {qr.style.display='block';} else {qr.style.display='none'}"
											style="cursor: pointer; border: 0; outline: 0; border-radius: 100%; padding: 0; margin: 0; letter-spacing: normal; text-transform: none; text-indent: 0px; text-shadow: none">
											<span
												onmouseover="this.style.color='rgb(236,96,0)';this.style.background='rgb(204,204,204)'"
												onmouseout="this.style.color='#fff';this.style.background='rgb(236,96,0)'"
												style="display: inline-block; width: 70px; height: 70px; border-radius: 100%; color: rgb(255, 255, 255); font-style: normal; font-variant: normal; font-weight: 400; font-stretch: normal; font-size: 35px; line-height: 75px; font-family: microsofty; background: rgb(236, 96, 0);">赏</span>
										</button>
										<div id="QR" style="display: none;">

											<div id="wechat"
												style="display: inline-block; margin-right: 20px;">
												<a
													href="http://liuhouer.python-project.com/blog/donate/praise.jpg"
													class="fancybox" rel="group"><img id="wechat_qr"
													src="http://liuhouer.python-project.com/blog/donate/praise.jpg"
													alt="Bruce WeChat Pay"
													style="width: 200px; height: 200px; max-width: 100%; display: inline-block"></a>
												<p>微信打赏</p>
											</div>


											<div id="alipay"
												style="display: inline-block; margin-right: 20px;">
												<a
													href="http://liuhouer.python-project.com/blog/donate/alipay.png"
													class="fancybox" rel="group"><img id="alipay_qr"
													src="http://liuhouer.python-project.com/blog/donate/alipay.png"
													alt="Bruce Alipay"
													style="width: 200px; height: 200px; max-width: 100%; display: inline-block"></a>
												<p>支付宝打赏</p>
											</div>

										</div>
									</div>


								</div>

								<!-- 打赏 -->
							</div>


							<!-- 反馈失效的资源 -->
							<div class="col-md-10 margin-b20" id="J_container_feed"></div>

							<div class="clearfix visible-xs">
								<hr>
							</div>

							<!-- northpark评论模块 -->
							<div id="comment" class="col-md-10" >
							    <hr>
								<%--展示评论详情--%>
								<div class="clearfix" id="stuffCommentBox_${article.id}">

								</div>

								<div id="J_progress" class="center padding-t20"></div>

								<div class="form-group clearfix note-comment margin-t10 " id="comment_${article.id}">
                                <textarea id="input_cm_${article.id}" placeholder="说点什么吧..."
										  class="form-control bg-lyellow"
										  rows="3"></textarea>

							    <button title="发布评论"

												class="btn btn-hero margin-t5 click2save"
												topic-id="${article.id}"
												topic-type="3"
												from-uid="${user.id}"
												from-uname="${user.username}"
												data-input="#input_cm_${article.id}">
							    <span class="fa fa-floppy-o padding5"></span>发布评论</button>

							    </div>

							</div>
							<!-- northpark评论模块 -->

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>



	<script type="text/javascript" data-cfasync="false" >
		$(function() {
			//设置定向uri
			$("#J_login_see").attr("href",
					"/login?redirectURI=" + window.location.href);

			//feedback
			$("#J_feedback_btn").click(function() {
				var u = '${user.id}';
				if (u) {
					var spanID = '${article.id}';
					var title = '${article.title}';
					var href = window.location.href;
					var data = {
						"spanID" : spanID,
						"title" : title,
						"href" : href,
						"uID"  : u
					};

					$.ajax({
						url : "/movies/resFeedBack",
						type : "post",
						data : JSON.stringify(data),
						dataType : "json",//返回结果按照json接收
						contentType : "application/json",//提交的数据类型
						success : function(msg) {
							console.log(msg);
							if (msg.data == "success") {
								art.dialog.tips('反馈成功，站长接下来会更新资源');
								//list feedback
								feedbackList();
							} else {
								art.dialog.tips('error happened.');
							}
						}
					});

				} else {
					art.dialog.tips('登录后再试.');
				}
			});

			//list feedback
			feedbackList();

			//展示全文和评论详情-- northpark评论模块 --
			loadComment('${article.id}', 3);

		});

		/* get feed back */
		function feedbackList() {
			$.ajax({
				url : "/movies/getFeedBack",
				type : "get",
				success : function(data) {
					$("#J_container_feed").empty().append(data);
				}
			});

		}

		function handup(id) {
			$.ajax({
				url : "/soft/handup",
				type : "post",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(msg) {
					if (msg.data == "success") {
						art.dialog.tips('置顶成功');
					} else {
						art.dialog.tips('error happened.');
					}
				}
			});

		}

		function hideup(id) {
			$.ajax({
				url : "/soft/hideup",
				type : "post",
				data : {
					"id" : id
				},
				dataType : "json",
				success : function(msg) {
					if (msg.data == "success") {
						art.dialog.tips('隐藏成功');
						window.location.href = window.location.href;
					} else {
						art.dialog.tips('error happened.');
					}
				}
			});

		}


		function beforeSend(XMLHttpRequest) {
			$("#J_progress").append("<div><img src='https://northpark.cn/statics/img/loading.gif' style='width:48px;height:48px;' /></div>");
		}

		function complete(XMLHttpRequest, textStatus) {
			$("#J_progress").empty();
		}


	</script>




</body>
</html>
