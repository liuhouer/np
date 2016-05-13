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
<title>布.词 | 忘记密码</title>
<meta name="description" content="布.词会让您记住每一件美好的事物，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>

</head>

<body style="" >
	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>

	<input  type="hidden" id="J_msg" value="${msg}"/>
	<div class="clearfix maincontent" >
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

	<%@ include file="/WEB-INF/views/page/common/container.jsp"%>
	<script src="/js/page/forget.js"> </script>
	
	
	</body></html>