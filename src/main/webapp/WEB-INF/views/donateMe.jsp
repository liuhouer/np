<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.text.*"
pageEncoding="UTF-8" %>
	<!DOCTYPE html>
	<html lang="zh-CN">
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<meta charset="UTF-8">
			<meta http-equiv="X-UA-Compatible" content="IE=edge">
			<meta name="apple-mobile-web-app-capable" content="yes">
			<meta name="viewport" content="width=device-width, initial-scale=1.0, minimal-ui">
			<meta name="apple-mobile-web-app-status-bar-style" content="black">
			<meta http-equiv="Content-Language" content="zh-CN">
			<meta name="author" content="www.qinco.net">
			<meta name="robots" content="index,follow,archive">
			<link rel="shortcut icon" href="https://northpark.cn/statics/img/favicon.ico">
			<title>
				赞助本站 | NorthPark
			</title>
			<meta name="keywords" content="赞助本站NorthPark">
			<meta name="description" content="">
			<%@ include file="/WEB-INF/views/page/common/common.jsp" %>
			<style>
				hr{
					border: 1px solid #dedede
				}
			</style>
		</head>
		<body style="">
			<%@ include file="/WEB-INF/views/page/common/navigation.jsp" %>
				<!-- 页面标题 -->
				<div class="clearfix maincontent grayback">
					<div class="container mainbody">
						<div class="col-sm-7" style="color:#888">
							<div class="content margin-b20">
								<h4 style="color:#333">
									赞助本站
								</h4>
								<br>
								<p>
									NorthPark中文网为出于兴趣所作，MAC应用和影视资源均免费，目前注册用户5万+
								</p>
								<p class="bg-danger bold-text">
									服务器带宽等支出压力还是挺大的，需要你的支持！
								</p>
								<p>
									打赏完不定时进行统计，整理名单致谢，谢谢哈！
								</p>
								<p>
									NorthPark坚持的：
								</p>
								<ul>
									<li>
										资源免费
									</li>
									<li>
										无广告-不会上线任何广告干扰用户！
									</li>
									<li>
										高速下载-使用蓝奏、天翼、阿里云等不限速网盘！
									</li>
									<li>
										资源更新-影视资源每日定时爬虫更新！
									</li>
									<li>
										安全无后门-全站数千款应用和游戏，影视剧终生免费下载！
									</li>
								</ul>
								<br>
								<h4 style="color:#333">
									支付宝
								</h4>
								<br>
								<p>
									在手机上使用【支付宝钱包】，使用【扫一扫】功能，扫描下面的二维码图片后向本站赞助相应金额；
								</p>
								<p style="text-align: center;">
									<img class="alignnone  max-width-200 " src="https://northpark.cn/statics/img/pay/alipay-merchant.jpg"
									height="320px;">
								</p>
								<p style="text-align: center;">
									<img class="aligncenter" style="width: 150px;" src="https://northpark.cn/statics/img/pay/alipay-online.png"
									alt="赞助本站" width="150">
								</p>
								<h4 style="color:#333">
									微信
								</h4>
								<p>
									在手机上使用【微信】，使用【扫一扫】功能，扫描下面的二维码图片后向本站赞助相应金额；
								</p>
								<p style="text-align: center;">
									<img class="alignnone size-full max-width-200" src="https://northpark.cn/statics/img/pay/wxpay.jpg"
									height="300">
								</p>
								<h4 style="color:#333">
									Paypal
								</h4>
								<p>
									<a href="https://paypal.me/liuhouer" target="_blank" rel="nofollow">
										https://paypal.me/liuhouer
									</a>
								</p>
								<br>
								<h4 style="color:#333">
									联系我们
								</h4>
								<br>
								<p>
									对网站有什么疑问，可以联系Email：
									<a href="mailto:zhangyang.z@icloud.com">zhangyang.z@icloud.com</a>

								</p>
							</div>

							<!-- northpark评论模块 -->
							<div id="comment" class="row" >
								<h4 style="color:#333">
									留言
								</h4>
								<hr>
								<%--展示评论详情--%>
								<div class="clearfix" id="stuffCommentBox_999999">


								</div>


								<div id="J_progress" class="center padding-t20"></div>


								<div class="form-group clearfix note-comment margin-t10 " id="comment_999999">
                         			<textarea id="input_cm_999999" placeholder="说点什么吧..."
								    class="form-control bg-lyellow"
								    rows="3"></textarea>


									<button title="发布评论"


											class="btn btn-hero margin-t5 click2save"
											topic-id="999999"
											topic-type="7"
											from-uid="${user.id}"
											from-uname="${user.username}"
											data-input="#input_cm_999999">
										<span class="fa fa-floppy-o padding5"></span>发布评论</button>


								</div>


							</div>
							<!-- northpark评论模块 -->

						</div>
						<div class="col-sm-offset-1 col-sm-4" style="color:#888">
							<div class="row bg-lblue padding20 radius-5">
								<h4 style="color:#333">
									捐助榜
								</h4>
								<br>
								<ul id="donateTab" class="nav nav-tabs ">
									<li class="active">
										<a href="#1" onclick="loadDonates(1)" data-toggle="tab">
											大老板
										</a>
									</li>
									<li>
										<a href="#2" onclick="loadDonates(2)" data-toggle="tab">
											老板
										</a>
									</li>
									<li>
										<a href="#3" onclick="loadDonates(3)" data-toggle="tab">
											好心人
										</a>
									</li>
								</ul>
								<div id="donateContent" class="tab-content row bg-lblue radius-5">
								</div>
							</div>
						</div>
					</div>
				</div>
				<%@ include file="/WEB-INF/views/page/common/container.jsp" %>
					<script data-cfasync="false" src="https://northpark.cn/statics/js/page/forget.js">
					</script>
					<script data-cfasync="false">
						loadDonates(1);
						//展示全文和评论详情-- northpark评论模块 --
						loadComment(999999, 7);

						function beforeSend(XMLHttpRequest) {
							$("#J_progress").append("<div><img src='https://northpark.cn/statics/img/loading.gif' style='width:48px;height:48px;' /></div>");
						}


						function complete(XMLHttpRequest, textStatus) {
							$("#J_progress").empty();
						}
					</script>
		</body>
	
	</html>
