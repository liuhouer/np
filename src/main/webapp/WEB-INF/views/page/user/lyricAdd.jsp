<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  import="org.springframework.web.util.UrlPathHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimal-ui">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Language" content="zh-CN">
<meta name="description" content="添加我的最爱:布.词会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">
<meta name="author" content="bruce">
<meta name="robots" content="index,follow,archive">
<title>添加我的布.图</title>
<%@ include file="../common/common.jsp"%>
<style type="text/css">
#preview{width:200px;height:200px;border:1px solid #000;overflow:hidden;top: 25%;position: relative;}
#imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);}
body {background:#f4f3f1;}
</style>
</head>

<body >
<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>





	<div class="clearfix maincontent">
		<div class="container">
			<div class="mainbody" style="margin-top: 5em;">
				<div class="align-center bg-white radius-5 padding10 max-width-400 min-width-300">
					<form method="POST" action="lyrics/addLyrics.action" accept-charset="UTF-8" role="form" id="addItemForm" style="color: #444;" class="form margin-t20"  enctype="multipart/form-data">
						<div class="clearfix">
							<h4>
								<span class="glyphicon glyphicon-plus"></span> 添加我的歌词
							</h4>
							<hr>
						</div>

						<div class="form-group ">
							<input id="artist" placeholder="艺术家 比如：周杰伦"
								class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
								name="artist" type="text">
						</div>
						<div class="form-group ">
							<input id="album" placeholder="专辑 比如：依然范特西"
								class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
								name="album" type="text">
						</div>
						<div class="form-group ">
							<input id="title" placeholder="歌曲名 比如：夜的第七章"
								class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
								name="title" type="text">
						</div>
						<div class="form-group ">
							<input id="medialength" placeholder="媒体长度 比如：00:04:25"
								class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
								name="medialength" type="text">
						</div>
						<span style="color: #999; opacity: 1;">专辑图片</span>
					            <input placeholder="专辑图片 选择一个你喜欢的图片" name="file" id="file1" type="file" onchange="previewImage(this)" accept=".jpg,.gif,.png,.ico,.bmp" class=" border-light-1 bg-lyellow grid98 radius-0"/>  
						<span style="color: #999; opacity: 1;">歌词文件</span>
						 		<input  placeholder="选择一个你喜欢的歌词" name="file" id="lrcid" type="file"  accept=".lrc"  class="border-light-1 bg-lyellow grid98 radius-0" />  
						<div class="clearfix hidden" id="searchResult"></div>

						<div class="form-group">
							<input disabled="disabled" id="formSubmit" data-activetext="添加 ››"
								class="btn btn-hero btn-xlg margin-t10 grid50" value="添加"
								type="submit">
						</div>
						 <div id="preview" class="align-center bg-white radius-5 padding10 max-width-400 min-width-300" style="padding-left: 50px;border: 0">
							<img id="imghead" border=0 src="img/head_180.jpg" width="200" height="200" />
				</div>
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

$(document).ready(function() {
	


	
	
  $('#formSubmit').attr('disabled',true);
  $('#addItemForm').on('keyup', '#artist', function(event) {
	    if($('#artist').val().length>=1&&$('#album').val().length>=1&&$('#title').val().length>=1&&$('#medialength').val().length>=1&&$('#lrcid').val().length>=1)
	    {
	      $('#formSubmit').removeAttr('disabled').val($('#formSubmit').data('activetext') );

	    }
	    else
	      $('#formSubmit').attr('disabled',true);
	  
    });
  
  $('#addItemForm').on('keyup', '#album', function(event) {
	    if($('#artist').val().length>=1&&$('#album').val().length>=1&&$('#title').val().length>=1&&$('#medialength').val().length>=1&&$('#lrcid').val().length>=1)
	    {
	      $('#formSubmit').removeAttr('disabled').val($('#formSubmit').data('activetext') );

	    }
	    else
	      $('#formSubmit').attr('disabled',true);
	  
  });
  
  $('#addItemForm').on('keyup', '#title', function(event) {
	    if($('#artist').val().length>=1&&$('#album').val().length>=1&&$('#title').val().length>=1&&$('#medialength').val().length>=1&&$('#lrcid').val().length>=1)
	    {
	      $('#formSubmit').removeAttr('disabled').val($('#formSubmit').data('activetext') );

	    }
	    else
	      $('#formSubmit').attr('disabled',true);
	  
  });
  
  $('#addItemForm').on('keyup', '#medialength', function(event) {
	    if($('#artist').val().length>=1&&$('#album').val().length>=1&&$('#title').val().length>=1&&$('#medialength').val().length>=1&&$('#lrcid').val().length>=1)
	    {
	      $('#formSubmit').removeAttr('disabled').val($('#formSubmit').data('activetext') );

	    }
	    else{
	      $('#formSubmit').attr('disabled',true);
	    }
	  
  });
  
  
  $('#addItemForm').on('change', '#lrcid', function(event) {
	    if($('#artist').val().length>=1&&$('#album').val().length>=1&&$('#title').val().length>=1&&$('#medialength').val().length>=1&&$('#lrcid').val().length>=1)
	    {
	      $('#formSubmit').removeAttr('disabled').val($('#formSubmit').data('activetext') );

	    }
	    else{
	      $('#formSubmit').attr('disabled',true);
	    }
	  
  });

 


});
</script>

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
										var ness = $("#lrcid").val();
										if(ness==""){
											art.dialog.alert('请选择您要上传的歌词文件！');
											return false;
										}

										$("#f1")
												.attr("action",
														"lyrics/addLyrics.action")
												.submit();

									},
									failure : function() {
										return false;
									}
								});
			}
			
	  
			
			
			
			
			//图片上传预览    IE是用了滤镜。
			function previewImage(file)
			{
			  var MAXWIDTH  = 260; 
			  var MAXHEIGHT = 180;
			  var div = document.getElementById('preview');
			  if (file.files && file.files[0])
			  {
			      div.innerHTML ='<img id=imghead>';
			      var img = document.getElementById('imghead');
			      img.onload = function(){
			        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
			        img.width  =  rect.width;
			        img.height =  rect.height;
//			         img.style.marginLeft = rect.left+'px';
			        img.style.marginTop = rect.top+'px';
			      }
			      var reader = new FileReader();
			      reader.onload = function(evt){img.src = evt.target.result;}
			      reader.readAsDataURL(file.files[0]);
			  }
			  else //兼容IE
			  {
			    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
			    file.select();
			    var src = document.selection.createRange().text;
			    div.innerHTML = '<img id=imghead>';
			    var img = document.getElementById('imghead');
			    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
			    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
			    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
			    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
			  }
			}


			function clacImgZoomParam( maxWidth, maxHeight, width, height ){
			    var param = {top:0, left:0, width:width, height:height};
			    if( width>maxWidth || height>maxHeight )
			    {
			        rateWidth = width / maxWidth;
			        rateHeight = height / maxHeight;
			        
			        if( rateWidth > rateHeight )
			        {
			            param.width =  maxWidth;
			            param.height = Math.round(height / rateWidth);
			        }else
			        {
			            param.width = Math.round(width / rateHeight);
			            param.height = maxHeight;
			        }
			    }
			    
			    param.left = Math.round((maxWidth - param.width) / 2);
			    param.top = Math.round((maxHeight - param.height) / 2);
			    return param;
			}
	</script>
</body>
</html>