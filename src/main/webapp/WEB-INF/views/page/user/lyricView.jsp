<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>布.图</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimal-ui">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Language" content="zh-CN">
<meta name="description" content="添加我的最爱:布.词会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">
<meta name="author" content="www.qinco.net">
<meta name="robots" content="index,follow,archive">
<link rel="shortcut icon" href="img/favicon.ico">

<%@ include file="../common/common.jsp"%>
<style type="text/css">
#preview{width:200px;height:200px;border:1px solid #000;overflow:hidden;top:0%;position: relative;}
#imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
</style>
</head>

<body style="overflow: hidden;">
<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>

	<div class="clearfix maincontent">
		<div class="container">
			<div class="mainbody" style="margin-top: 5em;">
				<div class="align-center bg-white radius-5 padding10 max-width-400 min-width-300">
	<form id="f1" action="lyrics/addLyrics.action" method="post" enctype="multipart/form-data">
	   <input type="hidden" name="id" value="${model.id }"/>
	         上传者:<c:out value="${auther }"/><br/>
		    
		    
		           艺术家：
		    
		    <c:out value="${model.artist }"></c:out>
			<br/>
		    
		    
		    
		           专辑：
		    
		    <c:out value="${model.album }"></c:out>
			<br/>
		    
		    
		    
		        歌曲名：
		    
		     <c:out value="${model.title }"></c:out>
			<br/>
		    
		    
		    
		         媒体长度:
		    
		    <c:out value="${model.medialength }"></c:out>
			<br/>
		    
            
		    
		    
            
            <div id="preview">
				<img id="imghead" border=0 src="/bruce/${imgp }" width="200" height="200" />
			</div>

				<div class="form-group">
							<input id="download" onclick="downloadIt()" data-activetext="下载 ››"
								class="btn btn-hero btn-xlg margin-t10 grid50" value="下载歌词"
								type="button">
				</div>


	</form>
	</div>
				
				<br>
				<br>

			</div>


		</div>
	</div>
	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
	<script type="text/javascript">
	
	function downloadIt(){
		$("#f1").attr("action","lyrics/downloadLrc.action").submit();
	}
	</script>
</body>
</html>