<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%--  <base href="<%=basePath%>"> --%>
<base href="http://northpark.cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--QQ登陆  -->
<!-- <meta property="qc:admins" content="61073355176476541272743636" /> 

<script type="text/javascript" data-callback="true" 
 src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js" 
 data-appid="101204466" data-redirecturi="http://northpark.cn/cm/list" charset="utf-8"></script>
  -->
<script src="/js/jquery-1.7.2.js"></script>  
<!-- <script src="/js/jquery.lazyload.js"></script>  --> 
<script src="/js/artDialog/artDialog.js?skin=blue"></script> 
<script src="/js/artDialog/jquery.artDialog.js?skin=blue"></script>
<script src="/js/artDialog/plugins/iframeTools.js"></script>
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


//QQ登陆code

//调用QC.Login方法，指定btnId参数将按钮绑定在容器节点中  
/* QC.Login({  
  //btnId：插入按钮的节点id，必选  
  btnId:"qqLoginBtn",      
}, function(reqData, opts){//登录成功  
   //根据返回数据，更换按钮显示状态方法  
  
	   if(QC.Login.check()){
       QC.Login.getMe(function(openId, accessToken){   
          $.ajax({
      		url:"/cm/qq/flag",
      		type:"post",
      		data:{"openId":openId},
      		success:function(msg){
      			if(msg=="0"){//首次绑定
      				 	var paras = {};  
      			        QC.api("get_user_info", paras)  
      			            .success(function(s){//成功回调  
      			            })  
      			            .error(function(f){//失败回调  
      			                art.dialog.tips('获取用户信息失败！');
      			            })  
      			            .complete(function(c){//完成请求回调  
      			                var  qqinfo = c.dataText;
      			                //异步传回用户数据进行绑定
      			                $.ajax({
      			            		url:"/cm/qq/add",
      			            		type:"post",
      			            		data:{"openId":openId,"qqinfo":qqinfo},
      			            		success:function(msg){
      			            			if(msg=="success"){
      			            				//art.dialog.tips('登陆成功');
      			            				$("#J_log_info_l").text("退出");
      			            				$("#J_log_info_l").attr("href","/cm/logout");
      			            				$("#J_log_info_r").text("我自己");
      			            				$("#J_log_info_r").attr("href","/cm/pcentral");
      			            			}			
      			            		}
      			            	}); 
      			            });  
      			}else if(msg == "1"){
      				//art.dialog.tips('登陆成功');
      				$("#J_log_info_l").text("退出");
      				$("#J_log_info_l").attr("href","/cm/logout");
      				$("#J_log_info_r").text("我自己");
      				$("#J_log_info_r").attr("href","/cm/pcentral");
      			}			
      		}
      	});
       });   
   }
  
   //这里可以调用自己的保存接口  
   //...  
}, function(opts){//注销成功  
	art.dialog.tips('qq注销');
    window.location.href = "/cm/logout?flag=qq";
}  
);  
 */

</script>



