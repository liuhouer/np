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
<link rel="shortcut icon" href="img/favicon.ico">
<title>布.词::【忘记密码】::分享最爱,保存记忆,享受美好</title>
<meta name="description" content="布.词会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">
<%@ include file="page/common/common.jsp"%>

</head>

<body style="" >
	<%@ include file="/page/common/navigation.jsp"%>


	<div class="clearfix maincontent" style="background: #f4f3f1;">
		<div class="container">
		<div class="mainbody" style="margin-top:5em;">

                    
            <div class="align-center bg-white radius-5 padding10 max-width-400 min-width-300">

                <div class="clearfix">
                  <h4>忘记密码</h4>
                  <hr>
                </div>

					<div class="form-group ">
						<label for="J_email" class="control-label">Email：</label> <input
							id="J_email" placeholder="example@gmail.com"
							class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0"
							name="email" type="text">
					</div>
					<label  class="control-label iteminfo " id="J_tip"></label>     
					<div class="form-group">
						<input id="formSubmit" data-activetext="发送重置邮件 ››"
							class="btn btn-hero btn-xlg margin-t10 grid50" type="submit"
							value="发送重置邮件" disabled="disabled">
					</div>
				</div>
            <br><br>
            
          </div>
		
		
		</div>
	</div>

	<%@ include file="/page/common/container.jsp"%>
	<script type="text/javascript">
		$(function(){
			$("#J_email").change(function(){
				 var em = $("#J_email").val();
				 if(em){
					  $.ajax({
				             url:"/cm/emailFlag",
				             type:"post",
				             data:{"email":em},
				             success:function(msg){
				                 if(msg=="exist"){
				                	 $("#J_tip").text("success");
				                	 $('#formSubmit').removeAttr('disabled').val($('#formSubmit').data('activetext') );
				                 }else{
				                	 $("#J_tip").text("邮件账号未注册");
				                	 $('#formSubmit').attr('disabled',true);
				                 }            
				             }
				      });
				 }
			});
			
			$("#formSubmit").click(function(){
				
				 var em = $("#J_email").val();
				 $.ajax({
		             url:"/cm/resetEmail",
		             type:"post",
		             data:{"email":em},
		             success:function(msg){
		                 if(msg=="success"){//发送成功
		                	 $("#J_tip").text("发送成功，快去登陆你的邮箱操作吧");
		                	 $('#formSubmit').attr('disabled',true);
		                 }else{             //发送失败
		                	 $("#J_tip").text("发送失败，请检查邮件的真实性");
		                 }            
		             }
		      });
				
			});
			
			var msg = "${msg}";
			if(msg=='success'){
				 art.dialog.tips('修改成功，正在跳转。。', 5);
				 window.location.href = "cm/pcentral";
			}else if (msg == 'isolded'){
				 $("#J_tip").text("验证码已过期或失效，请重新获取");
				 art.dialog.tips('验证码已过期或失效，请重新获取',3);
				 //art.dialog.tips('成功！已经保存在服务器');
			}else if(msg=='exception'){
				 $("#J_tip").text("Oops...sth wrong u know");
				 art.dialog.tips('Oops...sth wrong u know',3);
			}
			
		})
	</script>