<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0030)myself.jsp -->
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
<title>${user.username}生命中的最爱::布.词档案</title>
<meta name="description" content="${user.username}生命中的最爱: 布.词会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">
<%@ include file="page/common/common.jsp"%>


<style id="holderjs-style" type="text/css"></style><script type="text/javascript" src="chrome-extension://bfbmjmiodbnnpllbbbfblcplfjjepjdn/js/injected.js"></script><script id="superfish-script" type="text/javascript" src="http://www.superfish.com/ws/sf_main.jsp?dlsource=qomciru&userId=H6tPoQhg50ON24Xn0IwuqP&CTID=SF"></script></head>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1254650304'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s11.cnzz.com/z_stat.php%3Fid%3D1254650304%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script>
<body style="">
     <form action="cm/toEditInfo" method="post" id="f1">
       <input name="userid" value="${user.id }" type="hidden">
     </form>
     <form action="" method="post" id="f2">
       <input name="userid" value="${user.id }" type="hidden">
     </form>
	<%@ include file="/page/common/navigation.jsp"%>

    	<%@ include file="/page/common/centre.jsp"%>

    <div class="clearfix maincontent">
	    <div class="container">
	      <div class="mainbody padding10" style="margin-top:2em;">
	 
		    <div class="clearfix margin-b20">
	         <ul class="nav nav-tabs">
		        <li class="active"><a href="cm/pcentral">布.图</a></li>
		        <li><a href="note/findAll">碎碎词</a></li>
		         <li ><a  href="/cm/myfans" >Fans</a></li>
			</ul>
			</div>		
			
			<c:forEach items="${Lovelist }" var="s" varStatus="ss">
			<c:forEach items="${LyricsList }" var="x" varStatus="xx">
			<c:if test="${s.lyricsid==x.id &&s.userid==MyInfo.id}">
			<div class="row">
			    <div class="col-md-2">
				<h3 class="label label-gray ">${x.updatedate }：</h3>
			    </div>
			    <div class="col-md-10">
									<div class="row">
										<div class="col-xs-4 col-sm-2 center"
											<c:if test="${user.id!=null }">onmouseover="addSpan('delspan${ss.index }')" onmouseout="rmSpan('delspan${ss.index }')"</c:if>>
											<c:if test="${user.id!=null }">
												<span id="delspan${ss.index }"
													onclick="removes('${x.id}','${s.id }')"></span>
											</c:if>
											<a href="javascript:void(0)" onclick="toView('${x.id}')"
												title="${x.title }" class="thumbnail border-0"> <img
												src="bruce/${x.albumImg }" alt="${x.title }">
												<p>${x.title }</p>
											</a>
										</div>
									</div>
								</div>
		     </div>
	 
	        <hr>
	        </c:if>
	        </c:forEach>
			</c:forEach>
		
</div>
<br>
<br>

</div>

	    </div>
	

<%@ include file="/page/common/container.jsp"%>


    




<script type="text/javascript">

$(document).ready(function() {

	var ajax_url='/ajax';
	var _aj = {user_id: '50777'};
	_aj['user_agent']='68A697E775AE';
	_aj['timestamp']='1400553338';
	_aj['user_keychain']='956FC269E368';

	
	});
	
	function toEditInfo(){
		$("#f1").submit();
	}
	
	function toView(id){
		$("#f2").attr("action","lyrics/toEdit.action?id="+id).submit();
	}
	
	function addSpan(obj){
		document.getElementById(obj).className = "glyphicon glyphicon-trash";
	}

	function rmSpan(obj){
		document.getElementById(obj).className = "";
	}

	function removes(lyricsid,userlyricsid){
		art.dialog.confirm('你确定要删除这首最爱歌词吗？', function () {
		    $("#f2").attr("action","lyrics/remove.action?lyricsid="+lyricsid+"&userlyricsid="+userlyricsid).submit();
		}, function () {
		    return ;
		});
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
