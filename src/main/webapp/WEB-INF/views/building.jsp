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
<link rel="shortcut icon" href="img/favicon.ico">
<title>布词|星际穿越</title>
<meta name="description" content="${user.username}生命中的最爱: 布.词会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>


</head>

<body class="">
	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>

<div class="clearfix maincontent "  >
		<div class="container" >
		<div class="mainbody" style="margin-top:10em;">
			<div class="align-center  radius-5 padding20 max-width-800 min-width-600">
				<div class="col-sm-6 col-sm-offset-3 margin-b20 margin-t20">
					<div class="row margin-b20 margin-t20">
						<p >
							你所寻找的东西目前并不在这个地址。
							</p>	
							<p>
							除非你找的就是这个错误页面，在这种情况下：恭喜！你真的找到了。
							</p>	
							<p>
							 <span id="mes" style="cursor: pointer;"><font color="#49c7be">返回首页</font></span> <!-- <span id="mes"><font color="blue">5</font></span> 秒钟后返回首页！ -->
						</p>		
					</div>
				</div>
			</div>
			</div>
		</div>
</div>

	

<%@ include file="/WEB-INF/views/page/common/container.jsp"%>


	  <script language="javascript" type="text/javascript">
		/* var i = 5;
		var intervalid;
		intervalid = setInterval("fun()", 1000);
		function fun() {
			if (i == 0) {
				window.location.href = "/";
				clearInterval(intervalid);
			}
			$("#mes").html("<font color=\"blue\">"+i+"</font>");
			i--;
		}
		 */
		 

			$(function(){
				$("#mes").click(function(){
					window.location.href="/";
				})
			})
		console.log('非法请求资源页，IP已记录');
	</script>  






</body></html>    
