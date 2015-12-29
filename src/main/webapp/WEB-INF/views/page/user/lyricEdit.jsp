<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑我的布.图</title>
<%@ include file="../common/common.jsp"%>
<style type="text/css">
#preview{width:200px;height:200px;border:1px solid #000;overflow:hidden;top: 5%;position: relative;}
#imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
</style>
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
<link media="all" type="text/css" rel="stylesheet" href="/css/bootstrap.css">
	<link media="all" type="text/css" rel="stylesheet" href="/css/qinco.css">
	<link media="all" type="text/css" rel="stylesheet" href="/css/main2.css">
</head>

<body style="overflow: hidden;">
<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>

	<div class="clearfix maincontent">
		<div class="container">
			<div class="mainbody" style="margin-top: 5em;">
				<div class="align-center bg-white radius-5 padding10 max-width-400 min-width-300">
	<form id="f1" action="lyrics/update.action" method="post" enctype="multipart/form-data">
	   <input type="hidden" name="userid" value="${userid }"/>
	   <input type="hidden" id="albumpath" value="${albumpath }">
	   <input type="hidden" name="oldpath" value="${model.albumImg }">
	   <input type="hidden" name="type" value="${model.type }">
	   <input type="hidden" name="id" value="${model.id }">
	   <input type="hidden" name="path" value="${model.path }">
		<table style="margin-top: 25px;">
		    
		    <tr>
		    <td>艺术家</td>
		    <td><input type="text"  name="artist" id="artist"  value="${model.artist }" style="color: gray;" class="validate[required]" /><span class="star">*</span>
			</td>
		    </tr>
		    
		    <tr>
		    <td>专辑</td>
		    <td><input type="text"  name="album" id="album"  value="${model.album }" style="color: gray;" class="validate[required]"/><span class="star">*</span>
			</td>
		    </tr>
		    
		    <tr>
		    <td>歌曲名</td>
		    <td><input type="text"  name="title"  
				id="title" value="${model.title }" style="color: gray;" class="validate[required]"/><span class="star">*</span>
			</td>
		    </tr>
		    
		    <tr>
		    <td>媒体长度</td>
		    <td><input type="text"  name="medialength"  
				id="medialength" value="${model.medialength }" style="color: gray;" class="validate[required]"/><span class="star">*</span>
			</td>
		    </tr>
            
             <tr>
             <td>专辑图片</td>
            <td >
            
		    <input name="file" id="file1" type="file" onchange="previewImage(this)" accept=".jpg,.gif,.png,.ico,.bmp" />  
		    </td>
		    </tr>
            
            <div id="preview">
				<img id="imghead" border=0 src="bruce/${imgp }" width="200" height="200" />
			</div>

			
			

 			<div >
			<input id="btnsave"  onclick="openIt()" data-activetext="更新 ››"
								class="btn btn-hero btn-xlg margin-t10 grid50" value="更新"
								type="submit"/>
			</div>

		</table>


	</form>
	</div>
				
				<br>
				<br>

			</div>


		</div>
	</div>
	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
	<script src="/js/jquery-1.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/main2.js"></script>
	<script type="text/javascript">
	$(function() {
		var albumpath = $("#albumpath").val();
		if (albumpath != "" && albumpath != null && albumpath != '' && albumpath != 'Failure...') {
			window.parent.showtip2("成功！");
		}
		if(albumpath=='Failure...'){
			window.parent.showtip2("添加失败！");
		}
	});
			function openIt() {
				// SUCCESS AJAX CALL, replace "success: false," by:     success : function() { callSuccessFunction() }, 
				$("[class^=validate]")
						.validationEngine(
								{
									success : function() {

										$("#f1")
												.attr("action",
														"lyrics/update.action")
												.submit();

									},
									failure : function() {
										return false;
									}
								});
			}
			
	  
	</script>
</body>
</html>