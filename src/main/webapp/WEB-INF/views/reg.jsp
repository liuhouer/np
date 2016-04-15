<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>布词|加入布.词，记住每一个美好</title>
<meta name="description" content="加入布.词布.词会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="加入布.词,最爱,回忆,生活">



<%@ include file="/WEB-INF/views/page/common/common.jsp"%>

</head>

<body style="">

	<div class="navbar navbar-default navbar-fixed-top mainhead-navbox" role="navigation">

	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle mainhead-navbtn" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">菜单导航</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="navbar-brand">
				<a href="http://blog.northpark.cn/atom.xml" target="_blank" id="icon" title="订阅布.词RSS"><img src="./img/rss.png" width="20" height="20" alt="订阅布.词博客RSS"></a>
			</div>
		</div>
		<div class="navbar-collapse collapse mainhead-collapse">
			<ul class="nav mainhead-nav">
				<li><a href="/cm/list" title="一张图片，爱满满的">布.图</a></li>
				<li><a href="note/list" title="一段歌词，一段回忆">布.词</a></li>
				<li class="active"><a href="/cm/toLogin" title="已有账号，去登录布.词">注册</a></li>
			</ul>
		</div>
	</div>
</div>

    
    <div class="clearfix maincontent">
	    <div class="container">
	    	

        <div class="mainbody" style="margin-top:5em;">

                    
            <div class="align-center bg-white radius-5 padding10 max-width-400 min-width-300">

              <form method="POST" action="/cm/addUser" accept-charset="UTF-8" role="form" id="signupForm" style="color:#444;" class="form margin-t20">
                <div class="clearfix">
                  <h4>加入布.词，记住每一个美好</h4>
                  <hr>
                </div>
                
                <div class="form-group ">
                  <label for="newAccount" class="control-label">Email：</label> 
 				  <input id="newAccount" placeholder="example@gmail.com" 
 				  class="form-control  input-lg  border-light-1 bg-lyellow grid98 radius-0" name="email" type="text">
 				</div>

                <div class="form-group ">
                  <label for="newPassword" class="control-label">设置密码：</label>
                  <input id="newPassword" class="form-control  input-lg  border-light-1 bg-lyellow  grid98 radius-0" name="password" type="password" value="">                                    
                </div>
                
                <label  class="control-label"><c:if test="${reged eq 'reged' }"><font color="red">账号已注册</font> </c:if>   </label>     
                <div class="form-group">
                    <input id="formSubmit" data-activetext="加入 ››" class="btn btn-hero btn-xlg margin-t10 grid50" type="submit" value="加入" disabled="disabled"> 
                </div>
                
                <div class="form-group">
							 <a class="pull-left" href="/cm/toLogin"><span
								class="glyphicon glyphicon-hand-right"></span> 已有账号，去登陆</a>	
				</div>	
              </form>
            </div>
            <br><br>
            
          </div>
 

	    </div>
	</div>

<%@ include file="/WEB-INF/views/page/common/container.jsp"%>


<script type="text/javascript">
	function em(email){
	
	   var Regex = /^(?:\w+\.?)*\w+@(?:\w+\.)*\w+$/;            

	   if (Regex.test(email)){                

		return true;
		
	   }else{
		   return false;
	   }        
		
	}

$(document).ready(function() {


	if( $('#formSubmit').val().length<6){
		 
		$('#formSubmit').attr('disabled',true);
    }
	
  
   $('#signupForm').on('keyup', '#newPassword', function(event) {
	   if($('#newPassword').val().length>=6 && em($('#newAccount').val()))
	          $('#formSubmit').removeAttr('disabled').val($('#formSubmit').data('activetext') );
      else
        $('#formSubmit').attr('disabled',true);
    }); 
   
   $("#newAccount").change(function(){
	   if($('#newPassword').val().length>=6 && em($('#newAccount').val()))
	          $('#formSubmit').removeAttr('disabled').val($('#formSubmit').data('activetext') );
	   else
		   $('#formSubmit').attr('disabled',true);
	});
   
  
});
</script>

 

</body></html>