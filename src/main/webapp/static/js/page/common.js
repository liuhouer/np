//3.设置全局对话框
// 设置对话框全局默认配置
(function (config) {
    config['lock'] = true;
    config['fixed'] = true;
    config['okVal'] = 'Ok';
    config['cancelVal'] = 'Cancel';
    // [more..]
})(art.dialog.defaults);


//图片上传预览    IE是用了滤镜。
function previewImage(file) {
    var MAXWIDTH = 260;
    var MAXHEIGHT = 180;
    var div = document.getElementById('preview');
    if (file.files && file.files[0]) {
        div.innerHTML = '<img id=imghead>';
        var img = document.getElementById('imghead');
        img.onload = function () {
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width = rect.width;
            img.height = rect.height;
//         img.style.marginLeft = rect.left+'px';
            // img.style.marginTop = rect.top+'px';
        }
        var reader = new FileReader();
        reader.onload = function (evt) {
            img.src = evt.target.result;
        }
        reader.readAsDataURL(file.files[0]);
    }
    else //兼容IE
    {
        var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        div.innerHTML = '<img id=imghead>';
        var img = document.getElementById('imghead');
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
        div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;" + sFilter + src + "\"'></div>";
    }
}


function clacImgZoomParam(maxWidth, maxHeight, width, height) {
    var param = {top: 0, left: 0, width: width, height: height};
    if (width > maxWidth || height > maxHeight) {
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;

        if (rateWidth > rateHeight) {
            param.width = maxWidth;
            param.height = Math.round(height / rateWidth);
        } else {
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


//下拉查看更多的事件

$("body").on('click', '.click2show', function () {
    if ($(this).data('dismiss'))
        $($(this).data('dismiss')).hide();
    else
        $(this).hide();
    $($(this).data('target')).removeClass('hidden');
});


$("body").on('click', '.click2hide', function () {
    $($(this).data('target')).slideDown();
});

$("body").on('click', '.click2toggle', function () {
    $($(this).data('target')).slideToggle();
});

$("body").on('click', '.click2flip', function () {
    $($(this).data('hide')).slideUp();
    $($(this).data('show')).slideDown();
});



//复制内容自动添加版权信息 
/*var Sys = {}; 
   var ua = navigator.userAgent.toLowerCase(); 
   if( window.ActiveXObject ) 
   { 
       document.body.oncopy=function() 
       { 
           event.returnValue = false; 
           var t=document.selection.createRange().text; 
           var s="\n\r\t 原文出自[northpark.cn]NorthPark-小清新的软件、影视、心情、图片互动公园, 转载请保留原文链接:"+location.href; 
           clipboardData.setData('Text',t+'\r\n'+s); 
       } 
   } 
   else 
   { 
       function addLink() 
       { 
           var body_element = document.getElementsByTagName('body')[0]; 
           var selection; 
           selection = window.getSelection(); 
           var pagelink = "\n\r\t 原文出自[northpark.cn]NorthPark-小清新的软件、影视、心情、图片互动公园 ,转载请保留原文链接:"+document.location.href; 

           var copytext = selection + pagelink; 
           var newdiv = document.createElement('div'); 
           newdiv.style.position='absolute'; 
           newdiv.style.left='-99999px'; 
           body_element.appendChild(newdiv); 
           newdiv.innerHTML = copytext; 
           selection.selectAllChildren(newdiv); 
           window.setTimeout 
           ( 
               function() 
               { 
                   body_element.removeChild(newdiv); 
               },0 
           ); 
       } 
       document.oncopy = addLink; 
   } */

/*$(function () {
    $(window).resize(function () {
        $('body').css('min-height', ($(window).height()));
        $('.maincontent').css('min-height', ($(window).height() - 100));
        $('body').css('overflowX', 'hidden');

    }).resize();
});*/
