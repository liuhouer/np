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
<link rel="shortcut icon" href="img/favicon.ico">
<title>${user.username}的碎碎词::布.词档案</title>
<meta name="description" content="${user.username}的碎碎词: 布.词会让您记住每一件美好的事物，正是它们勾勒出了您最真实的生命轨迹.分享好东西，记录生命回忆，记住世界上最好的东西。">
<meta name="keywords" content="最爱,回忆,生活">

<%@ include file="/WEB-INF/views/page/common/common.jsp"%>
<script src="/wangEditor/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<link href="/wangEditor/css/wangEditor-1.3.12.css" rel="stylesheet" />
<script src="/wangEditor/js/wangEditor-1.3.12.js" type="text/javascript"></script>


</head>


<body style="">

<%@ include file="/WEB-INF/views/page/common/navigation.jsp"%>
<%@ include file="/WEB-INF/views/page/common/centre.jsp"%>

    <div class="clearfix maincontent">
	    <div class="container">
	    		<div class="mainbody padding10" style="margin-top:2em;">
	 
		<div class="clearfix margin-b20">
	<ul class="nav nav-tabs">
		<li><a href="/cm/pcentral">布.图</a></li>
		<li class="active"><a href="note/findAll">碎碎词</a></li>
		<li ><a  href="/cm/myfans" >Fans</a></li>
		

			</ul>
</div>
		       <form id="f2" method="post"><input name="userid" type="hidden" value="${user.id }"/></form>
		       <form action="cm/toEditInfo" method="post" id="f1">
                   <input name="userid" value="${user.id }" type="hidden">
               </form>
				<div class="row bg-white margin-t10 margin-b10  ">
			<div class="col-sm-1">
				<a href="/cm/pcentral" title="${user.username}的最爱"><img src="/img/davatar.jpg" class="img-responsive  img-circle max-width-50" alt="654714226的最爱"></a>			</div>
			<div class="col-sm-11">
				<form method="POST" action="note/addNote" accept-charset="UTF-8" role="form" class="form">
              	<input name="userid" type="hidden" value="${user.id }"/>
              	
              	<div class="form-group">
              		<textarea id="J_md_text" style="height:200px; max-height:400px; width:100%;" name="note" rows="5"></textarea>
              	</div>
				<div class="form-group">
                    <button type="submit" class="btn btn-inverse btn-md"><span class="glyphicon glyphicon-music"></span> 添加碎碎词</button>
                    &nbsp; &nbsp; 
                    <span class="bg-lyellow"><input name="opened" type="checkbox" value="no"> <small>仅供自己看到，不对外公布</small></span>
                </div>
				</form>
			</div>
		</div>
		
		<c:forEach items="${list }" var="s" varStatus="ss">
		
		    <div class='row bg-white margin-t10 margin-b10' id='notebox_${s.id }'>
				<div class='col-sm-1'>
					<small class='label label-gray'>${s.createtime }</small>
				</div>
				<div class='col-sm-11'>
											<label class='btn btn-gray btn-xs pull-right delNoteBtn1' rel='${s.id }' onclick="removes(this)"><span class='glyphicon glyphicon-trash'></span></label>
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

	<%@ include file="/WEB-INF/views/page/common/fenye.jsp"%>
	    </div>

<%@ include file="/WEB-INF/views/page/common/container.jsp"%>


<script type="text/javascript">

function removes(obj){
	      var id=$(obj).attr('rel');
	      art.dialog.confirm('你确定要删除曾经的故事吗？', function () {
	    	  $("#f2").attr("action","note/remove?id="+id).submit();
			}, function () {
			    return ;
			});
	      
	      
}

function toEditInfo(){
	$("#f1").submit();
}
</script>

 <script type="text/javascript">
$(function(){
	var editor = $('#J_md_text').wangEditor({
		'menuConfig': [
		                ['viewSourceCode'],
						['fontFamily','fontSize','bold','setHead'],
						['list','justify','blockquote'],
						['createLink','insertHr','undo']
					]
	});
});
</script>

</body></html>