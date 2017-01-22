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
			
			var msg = $("#J_msg").val();
			if(msg=='success'){
				 art.dialog.tips('修改成功，正在跳转。。', 5);
				 window.location.href = "/cm/pcentral";
			}else if (msg == 'isolded'){
				 $("#J_tip").text("验证码已过期或失效，请重新获取");
				 art.dialog.tips('验证码已过期或失效，请重新获取',3);
				 //art.dialog.tips('成功！已经保存在服务器');
			}else if(msg=='exception'){
				 $("#J_tip").text("Oops...sth wrong u know");
				 art.dialog.tips('Oops...sth wrong u know',3);
			}
			
		})