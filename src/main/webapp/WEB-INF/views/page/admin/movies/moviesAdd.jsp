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
<title>布词|添加电影</title>

<%@ include file="/WEB-INF/views/page/common/common.jsp"%>

		
</head>

<body >
<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>





	<div class="clearfix maincontent">
		<div class="container">
			<div class="mainbody" style="margin-top: 5em;">
				<div class="align-center bg-white radius-5 padding10 max-width-700 min-width-300">
					<form method="POST" action="movies/addItem" accept-charset="UTF-8" role="form" id="addItemForm" style="color: #444;" class="form margin-t20"  enctype="multipart/form-data">
						<div class="clearfix">
							<h4>
								<span class="glyphicon glyphicon-plus"></span> 添加电影资源
							</h4>
							<hr>
						</div>

						<div class="form-group ">
							<input id="J_name" placeholder="电影名" required
								class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
								name="moviename" type="text">
						</div>
						<div class="form-group ">
							<input id="J_path" placeholder="下载地址" required
								class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
								name="path" type="text">
						</div>
						<div class="form-group ">
							<input id="J_price" placeholder="定价" required
								class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
								name="price" type="text">
						</div>

						<div class="form-group">
							<textarea id="J_md_text" style="height: 200px; max-height: 400px;"
								name="description" rows="5">
								#电影简介
						    </textarea>
						</div>

						<div class="form-group">
							<input  id="formSubmit" data-activetext="添加 ››"
								class="btn btn-hero btn-xlg margin-t10 grid50" value="添加"
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

<script src="/wangEditor/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<link href="/wangEditor/css/wangEditor-1.3.12.css" rel="stylesheet" />
<script src="/wangEditor/js/wangEditor-1.3.12.js" type="text/javascript"></script>
<script type="text/javascript">
			$(function(){
				var editor = $('#J_md_text').wangEditor({
					'menuConfig': [
					                ['viewSourceCode'],
									['fontFamily','fontSize','bold','setHead'],
									['list','justify','blockquote'],
									['createLink','insertHr','undo']
								]
				});
				
				  //追加字符串
			    //editor.append('##电影简介');
				  
				  //提交表单
				 $("#formSubmit").click(function(){
					 if($("#J_name").val() && $("#J_md_text").val()&& $("#J_price").val()&& $("#J_path").val()){
						 
						 $.ajax({

				             url:"/movies/addItem",

				             type:"post",

				             data:$('#addItemForm').serialize(),// 要提交的表单 ,

				             success:function(msg){

				                 if(msg=="success"){

				                     art.dialog.tips('添加成功');
				                     $('#formSubmit').attr("disabled",'disabled');

				                 }            

				             }

				         });
					 }else{
						 art.dialog.tips('填写必要信息');
					 }
				 });
			});
</script>			
</body>
</html>