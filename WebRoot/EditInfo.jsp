<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
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
<link rel="shortcut icon" href="img/favicon.png">
<title>编辑我的档案</title>
<meta name="description" content="采麦会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">
<%@ include file="/page/common/common.jsp"%>

    <style type="text/css">
    	body {background:#f4f3f1;}
        #imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);width: 100%;width: 100%;position: relative;}
	</style>

<style id="holderjs-style" type="text/css"></style><script type="text/javascript" src="chrome-extension://bfbmjmiodbnnpllbbbfblcplfjjepjdn/js/injected.js"></script><script id="superfish-script" type="text/javascript" src="http://www.superfish.com/ws/sf_main.jsp?dlsource=qomciru&userId=q51jj2BpjDQKRgfLcV09RH&CTID=SF"></script></head>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254650304'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1254650304%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>
<body style="">

	<%@ include file="/page/common/navigation.jsp"%>

    
    <div class="clearfix maincontent">
	    <div class="container">
	    	


<div class="mainbody" style="margin-top:5em;">

    <div clas="row">
    
    <div class="col-lg-6 col-lg-offset-3 bg-white radius-5  min-width-300">
      <form method="POST" action="cm/saveEditInfo" enctype="multipart/form-data" accept-charset="UTF-8" role="form" style="color:#444;" id="f1" class="form-horizontal margin-t20"><input name="_token" type="hidden" value="UbnQeCcixF5pWNvwd42LhdrAMbif51AXuWIiYbtf">      
      <div class="form-group">
        <div class="col-md-9 col-md-offset-3 text-left">
          <h3>编辑我的档案</h3>
        </div>
      </div>
      <hr>
      <div class="form-group">
        <label for="email" class="col-md-3 control-label input-lg">注册邮箱：</label>        <div class="col-md-9 text-left">
          <input type="hidden" name="id"  value="${MyInfo.id }">
          <input type="hidden" name="email"  value="${MyInfo.email }">
          <input type="hidden" name="password"  value="${MyInfo.password }">
          <input type="hidden" name="oldpath"  value="${MyInfo.headpath }">
          <input type="hidden" name="date_joined"  value="${MyInfo.date_joined }">
          
          <p id="email" class="input-lg padding0 control-label text-left">${MyInfo.email }</p>
          <p class="help-block">如需修改邮箱，请<a href="http://user.qzone.qq.com/1007136434/infocenter?ptsig=28Kr5VKAgebWZnAm1pmylFiXL98OvRVMtfQ4kVhzEbM_">联系我们</a></p>
        </div>
      </div>
      <hr>
      <div class="form-group ">
        <label for="username" class="col-md-3 control-label input-lg">昵称：</label>        <div class="col-md-9 text-left">
          <input id="username" placeholder="肥肥安1987" class="form-control border-light-1 input-lg bg-lyellow padding10 grid70 radius-0" name="username" type="text" value="${MyInfo.username }">                    <p class="help-block">可以包含中文、英文、字母</p>
          
        </div>
      </div>
      <hr>

      <div class="form-group ">
        <label for="user_avatar" class="col-md-3 control-label input-lg">头像：</label>        <div class="col-md-9 text-left">
          <div class="row">
            <div class="col-xs-3" id="preview"><img  id="imghead" 

            <c:if test="${MyInfo.headpath==null }">src="/img/davatar.jpg"</c:if>
			<c:if test="${MyInfo.headpath!=null }">
			    <c:choose>
                                  <c:when test="${fn:contains(MyInfo.headpath ,'http://') }">src="${MyInfo.headpath }"</c:when>
                                  <c:otherwise>src="bruce/${MyInfo.headpath }"</c:otherwise>
                                </c:choose> 
			</c:if>

            class="img-responsive"></div>
            <div class="col-xs-9">
              
        <div id="plupload_box_uploadavatar50777" style="position: relative;">

            <div class="clearfix " id="plupload_queue_uploadavatar50777" style="height:10px;"></div>
            <div class="clearfix">
              <button type="button" id="plupload_pickbtn_uploadavatar50777" class="btn btn-large btn-gray btn-round " style="position: relative; z-index: 0;" onclick="headpath.click()"> <span class="glyphicon"></span> 修改头像</button>
              <button type="button" id="plupload_startupload_uploadavatar50777" class="hide  btn">.</button>
            </div>
            
         <input id="headpath" name="file" style="font-size: 999px; position: absolute; width: 100%; height: 100%;visibility: hidden;" type="file" accept="jpg,png" onchange="previewImage(this)">
         </div>
              <p class="help-block">JPG或者PNG格式</p>
            </div>
          </div>
                    
          
        </div>
      </div>
      <hr>
      
      <div class="form-group ">
        <label for="user_slug" class="col-md-3 control-label input-lg ">字母代号：</label>        <div class="col-md-9 text-left ">
          <div class="clearfix"><p class=""><b style="font-size:1.6em">http://buci.cc/ <input id="user_slug" placeholder="vivian1987" class="input-lg grid33 border-light-1 bg-lyellow radius-0" name="user_slug" type="text" value="${Dinfo.user_slug }"></b></p></div>
          <div class="clearfix"><p class="help-block">可以用6~20位的英文或者数字组成</p></div>

          
        </div>
      </div>
      <hr>
      <div class="form-group ">
        <label for="user_birth" class="col-md-3 control-label input-lg">生日：</label>        <div class="col-md-9 text-left">
          <div class="row">
            <input id="user_nick" placeholder="1991-12-31" onClick="WdatePicker()" class="Wdate form-control border-light-1 input-lg bg-lyellow padding10 grid70 radius-0" name="year_of_birth" type="text" value="${Dinfo.year_of_birth }">
          </div>
                  </div>
      </div>
      <hr>
      <div class="form-group ">
        <label for="courseware" class="col-md-3 control-label input-lg">博客/网站：</label>        <div class="col-md-9 text-left">
        <div class="row">
          <input id="courseware" placeholder="http://meditic.com" class="form-control border-light-1 input-lg bg-lyellow padding10 grid70 radius-0" name="courseware" type="text" value="${Dinfo.courseware }">                    
        </div>
        </div>
      </div>
      <hr>
      <div class="form-group ">
        <label for="passwordBtn" class="col-md-3 control-label input-lg">密码：</label>        <div class="col-md-9 text-left">
          
          <p class="input-lg padding0" id="passwordBtn"><button type="button" class="btn btn-gray btn-lg click2show" data-dismiss="#passwordBtn" data-target="#passwordBox">修改密码</button></p>
          <div class="row hidden" id="passwordBox">
            <div class="col-sm-6">
              <p>新密码：<input id="new_password" class="form-control  input-md  border-light-1 bg-lyellow  grid98 radius-0" name="new_password" type="password" value=""></p>
            </div>
            <div class="col-sm-6">
              <p>重复一次：<input id="new_password_confirmation" class="form-control  input-md  border-light-1 bg-lyellow  grid98 radius-0" name="new_password_confirmation" type="password" value=""></p>
            </div>
          </div>
                  </div>
      </div>
      <hr>
      
      <div class="form-group">
        <div class="col-sm-offset-3 col-md-9  text-left">
          <input class="btn btn-hero btn-lg margin-t10 " type="button" onclick="saves()" value="更新档案">        </div>
      </div>

      <input name="user_id" type="hidden" value="50777">      <input name="legalCheck" type="hidden" value="33DF46299863">      </form>    </div>
    <br><br>
    
  </div>
</div>
</div>
	    </div>
	

<%@ include file="/page/common/container.jsp"%>

<script async="" src="/js/analytics.js"></script><script src="/js/jquery-1.11.0.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/main2.js"></script>

      <script src="/js/plupload.js"></script>
  <script src="/js/plupload.full.js"></script>





<script type="text/javascript">

$(document).ready(function() {

	var ajax_url='/ajax';
	var _aj = {user_id: '50777'};
	_aj['user_agent']='68A697E775AE';
	_aj['timestamp']='1400654161';
	_aj['user_keychain']='FB637A9ACD11';
   
});


function saves(){
	var newpwd1=$("#new_password").val();
	var newpwd2=$("#new_password_confirmation").val();
	if(newpwd2!=newpwd1&&newpwd1!=""&&newpwd1!=''&&newpwd1!=null){//修改密码填了不一致
		art.dialog.alert('2次密码不一致');
		return;
	}else{
	$("#f1").attr("action","cm/saveEditInfo").submit();
	}
	
	
}
</script>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-199262-13', 'buci.cc');
  ga('send', 'pageview');

</script>
 

<script type="text/javascript" charset="utf-8" id="ABD75F83F0359849_Analytics" src="http://tajs.qq.com/stats?sId=26628622"></script></body></html>   
