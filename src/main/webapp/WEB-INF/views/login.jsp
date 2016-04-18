<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>布词 | 登录布.词</title>
<meta name="description" content="登录布.词：布.词会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="登录,最爱,回忆,生活">

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
			  <a href="http://blog.northpark.cn/atom.xml" target="_blank" id="icon" title="订阅布.词RSS"><img src="/img/rss.png" width="20" height="20" alt="订阅布.词RSS"></a>
			</div>
		</div>
		<div class="navbar-collapse collapse mainhead-collapse">
			<ul class="nav mainhead-nav">
				<li><a href="/cm/list" title="一张图片，爱满满的">布.图</a></li>
				<li><a href="/note/list" title="一段歌词，一段回忆">布.词</a></li>
				<li class="active"><a href="/cm/reg" title="去注册">登陆</a></li>
			</ul>
		</div>
	</div>
</div>
    
    <div class="clearfix maincontent">
	    <div class="container">
	    	

        <div class="mainbody" style="margin-top:5em;">

                    
            <div class="align-center bg-white radius-5 padding10 max-width-400 min-width-300">

              <form method="POST" action="cm/login" accept-charset="UTF-8" role="form" id="loginForm" style="color:#444;" class="form margin-t20">
                <div class="clearfix">
                  <h4>登录我的布.词</h4>
                  <hr>
                </div>

						<div class="form-group ">
							<label for="loginAccount" class="control-label">您的Email：</label>
							<input id="loginAccount" placeholder="example@gmail.com"
								class="form-control  input-lg  border-light-1 bg-lyellow   radius-0"
								name="email" type="text">
						</div>

						<div class="form-group ">
							<label for="loginPassword" class="control-label">您的密码：</label> 
							<a class="pull-right" href="/cm/forget"><span
								class="glyphicon glyphicon-question-sign"></span> 忘记密码了</a> 
							<input id="loginPassword"
								class="form-control  input-lg  border-light-1 bg-lyellow    radius-0"
								name="password" type="password" value="">
						</div>

						<div class="checkbox">
							<input name="loginRemember" type="checkbox" value="yes">
							在这台电脑上记住我的登录
						</div>
						<input id="redirectURI" name="redirectURI" type="hidden"
							value="${redirectURI} ">
						<div class="form-group">
							<input id="formSubmit" data-activetext="登录 ››"
								class="btn btn-success btn-lg margin-t10 grid50" type="button"
								value="登录" disabled="disabled">
						</div>
						<div class="form-group">
							 <a class="pull-left" href="/cm/reg"><span
								class="glyphicon glyphicon-hand-right"></span> 还没账号，去注册</a>	
						</div>		
						<!-- <span id="qqLoginBtn"></span>
						<script type="text/javascript">
							QC.Login({
								btnId : "qqLoginBtn" //插入按钮的节点id
							});
						</script> -->
					</form>
            </div>
            <br><br>
            
          </div>
 

	    </div>
	</div>

<%@ include file="/WEB-INF/views/page/common/container.jsp"%>


<script type="text/javascript">


$(document).ready(function() {

	if( $('#formSubmit').val().length<6){
	 
		$('#formSubmit').attr('disabled',true);
    }
	
  $('#loginForm').on('keyup', '#loginPassword', function(event) {
      if($('#loginPassword').val().length>=6)
        $('#formSubmit').removeAttr('disabled').val($('#formSubmit').data('activetext') );
      else
        $('#formSubmit').attr('disabled',true);
    }); 
   
   $(document).keydown(function(event){
	    if(event.keyCode==13){
	       $("#formSubmit").click();
	    }
   });
   
   
   
  
  $('#formSubmit').click(function(){
	  $.ajax({
          url:"/cm/login",
          type:"post",
          data:$("#loginForm").serialize(),
          success:function(msg){
        	  console.log(msg);
              if(msg=="success"){
                  art.dialog.tips('登陆成功,正在跳转..', 3);
                  var uri = $("#redirectURI").val();
                  if(uri){
                	  window.location.href = uri;
                  }else{
                	  
                      window.location.href = "/cm/list";
                  }
              }else{
            	  art.dialog.tips('用户名密码错误');
              }            
          }
      });
  });
  
  
});




</script>

</body></html>