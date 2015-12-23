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
<link rel="shortcut icon" href="img/favicon.ico">
<title>${MyInfo.username }生命中的最爱::布.词Fans</title>
<meta name="description" content="${MyInfo.username}生命中的最爱: 布.词会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">
<%@ include file="page/common/common.jsp"%>


</head>

<body style="">
	<%@ include file="/page/common/navigation.jsp"%>

    <%@ include file="/page/common/centre.jsp"%>
<form id="f2" method="post"><input name="userid" type="hidden" value="${user.id }"/></form>
 <form action="cm/toEditInfo" method="post" id="f1"><input name="userid" value="${user.id }" type="hidden"></form>
    <div class="clearfix maincontent">
	    <div class="container">
	      <div class="mainbody padding10" style="margin-top:2em;">
	 
		    <div class="clearfix margin-b20">
	         <ul class="nav nav-tabs">
		        <li><a href="cm/pcentral">布.图</a></li>
		        <li><a href="note/findAll">碎碎词</a></li>
		        <li class="active"><a href="/cm/myfans" >Fans</a></li>

			</ul>
			</div>		
			<c:forEach items="${fanlist }" var="s" varStatus="ss">
			<div class="row">
			    <div class="col-md-2">
				<h3 class="label label-gray ">${s.follow.create_time }：</h3>
			    </div>
			    <div class="col-md-10">
							<div class="row">
								
								<div class="col-xs-4 col-sm-2 center">
								
								<label class='btn btn-gray btn-xs pull-right delNoteBtn1'
									rel='${s.follow.id }' onclick="removes(this)"><span
									class='ace-icon glyphicon glyphicon-trash'></span></label>
								
								    
									<a 
									
									<c:if test="${s.user.tail_slug==null || s.user.tail_slug==''}">
								    href="/cm/detail/${s.user.id}"
								    </c:if>
								    <c:if test="${s.user.tail_slug!=null }">
								    href="/people/${s.user.tail_slug}"
								    </c:if>
									
									
									title="${s.user.username }"
										class="thumbnail border-0"> <img class=" img-circle"
										 <c:choose>
											<c:when test="${fn:contains(s.user.headpath  ,'http://') }">src="${s.user.headpath  }"</c:when>
											<c:otherwise>src="bruce/${s.user.headpath  }"</c:otherwise>
										 </c:choose> 
									
									alt="${s.user.username }">
										<p>${s.user.username }</p>
									</a>
								</div>
							</div>
						</div>
		     </div>
	 
	        <hr>
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
		$("#f2").attr("action","lyrics/toView.action?id="+id).submit();
	}
	
	function addSpan(obj){
		document.getElementById(obj).className = "span";
	}

	function rmSpan(obj){
		document.getElementById(obj).className = "";
	}

	function removes(obj){
		var id=$(obj).attr('rel');
		art.dialog.confirm('你确定要移除这个粉丝吗？', function () {
			$.ajax({
	 			url:"/cm/rmfollow",
	 			type:"post",
	 			data:{"id":id},
	 			success:function(msg){
	 				if(msg=="success"){
	 					art.dialog.tips('已移除');
	 					window.location.href = window.location.href;
	 				}else{
	 					art.dialog.tips('what happend...');
	 				}			
	 			}
	 		});
		}, function () {
		    return ;
		});
	}
	
	$("#J_gz_btn").click(function(){
		var url = window.location.href;
		//把userid的判断转为后台判断
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
        	  window.location.href = "/cm/toLogin?redirectURI="+url; 
          }
		
          }
		});
 	

	});

</script>

 

</body></html>    
