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
<link rel="shortcut icon" href="img/favicon.png">
<title>${MyInfo.username}的碎碎词::布.词Note</title>
<meta name="description" content="${MyInfo.username}的碎碎词: 布.词会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">
<%@ include file="page/common/common.jsp"%>

<style id="holderjs-style" type="text/css"></style><script type="text/javascript" src="chrome-extension://bfbmjmiodbnnpllbbbfblcplfjjepjdn/js/injected.js"></script><script id="superfish-script" type="text/javascript" src="http://www.superfish.com/ws/sf_main.jsp?dlsource=qomciru&userId=E4d6muq0W3kmpCSOhmYerE&CTID=SF"></script></head>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254650304'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1254650304%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>
<body style="">

<%@ include file="/page/common/navigation.jsp"%>
<%@ include file="/page/common/centrespace.jsp"%>

    <div class="clearfix maincontent">
	    <div class="container">
	    		<div class="mainbody padding10" style="margin-top:2em;">
	 
		<div class="clearfix margin-b20">
	<ul class="nav nav-tabs">
		<li><a href="/cm/detail/${MyInfo.id}">布.图</a></li>
		<li class="active"><a href="/note/viewNotes/${MyInfo.id}">碎碎词</a></li>
		<li><a href="/cm/fans/${MyInfo.id}">Fans</a></li>
		

			</ul>
</div>
		       <form id="f2" method="post"><input name="userid" type="hidden" value="${MyInfo.id }"/></form>
				<div class="row bg-white margin-t10 margin-b10  ">
			<div class="col-sm-1">
				<a href="/cm/detail/${MyInfo.id}" title="${MyInfo.username}的最爱"><img src="img/davatar.jpg" class="img-responsive  img-circle max-width-50" alt="654714226的最爱"></a>			</div>
			<%-- <div class="col-sm-11">
				<form method="POST" action="note/addNote" accept-charset="UTF-8" role="form" class="form"><input name="_token" type="hidden" value="UbnQeCcixF5pWNvwd42LhdrAMbif51AXuWIiYbtf">	              
              	<input name="userid" type="hidden" value="${MyInfo.id }"/>
              	<div class="form-group">
              		<textarea class="form-control" name="note" rows="5"></textarea>
              	</div>
                <div class="form-group">
                    <button type="submit" class="btn btn-inverse btn-md"><span class="glyphicon glyphicon-music"></span> 添加碎碎词</button>
                    &nbsp; &nbsp; <span class="bg-lyellow"><input name="opened" type="checkbox" value="no"> <small>仅供自己看到，不对外公布</small></span>
                </div>
				</form>
			</div> --%>
		</div>
		
		<c:forEach items="${list }" var="s" varStatus="ss">
		
		    <div class='row bg-white margin-t10 margin-b10' id='notebox_${s.id }'>
				<div class='col-sm-1'>
					<small class='label label-gray'>${s.createtime }</small>
				</div>
				<div class='col-sm-11'>
											<%-- <label class='btn btn-gray btn-xs pull-right delNoteBtn1' rel='${s.id }' onclick="removes(this)"><span class='glyphicon glyphicon-remove'></span></label> --%>
										<p>${s.note }</p>					<hr />
				</div>
				
			</div>
		</c:forEach>
		

			
	</div>

	<div class="row center">
			</div>
<br>
<br>

</div>

	<%@ include file="/page/common/fenye.jsp"%>
	    </div>

<%@ include file="/page/common/container.jsp"%>


<script type="text/javascript">

$(document).ready(function() {

	var ajax_url='/ajax';
	var _aj = {user_id: '50777'};
	_aj['user_agent']='68A697E775AE';
	_aj['timestamp']='1400644032';
	_aj['user_keychain']='926F5505C670';
 
});

function removes(obj){
	      var id=$(obj).attr('rel');
	      art.dialog.confirm('你确定要删除曾经的故事吗？', function () {
	    	  $("#f2").attr("action","note/remove?id="+id).submit();
			}, function () {
			    return ;
			});
	      
	      
}

$("#J_gz_btn").click(function(){
	
	//把userid的判断转为后台判断
	    var url = window.location.href;
	    $.ajax({
      url:"/cm/loginFlag",
      type:"post",
      success:function(msg){
      if(msg=="1"){//已登录
    	  var userid = '${user.id}' ;
    	 	var author_id = $("#by_id").val();
    	 	var gz_status = '${gz }';
    	 		if(author_id==userid){
    	 			art.dialog.alert('您不能关注自己');
    	 			return ;
    	 		}
    	 		if(gz_status=='ygz'){
    	 			return ;
    	 		}
    	 		$.ajax({
    	 			url:"/cm/follow",
    	 			type:"post",
    	 			data:{"author_id":author_id,"follow_id":userid},
    	 			success:function(msg){
    	 				if(msg=="success"){
    	 					art.dialog.tips('已关注');
    	 					window.location.href = window.location.href;
    	 				}			
    	 			}
    	 		});

      }else if(msg=="0"){//没有登录
      window.location.href = "/cm/loginTT?url="+url; 
      }
 	}
		});
 	
});

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