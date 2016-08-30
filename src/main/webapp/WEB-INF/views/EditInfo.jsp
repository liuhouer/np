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
<link rel="shortcut icon" href="img/favicon.ico">
<title>northpark / 编辑我的档案</title>
<meta name="description" content="采麦会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="northpark,最爱,回忆,生活">
<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
<link media="all" type="text/css" rel="stylesheet" href="/css/bsorigin/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="/js/bootstrap-datepicker.js"></script>


    <style type="text/css">
        #imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);width: 100%;width: 100%;position: relative;}
	</style>

</head>

<body style="">

	<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>

    
    
    <input  type="hidden" id="J_tail_slug" value="${MyInfo.tail_slug }"/>
    <div class="clearfix maincontent">
	    <div class="container">
	    	


<div class="mainbody" style="margin-top:5em;">

    <div clas="row">
    
    <div class="col-lg-6 col-lg-offset-3 bg-white radius-5  min-width-300">
      <form method="POST" action="/cm/saveEditInfo" enctype="multipart/form-data" accept-charset="UTF-8" role="form" style="color:#444;" id="f1" class="form-horizontal margin-t20">     
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
          <p class="help-block">如需修改邮箱，请<a href="mailto:zhangyang.z@icloud.com">联系我们</a></p>
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
                                  <c:otherwise>src="/bruce/${MyInfo.headpath }"</c:otherwise>
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
        <label for="tail_slug" class="col-md-3 control-label input-lg ">域名代号：</label>        <div class="col-md-9 text-left ">
          <div class="clearfix"><p class=""><b style="font-size:1.6em"> <input id="tail_slug" placeholder="vivian1987" required class="input-lg grid33 border-light-1 bg-lyellow radius-0" name="tail_slug" type="text" value="${MyInfo.tail_slug }"></b></p></div>
          <div class="clearfix"><p class="help-block">可以用2~20位的英文或者数字组成</p></div>

          
        </div>
      </div>
      <hr>
      <div class="form-group ">
        <label for="user_birth" class="col-md-3 control-label input-lg">生日：</label>        <div class="col-md-9 text-left">
          <div class="row">
            <input id="user_nick" placeholder="1991-12-31"  class="form_date form-control border-light-1 input-lg bg-lyellow padding10 grid70 radius-0" name="year_of_birth" type="text" value="${Dinfo.year_of_birth }">
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

       </form>    </div>
    <br><br>
    
  </div>
</div>
</div>
	    </div>
	

<%@ include file="/WEB-INF/views/page/common/container.jsp"%>



<script src="/js/page/editinfo.js"></script>



</body></html>   
