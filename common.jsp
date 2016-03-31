<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" import="org.springframework.web.util.UrlPathHelper" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%-- <base href="<%=basePath%>"> --%>
<base href="http://northpark.cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta property="qc:admins" content="1447341246400123636" />
<script src="/js/jquery-1.7.2.js"></script>  
<script src="/js/artDialog/artDialog.js?skin=blue"></script> 
<script src="/js/artDialog/jquery.artDialog.js?skin=blue"></script>
<script src="/js/artDialog/plugins/iframeTools.js"></script>
<script src="/js/jquery.hotkeys.js"></script>
<script src="/js/jquery.validationEngine.js"></script>
<script src="/js/My97DatePicker/WdatePicker.js"></script>
<link media="all" type="text/css" rel="stylesheet" href="/css/bootstrap.min.css">
<link media="all" type="text/css" rel="stylesheet" href="/css/bootstrapValidator.min.css">
<link media="all" type="text/css" rel="stylesheet" href="/css/qinco.css">
<link media="all" type="text/css" rel="stylesheet" href="/css/main2.css">
    <style type="text/css">
    	body {background:#f4f3f1;}
	</style>
<script>


//3.设置全局对话框
// 设置对话框全局默认配置
(function (config) {  
    config['lock'] = true;  
    config['fixed'] = true;  
    config['okVal'] = 'Ok';  
    config['cancelVal'] = 'Cancel';  
    // [more..]  
})(art.dialog.blue);//这个是用哪个主题有很多主题的你把名字打上就行啦  
 
 

/**
 * checkbox全选事件
 */
function selectAll(){
	 var checklist = document.getElementsByName ("checkbox");
	   if(document.getElementById("al").checked)
	   {
	   for(var i=0;i<checklist.length;i++)
	   {
	      checklist[i].checked = 1;
	   } 
	 }else{
	  for(var j=0;j<checklist.length;j++)
	  {
	     checklist[j].checked = 0;
	  }
	 }
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
//         img.style.marginLeft = rect.left+'px';
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



